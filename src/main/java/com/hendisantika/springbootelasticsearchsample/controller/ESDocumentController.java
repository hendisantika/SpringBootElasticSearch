package com.hendisantika.springbootelasticsearchsample.controller;

import com.hendisantika.springbootelasticsearchsample.domain.EmployeeDocument;
import com.hendisantika.springbootelasticsearchsample.repository.EmployeeDocumentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/")
@RequiredArgsConstructor
public class ESDocumentController {

    private final EmployeeDocumentRepository employeeDocumentRepository;

    @GetMapping
    @Operation(
            summary = "Add New Employee Data",
            description = "Add New Employee Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public String SpringBootESExample() {
        return "Welcome to Spring Boot Elastic Search Example! " + new Date();
    }

    @DeleteMapping("/deleteAll")
    @Operation(
            summary = "Delete All Data",
            description = "Delete All Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public String deleteAllDocuments() {
        try {   //delete all documents from solr core
            employeeDocumentRepository.deleteAll();
            return "documents deleted successfully!";
        } catch (Exception e) {
            return "Failed to delete documents";
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete Data by ID",
            description = "Delete Data by ID.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public String deleteDocumentById(@PathVariable("id") String id) {
        try {   //delete all documents from solr core
            employeeDocumentRepository.deleteById(id);
            return "Document with " + id + " has been deleted successfully!";
        } catch (Exception e) {
            return "Failed to delete document with id " + id;
        }
    }

    @PutMapping("/update/{id}")
    @Operation(
            summary = "Update Data",
            description = "Update Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public String updateDocumentById(@PathVariable("id") String id, @RequestBody @Valid EmployeeDocument employeeDocumentReq) {
        EmployeeDocument employeeDocument = new EmployeeDocument();
        try {
            Optional<EmployeeDocument> optionalEmployeeDocument = employeeDocumentRepository.findById(id);
            if (optionalEmployeeDocument.isPresent()) {
                employeeDocument = optionalEmployeeDocument.get();
                employeeDocument.setDocType(employeeDocumentReq.getDocType());
                employeeDocument.setDocTitle(employeeDocumentReq.getDocTitle());
                employeeDocumentRepository.save(employeeDocument);
            }
            return "Document with ID : " + id + " has been updated successfully!";
        } catch (Exception e) {
            return "Failed to update document with id " + id;
        }
    }

    @GetMapping("/find/{id}")
    @Operation(
            summary = "Find Data",
            description = "Find Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public EmployeeDocument findDocumentById(@PathVariable("id") String id) {
        EmployeeDocument employeeDocument = new EmployeeDocument();
        Optional<EmployeeDocument> optionalEmployeeDocument = employeeDocumentRepository.findById(id);
        if (optionalEmployeeDocument.isPresent()) {
            employeeDocument = optionalEmployeeDocument.get();
        }

        return employeeDocument;
    }

    @GetMapping("/init")
    @Operation(
            summary = "Init Data",
            description = "Init Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
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
    @Operation(
            summary = "Add New Data",
            description = "Add New Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public EmployeeDocument saveNewDocument(@RequestBody @Valid EmployeeDocument employeeDoc) {
        //Store Documents
        return employeeDocumentRepository.save(employeeDoc);
    }

    @GetMapping("/getAll")
    @Operation(
            summary = "Get All Data",
            description = "Get All Data.",
            tags = {"Data"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    description = "Success",
                    responseCode = "200",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation =
                            String.class))
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Not found", responseCode = "404",
                    content = @Content),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(description = "Internal error", responseCode = "500"
                    , content = @Content)
    }
    )
    public List<EmployeeDocument> getAllDocs() {
        List<EmployeeDocument> documents = new ArrayList<>();
        // iterate all documents and add it to list
        for (EmployeeDocument doc : this.employeeDocumentRepository.findAll()) {
            documents.add(doc);
        }
        return documents;
    }
}
