package com.example.cart.order.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequence_two")
@Data
public class DatabaseSequence_two {

    @Id
    private String id;

    private long seq;

    public DatabaseSequence_two() {
        System.out.println("database_2_constructor");
    }

    public String getId() {

        System.out.println("database_2_get_id");
        return id;
    }

    public void setId(String id) {
        this.id = id;
        System.out.println("database_2_set");
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }
}
