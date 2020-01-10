package com.hendisantika.springbootelasticsearchsample.controller;

import com.hendisantika.springbootelasticsearchsample.repository.EmployeeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/01/20
 * Time: 06.14
 */
@RestController
public class ESDocumentController {

    @Autowired
    private EmployeeDocumentRepository employeeDocumentRepository;

    @RequestMapping("/")
    public String SpringBootESExample() {
        return "Welcome to Spring Boot Elastic Search Example! " + new Date();
    }
}
