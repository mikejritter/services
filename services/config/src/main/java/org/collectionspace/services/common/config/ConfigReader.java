/**
 *  This document is a part of the source code and related artifacts
 *  for CollectionSpace, an open source collections management system
 *  for museums and related institutions:

 *  http://www.collectionspace.org
 *  http://wiki.collectionspace.org

 *  Copyright 2009 University of California at Berkeley

 *  Licensed under the Educational Community License (ECL), Version 2.0.
 *  You may not use this file except in compliance with this License.

 *  You may obtain a copy of the ECL 2.0 License at

 *  https://source.collectionspace.org/collection-space/LICENSE.txt

 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.collectionspace.services.common.config;

import java.io.File;
import java.util.List;

import org.collectionspace.services.common.api.JEEServerDeployment;

/**
 * ConfigReader is an interface for a configuration reader
 */
public interface ConfigReader<T> {

    final public static String RESOURCES_DIR_NAME = "resources";
    final public static String RESOURCES_DIR_PATH = JEEServerDeployment.CSPACE_DIR_NAME + File.separator +
    		JEEServerDeployment.CONFIG_DIR_PATH + File.separator + RESOURCES_DIR_NAME;

    /**
     * getFileName - get configuration file name
     * @return
     */
    public String getFileName();

    /**
     * read parse and read the default configuration file from default location
     * @throws Exception
     */
    public void read(boolean useAppGeneratedBindings) throws Exception;

    /**
     * read parse and read the given configruation file.
     * @param configFile fully qualified file name
     * @throws Exception
     */
    public void read(String configFile, boolean useAppGeneratedBindings) throws Exception;

    /**
     * Merge the given configuration files, and parse the result.
     * @param configFiles fully qualified file names
     * @throws Exception
     */
    public void read(List<String> configFiles, boolean useAppGeneratedBindings) throws Exception;

    /**
     * getConfig get configuration binding
     * @return
     */
    public T getConfiguration();
}
