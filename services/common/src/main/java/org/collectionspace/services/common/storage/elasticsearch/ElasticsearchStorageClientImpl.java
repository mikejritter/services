package org.collectionspace.services.common.storage.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.collectionspace.services.client.PoxPayloadIn;
import org.collectionspace.services.client.PoxPayloadOut;
import org.collectionspace.services.common.context.ServiceContext;
import org.collectionspace.services.common.document.BadRequestException;
import org.collectionspace.services.common.document.DocumentException;
import org.collectionspace.services.common.document.DocumentHandler;
import org.collectionspace.services.common.document.DocumentNotFoundException;
import org.collectionspace.services.common.document.TransactionException;
import org.collectionspace.services.common.storage.StorageClient;
import org.collectionspace.services.common.vocabulary.RefNameServiceUtils.AuthorityItemSpecifier;
import org.collectionspace.services.lifecycle.TransitionDef;

import org.nuxeo.ecm.platform.audit.service.NXAuditEventsService;
import org.nuxeo.ecm.platform.audit.api.LogEntry;
import org.nuxeo.runtime.api.Framework;
import org.nuxeo.elasticsearch.audit.ESAuditBackend;

public class ElasticsearchStorageClientImpl implements StorageClient {

	@Override
	public String create(ServiceContext ctx, DocumentHandler handler) throws BadRequestException, DocumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(ServiceContext ctx, String id) throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteWithWhereClause(ServiceContext ctx, String whereClause, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delete(ServiceContext ctx, String id, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void get(ServiceContext ctx, String id, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		NXAuditEventsService audit = (NXAuditEventsService) Framework.getRuntime()
				.getComponent(NXAuditEventsService.NAME);

		ESAuditBackend esBackend = (ESAuditBackend) audit.getBackend();
		LogEntry logEntry = esBackend.getLogEntryByID(Long.parseLong(id));
		String comment = logEntry.getComment();
		String principal = logEntry.getPrincipalName();
	}

	@Override
	public void get(ServiceContext ctx, DocumentHandler handler) throws DocumentNotFoundException, DocumentException {
	}

	@Override
	public void get(ServiceContext ctx, List<String> csidList, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void getAll(ServiceContext ctx, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		throw new UnsupportedOperationException();
	}

	@Override
	public void getFiltered(ServiceContext ctx, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(ServiceContext ctx, String id, DocumentHandler handler)
			throws BadRequestException, DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doWorkflowTransition(ServiceContext ctx, String id, DocumentHandler handler,
			TransitionDef transitionDef) throws BadRequestException, DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean synchronize(ServiceContext ctx, Object specifier, DocumentHandler handler)
			throws DocumentNotFoundException, TransactionException, DocumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean synchronizeItem(ServiceContext ctx, AuthorityItemSpecifier itemSpecifier, DocumentHandler handler)
			throws DocumentNotFoundException, TransactionException, DocumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(ServiceContext ctx, Object entityFound, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void releaseRepositorySession(ServiceContext<PoxPayloadIn, PoxPayloadOut> ctx, Object repoSession)
			throws TransactionException {
		// TODO Auto-generated method stub

	}

}
