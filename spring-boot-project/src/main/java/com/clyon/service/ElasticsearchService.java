package com.clyon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.clyon.bean.elasticsearch.Student;
import com.clyon.dao.elasticsearch.StudentDao;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;

@Service
public class ElasticsearchService {

	@Autowired
	private StudentDao userDao;
	
	@Transactional(rollbackFor = Exception.class)
	public void add(Student dto) throws Exception {

		System.out.println(dto.getName());
		userDao.save(dto);
		try {
			
		} catch (Exception e) {
			throw new ServiceException(StatusCode.CODE_702.value(), StatusCode.CODE_702.remark());
		}

	}
	
}
