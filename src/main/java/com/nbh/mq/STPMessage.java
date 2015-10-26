package com.nbh.mq;

import java.io.Serializable;
import java.util.Properties;

/**
 * Created by IntelliJ IDEA.
 * User: 840947824
 * Date: 17-Jun-2003
 * Time: 15:46:29
 * To change this template use Options | File Templates.
 */
public interface STPMessage extends Serializable {

    /**
     * get all properties
     * @return the properties: this never return null but the returned properties may be empty
     */
    public Properties getProperties();
    /**
     * return the proeprty descriptorsd for this message
     * @return
     */
    public String getProperty(String key);
    /**
     * set a property
     * @param key
     * @param value
     */
    public void setProperty(String key, String value);
    /**
     * get the content of the message
     * @return a byte[] representeing the content - some message types (e.g. heartbeats) may return null
     */
    public byte[] getPayload();
    /**
     * set the payload on this message
     * @param payload the data to set
     */
    public void setPayload(byte[] payload);
}
