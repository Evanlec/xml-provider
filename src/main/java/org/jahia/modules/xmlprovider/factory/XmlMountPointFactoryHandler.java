package org.jahia.modules.xmlprovider.factory;

import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.VFS;
import org.jahia.services.content.JCRCallback;
import org.jahia.services.content.JCRSessionWrapper;
import org.jahia.services.content.JCRTemplate;
import org.jahia.utils.i18n.Messages;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.webflow.execution.RequestContext;
import org.jahia.modules.external.admin.mount.AbstractMountPointFactoryHandler;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import javax.jcr.RepositoryException;
import java.io.Serializable;
import java.util.Locale;


public class XmlMountPointFactoryHandler extends AbstractMountPointFactoryHandler implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(XmlMountPointFactoryHandler.class);

    private static final long serialVersionUID = 41541258548484556L;

    private static final String BUNDLE = "resources.xml-externalprovider";

    private XmlMountPointFactory xmlMountPointFactory;

    private String stateCode;
    private String messageKey;

    public void init(RequestContext requestContext) {
        xmlMountPointFactory = new XmlMountPointFactory();
        try {
            super.init(requestContext, xmlMountPointFactory);
        } catch (RepositoryException e) {
            logger.error("Error retrieving mount point", e);
        }
        requestContext.getFlowScope().put("xmlFactory", xmlMountPointFactory);
    }

    public String getFolderList() {
        JSONObject result = new JSONObject();
        try {
            JSONArray folders = JCRTemplate.getInstance().doExecuteWithSystemSession(new JCRCallback<JSONArray>() {
                @Override
                public JSONArray doInJCR(JCRSessionWrapper session) throws RepositoryException {
                    return getSiteFolders(session.getWorkspace(),"contents");
                }
            });

            result.put("folders",folders);
        } catch (RepositoryException e) {
            logger.error("Error trying to retrieve local folders", e);
        } catch (JSONException e) {
            logger.error("Error trying to construct JSON from local folders", e);
        }

        return result.toString();
    }

    public Boolean save(MessageContext messageContext, RequestContext requestContext) {
        stateCode = "SUCCESS";
        Locale locale = LocaleContextHolder.getLocale();
        boolean validXmlPoint = validateXml(xmlMountPointFactory);
        if(!validXmlPoint) {
            logger.error("Error saving mount point : " + xmlMountPointFactory.getName() + "with the root : " + xmlMountPointFactory.getRoot());
            MessageBuilder messageBuilder = new MessageBuilder().error().defaultText(Messages.get(BUNDLE,"label",locale));
            messageContext.addMessage(messageBuilder.build());
            requestContext.getConversationScope().put("adminURL", getAdminURL(requestContext));
            return false;
        }
        try {
            boolean available = super.save(xmlMountPointFactory);
            if (available) {
                stateCode = "SUCCESS";
                messageKey = "label.success";
                requestContext.getConversationScope().put("adminURL", getAdminURL(requestContext));
                return true;
            } else {
                logger.warn("Mount point availability problem : " + xmlMountPointFactory.getName() + "with the root : " + xmlMountPointFactory.getRoot() + "the mount point is created but unmounted");
                stateCode = "WARNING";
                messageKey = "label.error";
                requestContext.getConversationScope().put("adminURL", getAdminURL(requestContext));
                return true;
            }
        } catch (RepositoryException e) {
            logger.error("Error saving mount point : " + xmlMountPointFactory.getName(), e);
            MessageBuilder messageBuilder = new MessageBuilder().error().defaultText(Messages.get(BUNDLE,"label.error",locale));
            messageContext.addMessage(messageBuilder.build());
        }
        return false;
    }

    private boolean validateXml(XmlMountPointFactory xmlMountPointFactory) {
        try {
            VFS.getManager().resolveFile(xmlMountPointFactory.getRoot());
        } catch (FileSystemException e) {
            logger.warn("XML mount point " + xmlMountPointFactory.getName() + " has validation problem " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public String getAdminURL(RequestContext requestContext) {
        StringBuilder builder = new StringBuilder(super.getAdminURL(requestContext));
        if (stateCode != null && messageKey != null) {
            builder.append("?stateCode=").append(stateCode).append("&messageKey=").append(messageKey).append("&bundleSource=").append(BUNDLE);
        }
        return builder.toString();
    }

}
