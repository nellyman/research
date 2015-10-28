/*
  * Copyright (c) 2015 by Cisco Systems, Inc.
  * All rights reserved.
  */

package com.nbh.ldap;



/**
 * @author nhardwic
 *
 */
public class LDAPException extends RuntimeException {

    /**
     * 
     */
    public LDAPException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     */
    public LDAPException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param cause
     */
    public LDAPException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     */
    public LDAPException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public LDAPException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        // TODO Auto-generated constructor stub
    }

}
