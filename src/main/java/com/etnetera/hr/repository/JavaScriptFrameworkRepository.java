package com.etnetera.hr.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.etnetera.hr.data.JavaScriptFramework;
import com.etnetera.hr.data.HypeLevel;


/**
 * Spring data repository interface used for accessing the data in database.
 * 
 * @author Etnetera
 *
 */
public interface JavaScriptFrameworkRepository extends CrudRepository<JavaScriptFramework, Long> {
    Iterable<JavaScriptFramework> findByNameStartingWith(String name);
    Iterable<JavaScriptFramework> findByHypeLevelEquals(HypeLevel hypeLevel);
    Iterable<JavaScriptFramework> findByNameStartingWithAndHypeLevelEquals(String name, HypeLevel hypeLevel);
}
