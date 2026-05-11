package org.collectionspace.services.listener.botgarden;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.collectionspace.services.client.workflow.WorkflowClient;
import org.collectionspace.services.common.api.RefNameUtils;
import org.collectionspace.services.movement.nuxeo.MovementBotGardenConstants;
import org.collectionspace.services.movement.nuxeo.MovementConstants;
import org.collectionspace.services.nuxeo.listener.AbstractCSEventSyncListenerImpl;

import org.nuxeo.ecm.core.api.DocumentModel;
import org.nuxeo.ecm.core.api.event.CoreEventConstants;
import org.nuxeo.ecm.core.api.event.DocumentEventTypes;
import org.nuxeo.ecm.core.event.Event;
import org.nuxeo.ecm.core.event.EventContext;
import org.nuxeo.ecm.core.event.impl.DocumentEventContext;

public class UpdateLocationListener extends AbstractCSEventSyncListenerImpl {
	private static final Logger logger = LoggerFactory.getLogger(UpdateLocationListener.class);

    @Override
	public boolean shouldHandleEvent(Event event) {
		EventContext ec = event.getContext();

    	if (ec instanceof DocumentEventContext) {
    		DocumentEventContext context = (DocumentEventContext) ec;
    		DocumentModel doc = context.getSourceDocument();

    		if (doc.getType().startsWith(MovementConstants.NUXEO_DOCTYPE) &&
    				!doc.isVersion() &&
    				!doc.isProxy() &&
    				!doc.getCurrentLifeCycleState().equals(WorkflowClient.WORKFLOWSTATE_DELETED)) {
    			return true;
    		}
    	}

    	return false;
    }
    
	/*
	 * Set the currentLocation and previousLocation fields in a Current Location record
	 * to appropriate values.
	 *
	 * <ul>
	 * <li>If the plant is dead, set currentLocation to none</li>
	 * <li>Set the previousLocation field to the previous value of the currentLocation field</li>
	 * <li>If the action code is Moved or Planted Out, clear the Garden Coordinate Information
	 *     (georeference) fields so that the garden location does not become out-of-sync with
	 *     the lat/longs</li>
	 * </ul>
	 */
	@Override
	public void handleCSEvent(Event event) {
		EventContext ec = event.getContext();
		DocumentEventContext context = (DocumentEventContext) ec;
		DocumentModel doc = context.getSourceDocument();

		String actionCode = (String) doc.getProperty(MovementBotGardenConstants.ACTION_CODE_SCHEMA_NAME,
				MovementBotGardenConstants.ACTION_CODE_FIELD_NAME);

		logger.debug("actionCode=" + actionCode);

		if (event.getName().equals(DocumentEventTypes.DOCUMENT_CREATED)) {
			/*
			 * Special case for a document that is created with an action code of dead.
			 * In this case, we'll set the currentLocation to none, and the previousLocation to
			 * the current value of currentLocation, since there isn't a previous value. To do
			 * this, we can simply save the document, which will cause the beforeDocumentModification
			 * event to fire, taking us into the other branch of this code, with the current document
			 * becoming the previous document.
			 */
			if (actionCode != null && RefNameUtils.doShortIDsMatch(actionCode, MovementBotGardenConstants.DEAD_ACTION_CODE)) {
				context.getCoreSession().saveDocument(doc);

				/*
				 *  The saveDocument call will have caused the document to be versioned via documentModified,
				 *  so we can skip the versioning that would normally happen on documentCreated.
				 */
				ec.setProperty(CreateVersionListener.SKIP_PROPERTY, true);
			}
			else if (actionCode != null && isGeoreferenceErasingActionCode(actionCode)) {
				/*
				 * If the document is created with an action code of Moved or Planted Out, clear the
				 * georeference fields. Save the document to persist the cleared values; this also
				 * causes versioning via documentModified, so skip the documentCreated versioning.
				 */
				clearGeoreferenceFields(doc);
				context.getCoreSession().saveDocument(doc);
				ec.setProperty(CreateVersionListener.SKIP_PROPERTY, true);
			}
		}
		else {
			if (actionCode != null && RefNameUtils.doShortIDsMatch(actionCode, MovementBotGardenConstants.DEAD_ACTION_CODE)) {
				doc.setProperty(MovementConstants.CURRENT_LOCATION_SCHEMA_NAME, MovementConstants.CURRENT_LOCATION_FIELD_NAME, MovementConstants.NONE_LOCATION);
			}
			else if (actionCode != null && isGeoreferenceErasingActionCode(actionCode)) {
				clearGeoreferenceFields(doc);
			}

			DocumentModel previousDoc = (DocumentModel) context.getProperty(CoreEventConstants.PREVIOUS_DOCUMENT_MODEL);
			String previousLocation = (String) previousDoc.getProperty(MovementConstants.CURRENT_LOCATION_SCHEMA_NAME, MovementConstants.CURRENT_LOCATION_FIELD_NAME);

			logger.debug("previousLocation=" + previousLocation);

			doc.setProperty(MovementBotGardenConstants.PREVIOUS_LOCATION_SCHEMA_NAME, MovementBotGardenConstants.PREVIOUS_LOCATION_FIELD_NAME, previousLocation);
		}
	}

	private boolean isGeoreferenceErasingActionCode(String actionCode) {
		return RefNameUtils.doShortIDsMatch(actionCode, MovementBotGardenConstants.MOVED_ACTION_CODE)
				|| RefNameUtils.doShortIDsMatch(actionCode, MovementBotGardenConstants.PLANTED_OUT_ACTION_CODE);
	}

	private void clearGeoreferenceFields(DocumentModel doc) {
		String schema = MovementBotGardenConstants.GEOREFERENCE_SCHEMA_NAME;
		doc.setProperty(schema, MovementBotGardenConstants.DECIMAL_LATITUDE_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.DECIMAL_LONGITUDE_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEODETIC_DATUM_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.COORD_UNCERTAINTY_IN_METERS_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.POINT_RADIUS_SPATIAL_FIT_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REFERENCED_BY_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_DATE_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_PROTOCOL_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_SOURCE_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_VERIFICATION_STATUS_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_REMARKS_FIELD_NAME, null);
		doc.setProperty(schema, MovementBotGardenConstants.GEO_REF_PLACE_NAME_FIELD_NAME, null);
	}
	
	@Override
	public Logger getLogger() {
		return logger;
	}
}