package com.example.cart.order.services.ServImpl;

import com.example.cart.order.entities.DatabaseSequence;
import com.example.cart.order.entities.DatabaseSequence_two;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class OrderItemSequenceGeneratorServ {

    private MongoOperations mongoOperations;

    @Autowired
    public OrderItemSequenceGeneratorServ (MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public String generateSequence(String seqName) {
        Query query=new Query();
        DatabaseSequence_two counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence_two.class);
        System.out.println("database_2_service");
        return String.valueOf(!Objects.isNull(counter) ? counter.getSeq() : 1);
    }
}
