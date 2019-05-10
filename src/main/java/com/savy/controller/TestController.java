package com.savy.controller;

import com.savy.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/test/get")
    @ResponseBody
    public Test testGet() {
        System.out.println("call /test/get");
        Test test = new Test();
        test.setValue("test");

        return test;
    }


    @RequestMapping(value = "/test/submit")
    @ResponseBody
    public Test testSubmit() {
        System.out.println("call /test/submit");
        Test test = new Test();
        test.setValue("success");
        return test;
    }
    @RequestMapping(value = "/test/mess")
    @ResponseBody
    public void mess(@RequestParam(name = "messageId",required= false) int messageId){
        System.out.println("----------------------"+(messageId>0?1:2));

    }

}
