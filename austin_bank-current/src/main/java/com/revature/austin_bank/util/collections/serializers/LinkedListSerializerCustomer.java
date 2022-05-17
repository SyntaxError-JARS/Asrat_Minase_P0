package com.revature.austin_bank.util.collections.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.austin_bank.models.Customer;

import java.io.IOException;
import java.util.LinkedList;

public class LinkedListSerializerCustomer extends StdSerializer<LinkedList> {

    public LinkedListSerializerCustomer() {
        this(null);
    }

    public LinkedListSerializerCustomer(Class<LinkedList> t) {
        super(t);
    }

    @Override
    public void serialize(LinkedList linkedList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for (int i = 0; i < linkedList.size(); i++) {
            Customer customer = (Customer) linkedList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("first_name", customer.getFirstName());
            jsonGenerator.writeStringField("llast_name", customer.getLastName());
            //jsonGenerator.writeStringField("dob", customer.getDob());
            jsonGenerator.writeStringField("ssn", customer.getSsn());
            jsonGenerator.writeStringField("username_u", customer.getUsername_u());
            jsonGenerator.writeStringField("password_p", customer.getPassword_p());
            jsonGenerator.writeStringField("email", customer.getEmail());
            jsonGenerator.writeEndObject();
        }

    }
}
