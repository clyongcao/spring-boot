package com.clyon.dao.elasticsearch;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.clyon.bean.elasticsearch.Student;

@Component
public interface StudentDao extends CrudRepository<Student, String> {

	
	
}
