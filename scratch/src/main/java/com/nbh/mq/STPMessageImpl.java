package com.nbh.mq;

import java.util.Properties;
import java.util.Enumeration;

/**
 * Created by IntelliJ IDEA.
 * User: 840947824
 * Date: 12-Jun-2003
 * Time: 16:56:12
 * This is what we send to/from the swift gateway: @todo needs to be finalised with the receiving end
 */
public class STPMessageImpl implements STPMessage {

    private byte[] payload;
    private short mType = 0;
    Properties props = new Properties();

    public STPMessageImpl(byte[] payload) {
        this.payload = payload;
    }

    /**
     * get all properties
     * @return the properties or null
     */
    public Properties getProperties() {
        return props;
    }

    /**
     * return the proeprty descriptorsd for this message
     * @return
     */
    public String getProperty(String key) {
        return props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return props.getProperty(key, defaultValue);
    }

    public void setProperty(String key, String value) {
        props.setProperty(key, value);
    }

    public byte[] getPayload() {
        return payload;
    }

    /**
     * set the payload on this message
     * @param payload the data to set
     */
    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    public short getMessageType() {
        return mType;
    }

    public String getSender() {
        return ""; //@todo return the BIC
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        Enumeration keys = props.keys();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            sb.append(key + ": " + props.getProperty(key));
            sb.append(System.getProperty("line.separator"));
        }
        if (payload != null) {
            sb.append(new String(payload));
        }
        else{
            sb.append("NULL payload");
        }
        return sb.toString();
    }

}
