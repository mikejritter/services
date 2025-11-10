/**
 * This document is a part of the source code and related artifacts
 * for CollectionSpace, an open source collections management system
 * for museums and related institutions:
 *
 * http://www.collectionspace.org
 * http://wiki.collectionspace.org
 *
 * Copyright © 2009 Regents of the University of California
 *
 * Licensed under the Educational Community License (ECL), Version 2.0.
 * You may not use this file except in compliance with this License.
 *
 * You may obtain a copy of the ECL 2.0 License at
 * https://source.collectionspace.org/collection-space/LICENSE.txt
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.collectionspace.services.client.test;

import java.math.BigDecimal;

import org.collectionspace.services.client.CollectionSpaceClient;
import org.collectionspace.services.client.DimensionClient;
import org.collectionspace.services.client.DimensionFactory;
import org.collectionspace.services.client.PoxPayloadOut;
import org.collectionspace.services.dimension.DimensionsCommon;
import org.collectionspace.services.jaxb.AbstractCommonList;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * DimensionServiceTest, carries out tests against a
 * deployed and running Dimension Service.
 *
 * $LastChangedRevision: 917 $
 * $LastChangedDate: 2009-11-06 12:20:28 -0800 (Fri, 06 Nov 2009) $
 */
public class DimensionServiceTest extends AbstractPoxServiceTestImpl<AbstractCommonList, DimensionsCommon> {

    /** The logger. */
    private final String CLASS_NAME = DimensionServiceTest.class.getName();
    private final Logger logger = LoggerFactory.getLogger(CLASS_NAME);

    // Instance variables specific to this test.
    /** The SERVIC e_ pat h_ component. */
    private final String DIMENSION_VALUE = "78.306";

    @Override
    protected Logger getLogger() {
        return this.logger;
    }
    
	@Override
	protected String getServiceName() {
		return DimensionClient.SERVICE_NAME;
	}
    
    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.BaseServiceTest#getClientInstance()
     */
    @Override
    protected CollectionSpaceClient getClientInstance() throws Exception {
        return new DimensionClient();
    }

	@Override
	protected CollectionSpaceClient getClientInstance(String clientPropertiesFilename) throws Exception {
        return new DimensionClient(clientPropertiesFilename);
	}	
    
    protected void compareInstances(DimensionsCommon original, DimensionsCommon updated) throws Exception {
        Assert.assertEquals(original.getValueDate(),
        		updated.getValueDate(),
                "Data in updated object did not match submitted data.");
    }
    
    @Override
    protected DimensionsCommon updateInstance(DimensionsCommon dimensionsCommon) {
    	DimensionsCommon result = new DimensionsCommon();
        
    	// Update the content of this resource.
    	result.setValue(dimensionsCommon.getValue().multiply(new BigDecimal("2.0")));
    	result.setValueDate("updated-" + dimensionsCommon.getValueDate());
        
        return result;
    }
    
    // ---------------------------------------------------------------
    // Utility methods used by tests above
    // ---------------------------------------------------------------
    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.BaseServiceTest#getServicePathComponent()
     */
    @Override
    public String getServicePathComponent() {
        return DimensionClient.SERVICE_PATH_COMPONENT;
    }

    @Override
    protected PoxPayloadOut createInstance(String identifier) throws Exception {
    	DimensionClient client = new DimensionClient();
    	return createInstance(client.getCommonPartName(), identifier);
    }
    
    /**
     * Creates the dimension instance.
     *
     * @param identifier the identifier
     * @return the multipart output
     */
    @Override
    protected PoxPayloadOut createInstance(String commonPartName, String identifier) {
        return createDimensionInstance(commonPartName, 
                "dimensionType-" + identifier,
                DIMENSION_VALUE,
                "entryDate-" + identifier);
    }

    /**
     * Creates the dimension instance.
     *
     * @param dimensionType the dimension type
     * @param entryNumber the entry number
     * @param entryDate the entry date
     * @return the multipart output
     */
    private PoxPayloadOut createDimensionInstance(String commonPartName, String dimensionType, String dimensionValue, String entryDate) {
        DimensionsCommon dimensionsCommon = new DimensionsCommon();
        dimensionsCommon.setDimension(dimensionType);
        dimensionsCommon.setValue(new BigDecimal(dimensionValue));
        dimensionsCommon.setValueDate(entryDate);
        PoxPayloadOut multipart = DimensionFactory.createDimensionInstance(
                commonPartName, dimensionsCommon);

        if (logger.isDebugEnabled()) {
            logger.debug("to be created, dimension common");
            logger.debug(objectAsXmlString(dimensionsCommon,
                    DimensionsCommon.class));
        }

        return multipart;
    }
	    
    // Placeholders until the three tests below can be uncommented.
    // See Issue CSPACE-401.

    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.AbstractServiceTestImpl#createWithMalformedXml(java.lang.String)
     */
    @Override
    public void createWithMalformedXml(String testName) throws Exception {
        //Should this really be empty?
    }

    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.AbstractServiceTestImpl#createWithWrongXmlSchema(java.lang.String)
     */
    @Override
    public void createWithWrongXmlSchema(String testName) throws Exception {
        //Should this really be empty?
    }
    
	@Override
	public void createWithEmptyEntityBody(String testName) throws Exception {
    	//FIXME: Should this test really be empty?
	}
    @Override
    public void updateWithEmptyEntityBody(String testName) throws Exception {
        //Should this really be empty?
    }

    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.AbstractServiceTestImpl#updateWithMalformedXml(java.lang.String)
     */
    @Override
    public void updateWithMalformedXml(String testName) throws Exception {
        //Should this really be empty?
    }

    /* (non-Javadoc)
     * @see org.collectionspace.services.client.test.AbstractServiceTestImpl#updateWithWrongXmlSchema(java.lang.String)
     */
    @Override
    public void updateWithWrongXmlSchema(String testName) throws Exception {
        //Should this really be empty?
    }

	@Override
	protected void compareUpdatedInstances(DimensionsCommon original,
			DimensionsCommon updated) throws Exception {
		//Check the dimension value to see if the update happened correctly
		BigDecimal expectedValue = original.getValue();
		BigDecimal actualValue = updated.getValue();
		Assert.assertTrue(actualValue.compareTo(expectedValue) == 0);
		
		//Next, check the date value to see if it was updated
		String expectedDate = original.getValueDate();
		String actualDate = updated.getValueDate();
		Assert.assertEquals(actualDate, expectedDate);
	}

    /*
     * For convenience and terseness, this test method is the base of the test execution dependency chain.  Other test methods may
     * refer to this method in their @Test annotation declarations.
     */
    @Override
    @Test(dataProvider = "testName",
    		dependsOnMethods = {
        		"org.collectionspace.services.client.test.AbstractServiceTestImpl.baseCRUDTests"})    
    public void CRUDTests(String testName) {
    	// Do nothing.  Simply here to for a TestNG execution order for our tests
    }
}
