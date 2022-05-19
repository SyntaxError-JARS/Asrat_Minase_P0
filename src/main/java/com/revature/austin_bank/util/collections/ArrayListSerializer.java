package com.revature.austin_bank.util.collections;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.revature.austin_bank.models.Customer;

import java.io.IOException;

public class ArrayListSerializer extends StdSerializer<ArrayList> {

    public ArrayListSerializer() {
        this(null);
    }

    public ArrayListSerializer(Class<ArrayList> t) {
        super(t);
    }

    @Override
    public void serialize(ArrayList arrayList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        for(int i = 0; i < arrayList.size; i++) {
            Customer customer = (Customer) arrayList.get(i);
            jsonGenerator.writeStartObject();
            jsonGenerator.writeStringField("first_name", customer.getFirstName());
            jsonGenerator.writeStringField("last_name", customer.getLastName());
            jsonGenerator.writeStringField("dob", customer.getDob());
            jsonGenerator.writeStringField("ssn", customer.getSsn());
            jsonGenerator.writeStringField("username_u", customer.getUsername_u());
            jsonGenerator.writeStringField("password_p", customer.getPassword_p());
            jsonGenerator.writeStringField("email", customer.getEmail());
            jsonGenerator.writeEndObject();
        }

    }
}
