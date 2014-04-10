/*
 * Copyright 2005-2011 WSO2, Inc. (http://wso2.com)
 *
 *      Licensed under the Apache License, Version 2.0 (the "License");
 *      you may not use this file except in compliance with the License.
 *      You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *      Unless required by applicable law or agreed to in writing, software
 *      distributed under the License is distributed on an "AS IS" BASIS,
 *      WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *      See the License for the specific language governing permissions and
 *      limitations under the License.
 */

package org.wso2.carbon.cloudmgt.internal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.service.component.ComponentContext;
import org.wso2.carbon.cloudmgt.users.service.UserManagementService;
import org.wso2.carbon.cloudmgt.users.util.AppFactoryConfiguration;
import org.wso2.carbon.cloudmgt.users.util.UserMgtUtil;
import org.wso2.carbon.registry.core.service.RegistryService;
import org.wso2.carbon.user.core.service.RealmService;
import org.wso2.carbon.utils.ConfigurationContextService;

/**
 *@scr.component name="org.wso2.carbon.cloudmgt.user"
 *                immediate="true"
 * @scr.reference name="user.realmservice.default"
 *                interface="org.wso2.carbon.user.core.service.RealmService"
 *                cardinality="1..1" policy="dynamic" 
 *                bind="setRealmService"
 *                unbind="unsetRealmService"
 * @scr.reference name="registry.service"
 *                interface="org.wso2.carbon.registry.core.service.RegistryService"
 *                cardinality="1..1" policy="dynamic"
 *                bind="setRegistryService"
 *                unbind="unsetRegistryService"
 * @scr.reference name="configuration.context.service"
 *                interface="org.wso2.carbon.utils.ConfigurationContextService"
 *                cardinality="1..1" policy="dynamic"
 *                bind="setConfigurationContextService"
 *                unbind="unsetConfigurationContextService"
 */
public class CloudUserMgtServiceComponent {
    private static Log log = LogFactory.getLog(CloudUserMgtServiceComponent.class);

    protected void activate(ComponentContext context) {
    	log.info("CloudUserMgtServiceComponent activated successfully.");
    	context.getBundleContext().registerService(UserManagementService.class.getName(), new UserManagementService(), null);
    }

    protected void deactivate(ComponentContext context) {
        if (log.isDebugEnabled()) {
            log.debug("DefaultRolesCreatorServiceComponent Service  bundle is deactivated ");
        }
    }
    
   

    protected void setRealmService(RealmService realmService) {
        UserMgtUtil.setRealmService(realmService);
    }

    protected void unsetRealmService(RealmService realmService) {
        UserMgtUtil.setRealmService(null);
    }

    protected void setRegistryService(RegistryService registryService) {
        if (registryService != null && log.isDebugEnabled()) {
            log.debug("Registry service initialized");
        }
        UserMgtUtil.setRegistryService(registryService);
    }
    
    protected void unsetRegistryService(RegistryService registryService) {
        UserMgtUtil.setRegistryService(null);
    }
    protected void setConfigurationContextService(ConfigurationContextService configurationContextService){
        UserMgtUtil.setConfigurationContextService(configurationContextService);
    }
    protected void unsetConfigurationContextService(ConfigurationContextService configurationContextService){
        UserMgtUtil.setConfigurationContextService(null);
    }
  
}