package com.nbh.tutorials.jms;

import com.nbh.common.LineReader;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 854096955
 * Date: 17-Aug-2004
 * Time: 09:34:25
 * To change this template use Options | File Templates.
 */
public class QRetailer implements javax.jms.MessageListener{

    private javax.jms.TopicConnection tConnect;
    private javax.jms.QueueConnection qConnect;

    private javax.jms.TopicSession tSession;
    private javax.jms.QueueSession qSession;

  private javax.jms.QueueSender qSender;

    private javax.jms.Topic hotDealsTopic;
    private javax.jms.TopicSubscriber tSubscriber;
    private String uname;
    private String topic="Hot Deals Subscription";

    public QRetailer(String broker, String userName, String password){
        try{
            TopicConnectionFactory tFactory = null;
            QueueConnectionFactory qFactory = null;
            InitialContext jndi=null;
            uname=userName;

            Properties env = new Properties();
            jndi = new InitialContext(env);

            tFactory=(TopicConnectionFactory)jndi.lookup(broker);
            qFactory = (QueueConnectionFactory)jndi.lookup(broker);
            tConnect = tFactory.createTopicConnection(userName, password);
            qConnect = qFactory.createQueueConnection(userName, password);
            tConnect.setClientID(userName);
            qConnect.setClientID(userName);

            tSession=tConnect.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            qSession=qConnect.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            hotDealsTopic = (Topic) jndi.lookup("hot deals");

            tSubscriber = tSession.createDurableSubscriber(hotDealsTopic, topic);
            tSubscriber.setMessageListener(this);
            tConnect.start();

        }catch(JMSException jmse){

        } catch (NamingException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

    public void onMessage(Message message) {
        autoBuy(message);
    }

    public void autoBuy(Message message){
        StreamMessage strmMsg = (StreamMessage)message;
        try {
            String dealDesc = strmMsg.readString();
            String itemDesc = strmMsg.readString();
            float oldPrice = strmMsg.readFloat();
            float newPrice =strmMsg.readFloat();
            System.out.println("Received hot buy : "+dealDesc);

            if (newPrice==0 || oldPrice / newPrice>1.1){
                int count=(int)(Math.random()*(double)1000);
                System.out.println("buying "+count+" "+itemDesc);
                TextMessage txtMsg = tSession.createTextMessage();
                txtMsg.setText(count+" "+itemDesc);
                txtMsg.setIntProperty("QTY", count);

                txtMsg.setJMSCorrelationID(uname);
                Queue buyQueue =(Queue)message.getJMSReplyTo();
                qSender = qSession.createSender(buyQueue);
                qSender.send(txtMsg, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY, 1800000);
            }
            else
                System.out.println("not buying that  crap.");

        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }

    private void exit(String s){
        try {
            if (s!=null && s.equalsIgnoreCase("unsubscribe")){
                tSubscriber.close();
                tSession.unsubscribe(topic);
            }
            tConnect.close();
            qConnect.close();
        } catch (JMSException e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
        System.exit(0);
    }

    public static void main(String[] args) throws Exception{
        String broker, username, password;
        System.out.println("enter broker : ");
        broker = LineReader.getLineFromSystemIn();
        System.out.println("enter username : ");
        username =LineReader.getLineFromSystemIn();
        System.out.println("enter password : ");
        password=LineReader.getLineFromSystemIn();

        QRetailer retailer = new QRetailer(broker, username, password);
         while (true){
                String s = LineReader.getLineFromSystemIn();
                if (null==s || "unsubscribe".equalsIgnoreCase(s))
                    retailer.exit(s);
         }
    }
}
