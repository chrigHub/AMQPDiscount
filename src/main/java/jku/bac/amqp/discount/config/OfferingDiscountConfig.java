package jku.bac.amqp.discount.config;

import jku.bac.amqp.discount.com.OfferingDiscountCom;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OfferingDiscountConfig {
    @Bean
    public Queue queue(){
        return new Queue("offeringdiscount.rpc.req");
    }
    @Bean
    public Queue syncQueue(){
        return new Queue("offeringdiscount.sync");
    }
    @Bean
    public DirectExchange exchange(){
        return new DirectExchange("offeringdiscount.rpc");
    }
    @Bean
    public Binding binding(DirectExchange exchange, Queue queue){
        return BindingBuilder.bind(queue)
                .to(exchange)
                .with("offeringdiscount");
    }
    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
    @Bean
    OfferingDiscountCom offeringDiscountCom(){
        return new OfferingDiscountCom();
    }
}
