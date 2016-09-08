/**
* 
*/
package com.axa.microservices.odata.edm.providers.product;

import java.util.Arrays;
import java.util.List;

import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntitySet;
import org.apache.olingo.commons.api.data.ValueType;
import org.apache.olingo.commons.api.edm.EdmEntitySet;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeKind;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.core.data.EntityImpl;
import org.apache.olingo.commons.core.data.EntitySetImpl;
import org.apache.olingo.commons.core.data.PropertyImpl;
import org.apache.olingo.server.api.edm.provider.EntityType;
import org.apache.olingo.server.api.edm.provider.Property;
import org.apache.olingo.server.api.edm.provider.PropertyRef;
import org.apache.olingo.server.api.uri.UriInfo;
import org.apache.olingo.server.api.uri.UriResource;
import org.apache.olingo.server.api.uri.UriResourceEntitySet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.axa.microservices.odata.edm.providers.EntityProvider;
import com.axa.microservices.odata.persistence.model.Account;
import com.axa.microservices.odata.service.IAccountService;

/**
 * @author Rajesh Iyer
 *
 */
@Component
public class AccountEntityProvider implements EntityProvider {

	// Service Namespace
	public static final String NAMESPACE = "com.axa.model";

	private static final Logger logger = LoggerFactory.getLogger(AccountEntityProvider.class);

	// EDM Container
	public static final String CONTAINER_NAME = "Container";
	public static final FullQualifiedName CONTAINER = new FullQualifiedName(NAMESPACE, CONTAINER_NAME);

	// Entity Types Names
	public static final String ET_PRODUCT_NAME = "Account";

	public static final FullQualifiedName ET_PRODUCT_FQN = new FullQualifiedName(NAMESPACE, ET_PRODUCT_NAME);

	// Entity Set Names
	public static final String ES_PRODUCTS_NAME = "Accounts";

	@Autowired
	private IAccountService accountService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.axa.microservices.odata.edm.providers.EntityProvider#getEntityType()
	 */
	@Override
	public EntityType getEntityType() {
		// create EntityType properties
		Property id = new Property().setName("ID").setType(EdmPrimitiveTypeKind.Int32.getFullQualifiedName());
		Property name = new Property().setName("Name").setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());
		Property description = new Property().setName("Description")
				.setType(EdmPrimitiveTypeKind.String.getFullQualifiedName());

		// create PropertyRef for Key element
		PropertyRef propertyRef = new PropertyRef();
		propertyRef.setPropertyName("ID");

		// configure EntityType
		EntityType entityType = new EntityType();
		entityType.setName(ET_PRODUCT_NAME);
		entityType.setProperties(Arrays.asList(id, name, description));
		entityType.setKey(Arrays.asList(propertyRef));

		return entityType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.axa.microservices.odata.edm.providers.EntityProvider#getEntitySet
	 * (org.apache.olingo.server.api.uri.UriInfo)
	 */
	@Override
	public EntitySet getEntitySet(UriInfo uriInfo) {
		List<UriResource> resourcePaths = uriInfo.getUriResourceParts();

		UriResourceEntitySet uriResourceEntitySet = (UriResourceEntitySet) resourcePaths.get(0); // in
																									// our
																									// example,
																									// the
																									// first
																									// segment
																									// is
																									// the
																									// EntitySet

		EdmEntitySet edmEntitySet = uriResourceEntitySet.getEntitySet();

		EntitySet entitySet = getData(edmEntitySet);

		return entitySet;
	}

	/**
	 * Helper method for providing some sample data.
	 *
	 * @param edmEntitySet
	 *            for which the data is requested
	 * @return data of requested entity set
	 */
	private EntitySet getData(EdmEntitySet edmEntitySet) {

		EntitySet entitySet = new EntitySetImpl();

		List<Entity> entityList = entitySet.getEntities();
		logger.debug("Size of the entities:" + entityList.size());

		List<Account> accounts = accountService.findAll();
		
		logger.debug("The number of accounts from the database:" + accounts.size());

		for (Account account : accounts) {

			entityList.add(new EntityImpl()
					.addProperty(new PropertyImpl(null, "ID", ValueType.PRIMITIVE, account.getId()))
					.addProperty(new PropertyImpl(null, "Name", ValueType.PRIMITIVE, account.getName()))
					.addProperty(new PropertyImpl(null, "Description", ValueType.PRIMITIVE, account.getDescription())));
		}
		return entitySet;
	}
	

	@Override
	public String getEntitySetName() {
		return ES_PRODUCTS_NAME;
	}

	@Override
	public FullQualifiedName getFullyQualifiedName() {
		return ET_PRODUCT_FQN;
	}
}
