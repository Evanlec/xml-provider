package org.jahia.modules.xmlprovider.factory;

import org.hibernate.validator.constraints.NotEmpty;
import org.jahia.modules.external.admin.mount.AbstractMountPointFactory;
import org.jahia.modules.external.admin.mount.validator.LocalJCRFolder;
import org.jahia.services.content.JCRNodeWrapper;

import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import java.io.Serializable;

public class XmlMountPointFactory extends AbstractMountPointFactory implements Serializable {
    private static final long serialVersionUID = -41541258548484556L;

    @NotEmpty
    private String name;

    private String localPath;
    @NotEmpty
    private String root;

    public XmlMountPointFactory() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    @Override
    public void populate(JCRNodeWrapper nodeWrapper) throws RepositoryException {
        super.populate(nodeWrapper);
        this.name = getName(nodeWrapper.getName());
        try {
            this.localPath = nodeWrapper.getProperty("mountPoint").getNode().getPath();
        } catch (PathNotFoundException e) {

        }
        this.root = nodeWrapper.getPropertyAsString("j:xmlPath");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLocalPath() {
        return localPath;
    }

    @Override
    public String getMountNodeType() {
        return "jnt:xmlMountPoint";
    }

    @Override
    public void setProperties(JCRNodeWrapper mountNode) throws RepositoryException {
        mountNode.setProperty("j:xmlPath",root);
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
