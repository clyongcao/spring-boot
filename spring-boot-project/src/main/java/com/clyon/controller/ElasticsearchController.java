package com.clyon.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.bean.elasticsearch.Beverage;
import com.clyon.bean.elasticsearch.KeyValueDTO;
import com.clyon.bean.elasticsearch.SnackEsBean;
import com.clyon.bean.elasticsearch.createIndexDTO;
import com.clyon.common.RespData;
import com.clyon.dto.es.SnackDTO;
import com.clyon.service.ElasticsearchService;

@RestController
@RequestMapping(value = "/es/")
public class ElasticsearchController {

	@Autowired
	ElasticsearchService elasticsearchService;
	
	@RequestMapping("createIndex")
	public RespData createIndex(@RequestBody createIndexDTO dto) throws Exception {

		elasticsearchService.createIndex(dto);
		
		return RespData.success("保存成功");
	}
	
	@RequestMapping("setMappings")
	public RespData setMappings() throws Exception {

		elasticsearchService.setMappings();
		
		return RespData.success("保存成功");
	}
	
	@RequestMapping("addDocument")
	public RespData addDocument() throws Exception {

		elasticsearchService.addDocument();
		
		return RespData.success("保存成功");
	}
	
	@RequestMapping("addDocument2")
	public RespData addDocument2(@RequestBody Beverage beverages) throws Exception {

		elasticsearchService.addDocument2(beverages);
		
		return RespData.success("保存成功");
	}
	
	@RequestMapping("searchById")
	public RespData<List<Beverage>> searchById(@RequestBody KeyValueDTO dto) throws Exception {

		List<Beverage> beverages = elasticsearchService.searchById(dto);
		
		return RespData.success(beverages);
	}
	
	@RequestMapping("searchByTerm")
	public RespData<List<Beverage>> searchByTerm(@RequestBody KeyValueDTO dto) throws Exception {

		List<Beverage> beverages = elasticsearchService.searchByTerm(dto);
		
		return RespData.success(beverages);
	}
	
	@RequestMapping("searchByQueryStringByPage")
	public RespData<List<Beverage>> searchByQueryStringByPage(@RequestBody KeyValueDTO dto) throws Exception {

		List<Beverage> beverages = elasticsearchService.searchByQueryStringByPage(dto);
		
		return RespData.success(beverages);
	}
	
	@RequestMapping("sbdSetMappings")
	public RespData sbdSetMappings() throws Exception {

		elasticsearchService.sbdSetMappings();
		
		return RespData.success("创建成功");
	}
	
	@RequestMapping("sbdAddDocument")
	public RespData sbdAddDocument(@RequestBody SnackDTO dto) throws Exception {

		elasticsearchService.sbdAddDocument(dto);
		
		return RespData.success("保存成功");
	}
	
	@RequestMapping("sbdSearchAll")
	public RespData<List<SnackEsBean>> sbdSearchAll() throws Exception {

		List<SnackEsBean> snacks = elasticsearchService.sbdSearchAll();
		
		return RespData.success(snacks);
	}
	
	@RequestMapping("sbdSearchById")
	public RespData<SnackEsBean> sbdSearchById(@RequestBody KeyValueDTO dto) throws Exception {

		SnackEsBean snack = elasticsearchService.sbdSearchById(dto);
		
		return RespData.success(snack);
	}
	
	@RequestMapping("sbdSearchByStandard")
	public RespData<List<SnackEsBean>> sbdSearchByStandard(@RequestBody SnackDTO dto) throws Exception {

		List<SnackEsBean> snacks = elasticsearchService.sbdSearchByStandard(dto);
		
		return RespData.success(snacks);
	}
	
	@RequestMapping("sbdNativeSearch")
	public RespData<List<SnackEsBean>> sbdNativeSearch(@RequestBody SnackDTO dto) throws Exception {

		List<SnackEsBean> snacks = elasticsearchService.sbdNativeSearch(dto);
		
		return RespData.success(snacks);
	}
	
	
}