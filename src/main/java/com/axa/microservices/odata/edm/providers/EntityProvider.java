/**
 * 
 */
package com.axa.microservices.odata.edm.providers;


import org.apache.olingo.commons.api.data.EntitySet;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.server.api.edm.provider.EntityType;
import org.apache.olingo.server.api.uri.UriInfo;

/**
 * @author Rajesh Iyer
 *
 */
public interface EntityProvider {

	
	EntityType getEntityType();

	String getEntitySetName();
	
	EntitySet getEntitySet(UriInfo uriInfo);
	
	/**
	 * Gets the fully qualified name.
	 *
	 * @return the fully qualified name
	 */
	FullQualifiedName getFullyQualifiedName();
}
