package org.god.jmu.business.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 石玉森
 * @create 2020-02-25 12:51
 **/
@RequestMapping("/jmu")
@RestController
public class JmuHomeController {


    @RequestMapping("/index")
    public String home() {
        return "hello welcome to jmu";
    }


    @RequestMapping("/mysql")
    public String mysql() {
        return null;
    }

}
