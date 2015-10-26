package com.nbh.mq;

import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.JMSC;

import javax.jms.*;
import java.util.StringTokenizer;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: Phuong Pham
 * Date: 14-Jul-2003
 * Time: 18:13:06
 * Description:
 */
public class FileToSTPQueue {

    private QueueSession session = null;
    private QueueConnection connection = null;
    private Queue queue = null;
    private QueueSender qr;

    private boolean closed = true;

    private String queueName;

    private File file;
    private static final String delimiter = "$";
    private Properties prop;

    /**
     * @param queueName
     *
     */
    public FileToSTPQueue(String queueName) throws Exception {
        this.queueName = queueName.trim();
        init();
    }


    public void init() throws Exception {
        QueueConnectionFactory qf = null;
        qf = getConnectionFactory();
        connection = qf.createQueueConnection();
        session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        queue = session.createQueue(queueName);
        qr = session.createSender(queue);
        String fileSep = System.getProperty("file.separator");
        prop = new Properties();
        prop.load(new FileInputStream("Y://Working//SwiftMessaging//BESSOUT//prop//mpref.txt"));

        file = new File(System.getProperty("user.dir")
                                  + fileSep
                                  + "swiftin"
                                  + fileSep
                                  + "SwiftMessage_multi.txt");

        int i = 0;
        i +=send(file);
        System.out.println("Total messages queued:" + i);
    }


    public void close() throws Exception {
        if (closed) {
            return;
        }
        qr.close();
        session.close();
        connection.close();
        queue = null;
        session = null;
        connection = null;
        closed = true;
    }


    /**
     * create an MQ connection factory
     * @return javax.jms.QueueConnectionFactory
     * @throws javax.jms.JMSException
     *
     */
    private QueueConnectionFactory getConnectionFactory() throws JMSException {
        MQQueueConnectionFactory mqFactory = null;
        mqFactory = new MQQueueConnectionFactory();
        mqFactory.setQueueManager("LONDON_RTDEV400");
        mqFactory.setHostName("RTDEV400");
        mqFactory.setChannel("BESS.CHANNEL");
        mqFactory.setTransportType(JMSC.MQJMS_TP_CLIENT_MQ_TCPIP);
        return mqFactory;
    }

    private int send(File f) throws Exception {
        int n = 0;
        FileInputStream in = new FileInputStream(f);
        byte[] bytes = new byte[in.available()];
        in.read(bytes, 0, bytes.length);
        String st = new String(bytes);
        StringTokenizer stn = new StringTokenizer(st, delimiter, false);
        System.out.println("Sending to " + queueName);
        connection.start();
        //int p = 1;

        while (stn.hasMoreTokens()) {
        SwiftSTPMessage m = new SwiftSTPMessage(stn.nextToken().getBytes());
        m.setProperty("mp.ref", prop.getProperty("P" + (n+1)));

        qr.send(session.createObjectMessage((m)));
        n++;
        //p++;
        }
        connection.stop();
        close();
        System.out.println("*************** DONE **************************");
        return n;

    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("supply queueName(s), comma separated");
            return;
        }
        StringTokenizer st = new StringTokenizer(args[0], ",");
        try {
            while (st.hasMoreTokens()) {
                new FileToSTPQueue(st.nextToken());

            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use Options | File Templates.
        }
    }


}
