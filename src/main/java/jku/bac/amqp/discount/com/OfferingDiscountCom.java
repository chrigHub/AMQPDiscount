package jku.bac.amqp.discount.com;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jku.bac.amqp.discount.entity.TransferItem;
import jku.bac.amqp.discount.service.AMQPDiscountService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OfferingDiscountCom {
    @Autowired
    private AMQPDiscountService amqpDiscountService;


    @RabbitListener(queues = "offeringdiscount.rpc.req")
    public List<TransferItem> receive(ArrayList objArr){
        ObjectMapper mapper = new ObjectMapper();
        List<TransferItem> transferItemList = mapper.convertValue(objArr, new TypeReference<List<TransferItem>>() {});
        return amqpDiscountService.calcDiscountFromList(transferItemList);
    }

    @RabbitListener(queues = "offeringdiscount.sync")
    public void receive(String label){
        amqpDiscountService.removeEntry(label);
    }
}
