package com.hendisantika.springbootelasticsearchsample.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/01/20
 * Time: 06.04
 */
@Document(indexName = "employee", type = "employee")
public class EmployeeDocument {
    @Id
    private String id;
    private String docType;
    private String docTitle;

    public EmployeeDocument() {
    }

    public EmployeeDocument(String id, String docType, String docTitle) {
        this.id = id;
        this.docTitle = docTitle;
        this.docType = docType;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id='" + id + '\'' +
                ", docType='" + docType + '\'' +
                ", docTitle='" + docTitle + '\'' +
                '}';
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getDocTitle() {
        return docTitle;
    }

    public void setDocTitle(String docTitle) {
        this.docTitle = docTitle;
    }
}
