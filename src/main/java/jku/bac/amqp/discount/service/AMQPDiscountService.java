package jku.bac.amqp.discount.service;

import jku.bac.amqp.discount.entity.AMQPDiscountFactor;
import jku.bac.amqp.discount.entity.TransferItem;
import jku.bac.amqp.discount.repo.AMQPDiscountRepo;
import org.apache.naming.TransactionRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class AMQPDiscountService {
    @Autowired
    AMQPDiscountRepo amqpDiscountRepo;

    public void insertItems() throws Exception {
        amqpDiscountRepo.deleteAll();
        amqpDiscountRepo.save(new AMQPDiscountFactor("Screw",0));
        amqpDiscountRepo.save(new AMQPDiscountFactor("Screwdriver",10));
        amqpDiscountRepo.save(new AMQPDiscountFactor("Drill", 50));
    }

    public List<TransferItem> calcDiscountFromList(List<TransferItem> itemList){
        for(int i = 0; i < itemList.size(); i++){
            TransferItem item = itemList.get(i);
            AMQPDiscountFactor discountItem = amqpDiscountRepo.findByItemLabel(item.getLabel());
            if(discountItem != null){
                item.setPrize(item.getPrize() - item.getPrize() * ((double)discountItem.getDiscount()/100));
            }
            itemList.set(i,item);
        }
        return itemList;
    }

    public void removeEntry (String label){
        AMQPDiscountFactor entry = amqpDiscountRepo.findByItemLabel(label);
        if(entry != null){
            amqpDiscountRepo.delete(entry);
        }
    }
}
