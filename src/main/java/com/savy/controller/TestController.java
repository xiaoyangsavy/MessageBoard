package com.savy.controller;

import com.savy.model.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @RequestMapping(value = "/test/get")
    @ResponseBody
    public Test testFreemarker() {
        System.out.println("call /test/get");
        Test test = new Test();
        test.setValue("test");

        return test;
    }

}
