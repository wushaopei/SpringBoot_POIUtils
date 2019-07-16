package com.example.poiutis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName CeShi
 * @Description TODO
 * @Author wushaopei
 * @Date 2019/7/16 0:04
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/poiutis")
public class CeShi {
    private static Logger logger = LoggerFactory.getLogger(CeShi.class);

    @RequestMapping(value = "/poidaochu/getDataByWord", method = RequestMethod.GET)
    public Object getOpenInvoicePage(HttpServletRequest request){

        return "hello";
    }



}
