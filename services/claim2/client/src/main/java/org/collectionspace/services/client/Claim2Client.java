/*
 * This document is a part of the source code and related artifacts
 * for CollectionSpace, an open source collections management system
 * for museums and related institutions:
 *
 * http://www.collectionspace.org
 * http://wiki.collectionspace.org
 *
 * Licensed under the Educational Community License (ECL), Version 2.0.
 * You may not use this file except in compliance with this License.
 *
 * You may obtain a copy of the ECL 2.0 License at
 * https://source.collectionspace.org/collection-space/LICENSE.txt
 */
package org.collectionspace.services.client;

import org.collectionspace.services.claim2.Claims2Common;

/**
 * ClaimClient.java
 */
public class Claim2Client extends AbstractCommonListPoxServiceClientImpl<Claim2Proxy, Claims2Common> {

    public static final String SERVICE_NAME = "claims2";
    public static final String SERVICE_PATH_COMPONENT = SERVICE_NAME;
    public static final String SERVICE_PATH = "/" + SERVICE_PATH_COMPONENT;
    public static final String SERVICE_PATH_PROXY = SERVICE_PATH + "/";
    public static final String SERVICE_PAYLOAD_NAME = SERVICE_NAME;

    public Claim2Client() throws Exception {
        super();
    }

    public Claim2Client(String clientPropertiesFilename) throws Exception {
        super(clientPropertiesFilename);
    }

    @Override
    public String getServicePathComponent() {
        return SERVICE_PATH_COMPONENT;
    }

    @Override
    public String getServiceName() {
        return SERVICE_NAME;
    }

    @Override
    public Class<Claim2Proxy> getProxyClass() {
        return Claim2Proxy.class;
    }
}
