package org.collectionspace.services.common.storage.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.collectionspace.services.audit.AuditCommon;
import org.collectionspace.services.client.PoxPayloadIn;
import org.collectionspace.services.client.PoxPayloadOut;
import org.collectionspace.services.common.context.ServiceContext;
import org.collectionspace.services.common.document.BadRequestException;
import org.collectionspace.services.common.document.DocumentException;
import org.collectionspace.services.common.document.DocumentHandler;
import org.collectionspace.services.common.document.DocumentNotFoundException;
import org.collectionspace.services.common.document.DocumentWrapper;
import org.collectionspace.services.common.document.DocumentWrapperImpl;
import org.collectionspace.services.common.document.TransactionException;
import org.collectionspace.services.common.document.DocumentHandler.Action;
import org.collectionspace.services.common.storage.StorageClient;
import org.collectionspace.services.common.vocabulary.RefNameServiceUtils.AuthorityItemSpecifier;
import org.collectionspace.services.lifecycle.TransitionDef;
import org.collectionspace.services.nuxeo.client.java.CoreSessionInterface;
import org.collectionspace.services.nuxeo.client.java.DocumentModelHandler;
import org.collectionspace.services.nuxeo.client.java.NuxeoDocumentException;
import org.collectionspace.services.nuxeo.client.java.NuxeoRepositoryClientImpl;
import org.collectionspace.services.nuxeo.util.NuxeoUtils;
import org.nuxeo.ecm.platform.audit.service.NXAuditEventsService;
import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.DocumentRef;
import org.nuxeo.ecm.core.api.NuxeoException;
import org.nuxeo.ecm.platform.audit.api.LogEntry;
import org.nuxeo.runtime.api.Framework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.nuxeo.elasticsearch.audit.ESAuditBackend;

public class ESAuditStorageClientImpl implements StorageClient {

    private final Logger logger = LoggerFactory.getLogger(ESAuditStorageClientImpl.class);

    protected String logException(Exception e, String msg) {
        String result = null;

        String exceptionMessage = e.getMessage();
        exceptionMessage = exceptionMessage != null ? exceptionMessage : "<No details provided>";
        result = msg = msg + ". Caught exception:" + exceptionMessage;

        if (logger.isTraceEnabled() == true) {
            logger.error(msg, e);
        } else {
            logger.error(msg);
        }

        return result;
    }

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

	private LogEntry getLogEntry(String id) throws DocumentNotFoundException {
		NXAuditEventsService audit = (NXAuditEventsService) Framework.getRuntime()
				.getComponent(NXAuditEventsService.NAME);

		ESAuditBackend esBackend = (ESAuditBackend) audit.getBackend();
		LogEntry logEntry = null;
		
		try {
			logEntry = esBackend.getLogEntryByID(Long.parseLong(id));
		} catch (NuxeoException e) {
			throw new DocumentNotFoundException(e);
		}
		
		return logEntry;
	}

	@Override
	public void get(ServiceContext ctx, String id, DocumentHandler handler)
			throws DocumentNotFoundException, DocumentException {

        if (handler == null) {
            throw new IllegalArgumentException(
                    "RepositoryJavaClient.get: handler is missing");
        }

        CoreSessionInterface repoSession = null;
        try {
            handler.prepare(Action.GET);
            LogEntry logEntry = null;
            try {
                logEntry = getLogEntry(id);
            } catch (org.nuxeo.ecm.core.api.DocumentNotFoundException ce) {
                String msg = logException(ce,
                		String.format("Could not find %s resource/record with ID=%s", ctx.getDocumentType(), id));
                throw new DocumentNotFoundException(msg, ce);
            }
            DocumentWrapper<LogEntry> wrapDoc = new DocumentWrapperImpl<LogEntry>(logEntry);
            handler.handle(Action.GET, wrapDoc);
            handler.complete(Action.GET, wrapDoc);
        } catch (IllegalArgumentException iae) {
            throw iae;
        } catch (DocumentException de) {
        	if (logger.isDebugEnabled()) {
        		logger.debug(de.getMessage(), de);
        	}
            throw de;
        } catch (Throwable e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Caught exception ", e);
            }
            throw new NuxeoDocumentException(e);
        }
    }


}
