package org.acme.client;

import io.smallrye.graphql.client.typesafe.api.GraphQLClientApi;
import org.acme.serverside.Address;
import org.acme.serverside.Person;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Source;

import java.util.List;

@GraphQLClientApi(configKey = "person-api")
public interface PersonAPI {

    List<Person> persons();

    Person getPerson(@Name("personId") int id);

    Address getAddressFromPerson(@Source Person person);

}
