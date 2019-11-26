package com.clyon.dao.elasticsearch;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.clyon.bean.elasticsearch.SnackEsBean;

public interface SnackEsDao extends ElasticsearchRepository<SnackEsBean, Long> {
	
	List<SnackEsBean> findByName(String name);
	
	List<SnackEsBean> findByNameOrDescription(String name,String description);
	
	List<SnackEsBean> findByNameOrDescription(String name,String description, Pageable pageable);
	
}
