/*
 * Copyright (c) 2015 by Cisco Systems, Inc.
 * All rights reserved.
 */

package com.nbh.noxml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * http://localhost:8080/no-webxml/hello/greeting
 * @author nhardwic
 *
 */

@Controller
public class HelloController {

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    @ResponseBody
    public String showIndex() {
        return "Hello world";
    }
}
