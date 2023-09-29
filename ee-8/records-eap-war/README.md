# records-rest-war

Works as expected with a class.

>  http://localhost:8080/records-rest-war-0.0.1-SNAPSHOT/class

Does not work with a record, only returns an empty object. 

>  http://localhost:8080/records-rest-war-0.0.1-SNAPSHOT/record

Does not work with json bind, no support for records. Deserializer returns an empty object. Serializer fails to create object.

>  http://localhost:8080/records-rest-war-0.0.1-SNAPSHOT/jsonb

