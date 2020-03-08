package io.github.shiyusen.jmu.business.test.controller;

import io.github.shiyusen.jmu.business.service.OperationLoggerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 石玉森
 * @create 2020-02-25 12:51
 **/
@RequestMapping("/jmu")
@RestController
public class JmuHomeController {


    @Autowired
    private OperationLoggerService operationLoggerService;

    @RequestMapping("/index")
    public String home() {
        return "hello welcome to jmu";
    }


    @RequestMapping("/mysql")
    public String mysql() {
         operationLoggerService.getOperationLogInfo(null);
        return "welcome jmu";
    }

}
