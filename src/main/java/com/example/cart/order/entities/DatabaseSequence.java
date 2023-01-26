package com.example.cart.order.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;

    public DatabaseSequence() {
        System.out.println("database_1_const");
    }

    public String getId() {

        System.out.println("database_1_get_id");
        return id;

    }

    public void setId(String id) {
        this.id = id;
        System.out.println("database_1_set");
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}