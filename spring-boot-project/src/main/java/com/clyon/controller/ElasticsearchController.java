package com.clyon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.bean.elasticsearch.Student;
import com.clyon.common.RespData;
import com.clyon.service.ElasticsearchService;
import com.clyon.util.StringUtil;

@RestController
@RequestMapping(value = "/es")
public class ElasticsearchController {

	@Autowired
	ElasticsearchService elasticsearchService;
	
	@RequestMapping("/add")
	public RespData add(@RequestBody Student dto) throws Exception {

		elasticsearchService.add(dto);
		
		return RespData.success("保存成功");

	}
	
	
}