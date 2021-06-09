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
package org.collectionspace.services.audit.nuxeo;

import java.util.List;

import org.collectionspace.services.audit.AuditCommon;
import org.collectionspace.services.audit.AuditCommonList;
import org.collectionspace.services.common.api.RefName.RefNameInterface;
import org.collectionspace.services.common.context.ServiceContext;
import org.collectionspace.services.common.document.AbstractDocumentHandlerImpl;
import org.collectionspace.services.common.document.AbstractMultipartDocumentHandlerImpl;
import org.collectionspace.services.common.document.DocumentException;
import org.collectionspace.services.common.document.DocumentFilter;
import org.collectionspace.services.common.document.DocumentWrapper;
import org.collectionspace.services.lifecycle.Lifecycle;
import org.collectionspace.services.lifecycle.TransitionDef;
import org.collectionspace.services.nuxeo.client.java.NuxeoDocumentModelHandler;
import org.nuxeo.ecm.core.api.DocumentModel;

/** AuditDocumentModelHandler
 *  $LastChangedRevision$
 *  $LastChangedDate$
 */
public class AuditDocumentModelHandler
        extends AbstractDocumentHandlerImpl<AuditCommon, AuditCommonList, AuditCommon, AuditCommonList> {

	@Override
	public Lifecycle getLifecycle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Lifecycle getLifecycle(String serviceObjectName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleWorkflowTransition(ServiceContext ctx, DocumentWrapper<DocumentModel> wrapDoc,
			TransitionDef transitionDef) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuditCommonList extractPagingInfo(AuditCommonList theCommonList, DocumentWrapper<AuditCommonList> wrapDoc)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean supportsWorkflowStates() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected String getRefnameDisplayName(DocumentWrapper<AuditCommon> docWrapper) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected RefNameInterface getRefName(DocumentWrapper<AuditCommon> docWrapper, String tenantName,
			String serviceName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocumentFilter createDocumentFilter() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handleCreate(DocumentWrapper<AuditCommon> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleUpdate(DocumentWrapper<AuditCommon> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleGet(DocumentWrapper<AuditCommon> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleGetAll(DocumentWrapper<AuditCommonList> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuditCommon extractCommonPart(DocumentWrapper<AuditCommon> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void fillCommonPart(AuditCommon obj, DocumentWrapper<AuditCommon> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuditCommonList extractCommonPartList(DocumentWrapper<AuditCommonList> wrapDoc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AuditCommon getCommonPart() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommonPart(AuditCommon obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AuditCommonList getCommonPartList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCommonPartList(AuditCommonList obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getQProperty(String prop) throws DocumentException {
		// TODO Auto-generated method stub
		return null;
	}}

