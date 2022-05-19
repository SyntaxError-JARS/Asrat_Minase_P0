package com.revature.austin_bank.daos;

import java.io.IOException;
import java.util.List;


public interface Genericable<T> {

    // Create
    T create(T newObject);

    // Read
    List<T> findAll() throws IOException;
   List<T> findById(String last_name);

    // Update
    int update(String last_name, String email);

    //Delete
    int delete (String id);

}

