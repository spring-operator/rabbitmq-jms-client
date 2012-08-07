package com.rabbitmq.jms.client;

import java.io.IOException;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.Topic;
import javax.jms.TopicPublisher;

import com.rabbitmq.jms.admin.RMQDestination;
import com.rabbitmq.jms.util.Util;

/**
 *
 */
public class RMQMessageProducer implements MessageProducer, QueueSender, TopicPublisher {

    private final RMQDestination destination;
    private final RMQSession session;
    private int deliveryMode;
    private boolean disableMessageID;
    private boolean disableMessageTimestamp;
    private int priority;
    private long ttl;

    public RMQMessageProducer(RMQSession session, RMQDestination destination) {
        this.session = session;
        this.destination = destination;
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {
        disableMessageID = value;
    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        return disableMessageID;
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {
        disableMessageTimestamp = value;
    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        return disableMessageTimestamp;
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {
        this.deliveryMode = deliveryMode;
    }

    @Override
    public int getDeliveryMode() throws JMSException {
        return deliveryMode;
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {
        priority = defaultPriority;
    }

    @Override
    public int getPriority() throws JMSException {
        return priority;
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {
        ttl = timeToLive;
    }

    @Override
    public long getTimeToLive() throws JMSException {
        return ttl;
    }

    @Override
    public Destination getDestination() throws JMSException {
        return this.destination;
    }

    @Override
    public void close() throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void send(Message message) throws JMSException {
        assert message instanceof RMQMessage;
        try {
            byte[] data = RMQMessage.toMessage((RMQMessage) message);
            destination.getSession().getChannel().basicPublish(destination.getExchangeName(), destination.getRoutingKey(), null, data);
        } catch (IOException x) {
            Util.util().handleException(x);
        }
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public Queue getQueue() throws JMSException {
        return destination;
    }

    @Override
    public void send(Queue queue, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void send(Queue queue, Message message) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public Topic getTopic() throws JMSException {
        return destination;
    }

    @Override
    public void publish(Message message) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void publish(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void publish(Topic topic, Message message) throws JMSException {
        // TODO Auto-generated method stub

    }

    @Override
    public void publish(Topic topic, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {
        // TODO Auto-generated method stub

    }

}
