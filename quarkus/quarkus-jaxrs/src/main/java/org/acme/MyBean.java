package org.acme;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.UriInfo;
import jakarta.ws.rs.ext.Provider;

@Dependent
@Provider
public class MyBean {

	@Inject
	UriInfo uriInfo;

	public void foo() {
		System.out.println("foo");
		System.out.println(uriInfo.getAbsolutePath());
	}

}
