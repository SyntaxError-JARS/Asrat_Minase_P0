package com.revature.banking_application_p0.daos;

import java.io.IOException;


public interface Genericable<T> {

    // Create
    T create(T newObject);

    // Read
    T[] findAll() throws IOException;
    T findById(String id);

    // Update
    boolean update(T updatedObj);

    //Delete
    boolean delete(String id);

}

