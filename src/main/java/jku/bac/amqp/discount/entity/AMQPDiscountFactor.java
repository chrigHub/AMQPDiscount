package jku.bac.amqp.discount.entity;

import org.springframework.data.annotation.Id;

public class AMQPDiscountFactor {

    @Id
    private String id;

    private String itemLabel;
    private int discount;

    public AMQPDiscountFactor() {}

    public AMQPDiscountFactor(String itemLabel, int discount){
        this.itemLabel = itemLabel;
        this.discount = discount;
    }

    @Override
    public String toString(){
        return String.format("Item[id=%s, label=%s, discount=%d]",id,itemLabel,discount);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
