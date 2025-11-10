/**	
 * CollectionSpacePerformanceTest.java
 *
 * {Purpose of This Class}
 *
 * {Other Notes Relating to This Class (Optional)}
 *
 * $LastChangedBy: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 *
 * This document is a part of the source code and related artifacts
 * for CollectionSpace, an open source collections management system
 * for museums and related institutions:
 *
 * http://www.collectionspace.org
 * http://wiki.collectionspace.org
 *
 * Copyright © 2009 {Contributing Institution}
 *
 * Licensed under the Educational Community License (ECL), Version 2.0.
 * You may not use this file except in compliance with this License.
 *
 * You may obtain a copy of the ECL 2.0 License at
 * https://source.collectionspace.org/collection-space/LICENSE.txt
 */
package org.collectionspace.services.PerformanceTests.test;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.collectionspace.services.collectionobject.CollectionobjectsCommon;
import org.collectionspace.services.collectionobject.TitleGroup;
import org.collectionspace.services.collectionobject.TitleGroupList;

/**
 * The Class CollectionSpacePerformanceTests.
 */
public abstract class CollectionSpacePerformanceTest {

	protected final static String OBJECT_NUMBER = "objectNumber_";
	protected final static String OBJECT_TITLE = "objectTitle_";

	/*
	 * Package scoped methods.
	 */

	/**
	 * Fill collection object.
	 * 
	 * @param co the co
	 * @param identifier the identifier
	 */
	void fillCollectionObject(CollectionobjectsCommon co, String identifier) {
		fillCollectionObject(co, OBJECT_NUMBER + identifier, OBJECT_TITLE + identifier);
	}

	/**
	 * Fill collection object.
	 * 
	 * @param co the co
	 * @param objectNumber the object number
	 * @param title the object title
	 */
	void fillCollectionObject(CollectionobjectsCommon co, String objectNumber,
			String title) {
		co.setObjectNumber(objectNumber);
                TitleGroupList titleGroupList = new TitleGroupList();
                List<TitleGroup> titleGroups = titleGroupList.getTitleGroup();
                TitleGroup titleGroup = new TitleGroup();
                titleGroup.setTitle(title);
                titleGroups.add(titleGroup);
                co.setTitleGroupList(titleGroupList);
	}

	String extractId(Response res) {
		String result = null;

		MultivaluedMap mvm = res.getMetadata();
		String uri = (String) ((ArrayList) mvm.get("Location")).get(0);
		verbose("extractId:uri=" + uri);
		String[] segments = uri.split("/");
		result = segments[segments.length - 1];
		verbose("id=" + result);

		return result;
	}

	/**
	 * Verbose.
	 * 
	 * @param msg the msg
	 */
	void verbose(String msg) {
	}

}
