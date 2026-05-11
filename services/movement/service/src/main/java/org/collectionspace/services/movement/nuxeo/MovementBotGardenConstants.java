package org.collectionspace.services.movement.nuxeo;

import org.collectionspace.services.client.MovementClient;
import org.collectionspace.services.client.CollectionSpaceClient;

/**
 * Constants related to the official Botanical Garden profile.
 * @author remillet
 *
 */
public class MovementBotGardenConstants {    
	public static final String BOTGARDEN_PROFILE_NAME = MovementClient.BOTGARDEN_PROFILE_NAME;
	public static final String BOTGARDEN_SCHEMA_NAME = MovementClient.SERVICE_NAME + CollectionSpaceClient.PART_LABEL_SEPARATOR + BOTGARDEN_PROFILE_NAME;
	
	public static final String PREVIOUS_LOCATION_SCHEMA_NAME = BOTGARDEN_SCHEMA_NAME;
	public static final String PREVIOUS_LOCATION_FIELD_NAME = "previousLocation";

	public static final String ACTION_CODE_SCHEMA_NAME = MovementConstants.COMMON_SCHEMA_NAME;
	public static final String ACTION_CODE_FIELD_NAME = "reasonForMove";

	public static final String ACTION_DATE_SCHEMA_NAME = MovementConstants.COMMON_SCHEMA_NAME;
	public static final String ACTION_DATE_FIELD_NAME = "locationDate";

	// See CollectionSpace wiki documentation for "RefName" values
	// https://collectionspace.atlassian.net/wiki/spaces/DOC/pages/2754224504/RefName
	public static final String DEAD_ACTION_CODE = "urn:cspace:botgarden.cspace.berkeley.edu:vocabularies:name(actionCode):item:name(actCode00)";
	public static final String PLANTED_OUT_ACTION_CODE = "urn:cspace:botgarden.cspace.berkeley.edu:vocabularies:name(actionCode):item:name(actCode01)";
	public static final String MOVED_ACTION_CODE = "urn:cspace:botgarden.cspace.berkeley.edu:vocabularies:name(actionCode):item:name(actCode02)";
	public static final String REVIVED_ACTION_CODE = "urn:cspace:botgarden.cspace.berkeley.edu:vocabularies:name(actionCode):item:name(actCode06)";
	public static final String OTHER_ACTION_CODE = "urn:cspace:botgarden.cspace.berkeley.edu:vocabularies:name(actionCode):item:name(actCode05)";

	public static final String GEOREFERENCE_SCHEMA_NAME = BOTGARDEN_SCHEMA_NAME;
	public static final String DECIMAL_LATITUDE_FIELD_NAME = "decimalLatitude";
	public static final String DECIMAL_LONGITUDE_FIELD_NAME = "decimalLongitude";
	public static final String GEODETIC_DATUM_FIELD_NAME = "geodeticDatum";
	public static final String COORD_UNCERTAINTY_IN_METERS_FIELD_NAME = "coordUncertaintyInMeters";
	public static final String POINT_RADIUS_SPATIAL_FIT_FIELD_NAME = "pointRadiusSpatialFit";
	public static final String GEO_REFERENCED_BY_FIELD_NAME = "geoReferencedBy";
	public static final String GEO_REF_DATE_FIELD_NAME = "geoRefDate";
	public static final String GEO_REF_PROTOCOL_FIELD_NAME = "geoRefProtocol";
	public static final String GEO_REF_SOURCE_FIELD_NAME = "geoRefSource";
	public static final String GEO_REF_VERIFICATION_STATUS_FIELD_NAME = "geoRefVerificationStatus";
	public static final String GEO_REF_REMARKS_FIELD_NAME = "geoRefRemarks";
	public static final String GEO_REF_PLACE_NAME_FIELD_NAME = "geoRefPlaceName";

	public static final String LABEL_REQUESTED_SCHEMA_NAME = BOTGARDEN_SCHEMA_NAME;
	public static final String LABEL_REQUESTED_FIELD_NAME = "labelRequested";
	public static final String LABEL_REQUESTED_YES_VALUE = "Yes";
	public static final String LABEL_REQUESTED_NO_VALUE = "No";
}
