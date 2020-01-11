package com.hendisantika.springbootelasticsearchsample.controller;

import com.hendisantika.springbootelasticsearchsample.domain.EmployeeDocument;
import com.hendisantika.springbootelasticsearchsample.repository.EmployeeDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

    @DeleteMapping("/deleteAll")
    public String deleteAllDocuments() {
        try {   //delete all documents from solr core
            employeeDocumentRepository.deleteAll();
            return "documents deleted successfully!";
        } catch (Exception e) {
            return "Failed to delete documents";
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDocumentById(@PathVariable("id") String id) {
        try {   //delete all documents from solr core
            employeeDocumentRepository.deleteById(id);
            return "Document with " + id + " has been deleted successfully!";
        } catch (Exception e) {
            return "Failed to delete document with id " + id;
        }
    }

    @GetMapping("/init")
    public String initDocuments() {
        //Init Store Documents
        employeeDocumentRepository.saveAll(Arrays.asList(new EmployeeDocument("1", "pdf", "Java Dev Zone"),
                new EmployeeDocument("2", "msg", "subject:reinvention"),
                new EmployeeDocument("3", "pdf", "Spring boot sessions"),
                new EmployeeDocument("4", "docx", "meeting agenda"),
                new EmployeeDocument("5", "docx", "Spring boot + Elastic Search")));
        return "5 documents saved!!!";
    }

    @PostMapping("/save")
    public EmployeeDocument saveNewDocument(@RequestBody @Valid EmployeeDocument employeeDoc) {
        //Store Documents
        return employeeDocumentRepository.save(employeeDoc);
    }

    @GetMapping("/getAll")
    public List<EmployeeDocument> getAllDocs() {
        List<EmployeeDocument> documents = new ArrayList<>();
        // iterate all documents and add it to list
        for (EmployeeDocument doc : this.employeeDocumentRepository.findAll()) {
            documents.add(doc);
        }
        return documents;
    }
}
