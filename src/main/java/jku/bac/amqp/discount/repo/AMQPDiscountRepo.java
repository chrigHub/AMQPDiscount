package jku.bac.amqp.discount.repo;

import jku.bac.amqp.discount.entity.AMQPDiscountFactor;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AMQPDiscountRepo extends MongoRepository<AMQPDiscountFactor, String> {
    public AMQPDiscountFactor findByItemLabel(String label);
}
