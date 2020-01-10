package com.hendisantika.springbootelasticsearchsample.repository;

import com.hendisantika.springbootelasticsearchsample.domain.EmployeeDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-elasticsearch-sample
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/01/20
 * Time: 06.10
 */
public interface EmployeeDocumentRepository extends ElasticsearchRepository<EmployeeDocument, String> {
    List<EmployeeDocument> findByDocTitleEndsWith(String name);

    List<EmployeeDocument> findByDocTitleStartsWith(String name);

    List<EmployeeDocument> findByDocTypeEndsWith(String name);

    List<EmployeeDocument> findByDocTypeStartsWith(String name);
}
