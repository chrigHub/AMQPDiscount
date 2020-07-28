package jku.bac.amqp.discount;

import jku.bac.amqp.discount.service.AMQPDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AMQPDiscountEndpoint {
    @Autowired
    AMQPDiscountService amqpDiscountService;

    @GetMapping("/resetDB")
    public String resetDB() throws Exception {
        amqpDiscountService.insertItems();
        return "Discount database has been reset!";
    }
}
