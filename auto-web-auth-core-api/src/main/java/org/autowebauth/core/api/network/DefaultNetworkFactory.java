package org.autowebauth.core.api.network;

import org.autowebauth.core.api.network.provider.NetworkProvider;

/**
 * Wrapper class for default implementation of network factory.
 * 
 * @author Andrin Bertschi
 * 
 */
class DefaultNetworkFactory implements NetworkFactory
{

    private static final String NETWORK_FACTORY_IMPL = "org.autowebauth.core.network.factory.DefaultNetworkFactoryImpl";

    private NetworkFactory factoryWrapper;

    public DefaultNetworkFactory()
    {
        createFactoryImpl();
    }

    @Override
    public NetworkProvider getProvider()
    {
        // delegate to impl
        return this.factoryWrapper.getProvider();
    }

    @Override
    public NetworkProvider getProvider(Class<? extends NetworkProvider> type)
    {
        // delegate to impl
        return this.factoryWrapper.getProvider(type);
    }

    private void createFactoryImpl()
    {
        try
        {
            this.factoryWrapper = (NetworkFactory) Class.forName(
                    NETWORK_FACTORY_IMPL).newInstance();
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new IllegalArgumentException(
                    "Instanciation of DefaultNetworkFactory not possible", e);
        }
        catch (ClassNotFoundException e)
        {
            throw new IllegalArgumentException(
                    "DefaultNetworkFactory implementation not found", e);
        }
    }
}
