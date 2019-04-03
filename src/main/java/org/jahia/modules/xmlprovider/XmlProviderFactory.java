package org.jahia.modules.xmlprovider;

import org.jahia.exceptions.JahiaInitializationException;
import org.jahia.modules.external.ExternalContentStoreProvider;
import org.jahia.modules.xmlprovider.factory.XmlMountPointFactoryHandler;
import org.jahia.services.SpringContextSingleton;
import org.jahia.services.content.JCRNodeWrapper;
import org.jahia.services.content.JCRSessionFactory;
import org.jahia.services.content.JCRStoreProvider;
import org.jahia.services.content.ProviderFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.jcr.RepositoryException;

public class XmlProviderFactory implements ProviderFactory, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(XmlMountPointFactoryHandler.class);
    private static final String CACHE_NAME  = "xml-cache";
    private ApplicationContext applicationContext;

    @Override
    public String getNodeTypeName() { return "jnt:xmlMountPoint"; }

    @Override
    public JCRStoreProvider mountProvider(JCRNodeWrapper mountPoint) throws RepositoryException {
        ExternalContentStoreProvider provider = (ExternalContentStoreProvider) SpringContextSingleton.getBean("ExternalStoreProviderPrototype");
        provider.setKey(mountPoint.getIdentifier());
        provider.setMountPoint(mountPoint.getPath());
        XmlDataSourceWritable dataSource = applicationContext.getBean(XmlDataSourceWritable.class);

        // Initialization of the EhCache if necessary
        if (dataSource.getCache() == null) {
            try {
                if (!dataSource.getEhCacheProvider().getCacheManager().cacheExists(CACHE_NAME)) {
                    dataSource.getEhCacheProvider().getCacheManager().addCache(CACHE_NAME);
                }
                dataSource.setCache(dataSource.getEhCacheProvider().getCacheManager().getCache(CACHE_NAME));
            } catch (Exception e) {
                logger.error("Error with the cache : " + e.getMessage());
            }
        }

        // Setting the XML path from the mount point
        dataSource.setXmlFilePath(mountPoint.getPropertyAsString("j:xmlPath"));
        // Setting the data source then start
        provider.setDataSource(dataSource);
        provider.setDynamicallyMounted(true);
        provider.setSessionFactory(JCRSessionFactory.getInstance());
        try {
            provider.start();
        } catch (JahiaInitializationException e) {
            throw new RepositoryException(e);
        }
        return provider;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
