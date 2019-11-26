package com.clyon.service;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.clyon.bean.elasticsearch.Beverage;
import com.clyon.bean.elasticsearch.BeverageEsBean;
import com.clyon.bean.elasticsearch.KeyValueDTO;
import com.clyon.bean.elasticsearch.SnackEsBean;
import com.clyon.bean.elasticsearch.createIndexDTO;
import com.clyon.dao.elasticsearch.SnackEsDao;
import com.clyon.dto.es.SnackDTO;
import com.clyon.emus.StatusCode;
import com.clyon.exception.ServiceException;
import com.clyon.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ElasticsearchService {

	@Autowired
	private SnackEsDao snackEsDao;
	
	@Autowired
	ElasticsearchTemplate template;
	
	@Value("${elasticsearch.cluster-name}")
	private String clusterName;
	
	@Value("${elasticsearch.cluster-address}")
	private String inetAddress;
		
	@Transactional(rollbackFor = Exception.class)
	public void createIndex(createIndexDTO dto) throws Exception {
		
		TransportClient client = getClient();
				
		client.admin().indices().prepareCreate(dto.getName())
		.get();
		
		client.close();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void setMappings() throws Exception {
		
		TransportClient client = getClient();
				
		XContentBuilder builder = XContentFactory.jsonBuilder()
			.startObject()
				.startObject("beverages")
					.startObject("properties")
						.startObject("id")
							.field("type","long")
							.field("store",true)
						.endObject()
						.startObject("name")
							.field("type","text")
							.field("store",true)
							.field("analyzer","chinese")
						.endObject()
						.startObject("description")
						.field("type","text")
						.field("store",true)
						.field("analyzer","chinese")
						.endObject()
					.endObject()
				.endObject()
			.endObject();
		
		client.admin().indices()
			.preparePutMapping("jindex")
			.setType("beverages")
			.setSource(builder)
			.get();
					
		client.close();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addDocument() throws Exception {
		
		TransportClient client = getClient();
				
		XContentBuilder builder = XContentFactory.jsonBuilder()
			.startObject()
				.field("id",1)
				.field("name","新疆阿克苏冰糖心苹果")
				.field("description","阿克苏苹果又称加丽果，具有果面光滑细腻、色泽光亮、果肉细腻、果核透明、甘甜味厚、汁多无渣等特点。富含丰富的维生素C、纤维素、果胶等。而阿克苏苹果果核透明（俗称“糖心”）则是其区别于其他产地红富士苹果的显著标志。")
			.endObject();
						
		client.prepareIndex()
		.setIndex("jindex")
		.setType("beverages")
		.setId("1")
		.setSource(builder)
		.get();
					
		client.close();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void addDocument2(Beverage beverages) throws Exception {
		
		TransportClient client = getClient();
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonBeverages =  objectMapper.writeValueAsString(beverages);
		
		System.err.println(jsonBeverages);
										
		client.prepareIndex("jindex","beverages",String.valueOf(beverages.getId()))
		.setSource(jsonBeverages,XContentType.JSON)
		.get();
					
		client.close();
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<Beverage> searchById(KeyValueDTO dto) throws Exception {
		
		String[] ids = dto.getValue().split(",");
		
		TransportClient client = getClient();
		
		QueryBuilder queryBuilder = QueryBuilders.idsQuery().addIds(ids[0],ids[1]);
		
		SearchResponse response = client.prepareSearch("jindex")
				.setTypes("beverages")
				.setQuery(queryBuilder)
				.get();
												
		SearchHits searchHits = response.getHits();
		
		List<Beverage> beverages = analysisSearchHits(searchHits);
		
		client.close();

		return beverages;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<Beverage> searchByTerm(KeyValueDTO dto) throws Exception {
				
		TransportClient client = getClient();
		
		QueryBuilder queryBuilder = QueryBuilders.termQuery(dto.getKey(), dto.getValue());
		
		SearchResponse response = client.prepareSearch("jindex")
				.setTypes("beverages")
				.setQuery(queryBuilder)
				.get();
												
		SearchHits searchHits = response.getHits();
		
		List<Beverage> beverages = analysisSearchHits(searchHits);
		
		client.close();

		return beverages;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<Beverage> searchByQueryStringByPage(KeyValueDTO dto) throws Exception {
				
		TransportClient client = getClient();
		
		QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(dto.getValue()).defaultField(dto.getKey());
		
		SearchResponse response;
		if(dto.getPageNow() != null && dto.getPageSize() != null) {
			response = client.prepareSearch("jindex")
					.setTypes("beverages")
					.setQuery(queryBuilder)
					.setFrom(dto.getPageNow())
					.setSize(dto.getPageSize())
					.get();
		}else {
			response = client.prepareSearch("jindex")
					.setTypes("beverages")
					.setQuery(queryBuilder)
					.get();
		}
		
												
		SearchHits searchHits = response.getHits();
		
		List<Beverage> beverages = analysisSearchHits(searchHits);
		
		client.close();

		return beverages;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<Beverage> analysisSearchHits(SearchHits searchHits) throws Exception {
		
		long totalHits = searchHits.getTotalHits();
		System.out.println("共命中" + totalHits + "条记录");
		
		Iterator<SearchHit> iterators = searchHits.iterator();
		
		List<Beverage> beverages = new ArrayList<>();
		while (iterators.hasNext()) {
			SearchHit hits = iterators.next();
			Map<String, Object> res = hits.getSourceAsMap();
			Beverage beverage = new Beverage();
			beverage.setId((Integer)res.get("id"));
			beverage.setName((String)res.get("naem"));
			beverage.setDescription((String)res.get("description"));
			beverages.add(beverage);
		}
					
		return beverages;	
	}
	
	@Transactional(rollbackFor = Exception.class)
	public TransportClient getClient() throws Exception {
		
		Settings settings = Settings.builder()
				.put("cluster.name",clusterName)
				.build();
				
		TransportClient client = new PreBuiltTransportClient(settings);
		
		
		client.addTransportAddress(new TransportAddress(InetAddress.getByName(inetAddress),9300));		
		
		return client;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void sbdSetMappings() throws Exception {
		
		template.createIndex(SnackEsBean.class);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void sbdAddDocument(SnackDTO dto) throws Exception {
		
		SnackEsBean snack = new SnackEsBean(); 
		snack.setId(dto.getId());
		snack.setName(dto.getName());
		snack.setDescription(dto.getDescription());
		
		snackEsDao.save(snack);
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<SnackEsBean> sbdSearchAll() throws Exception {
		
		Iterable<SnackEsBean> iterable = snackEsDao.findAll();
		
		List<SnackEsBean> snacks = new ArrayList<>();
		for (SnackEsBean snack : iterable) {
			snacks.add(snack);
		}
		
		return snacks;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public SnackEsBean sbdSearchById(KeyValueDTO dto) throws Exception {
		
		Optional<SnackEsBean> optional = snackEsDao.findById(Long.valueOf(dto.getKey()));
		
		SnackEsBean snack = optional.get();
		
		return snack;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<SnackEsBean> sbdSearchByStandard(SnackDTO dto) throws Exception {
		
		List<SnackEsBean> snacks = new ArrayList<>();
		
		if(!StringUtil.isBlank(dto.getName()) && StringUtil.isBlank(dto.getDescription())) {
			snacks= snackEsDao.findByName(dto.getName());
			return snacks;
		} else if (!StringUtil.isBlank(dto.getName()) && !StringUtil.isBlank(dto.getDescription())&& dto.getId() != 0) {
			snacks = snackEsDao.findByNameOrDescription(dto.getName(), dto.getDescription());
			return snacks;
		} else if(dto.getId() == 0){
			Pageable pageable = PageRequest.of(dto.getPageNow(), dto.getPageSize());
			snacks = snackEsDao.findByNameOrDescription(dto.getName(), dto.getDescription(), pageable);
			return snacks;
		} else{
			return snacks;
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	public List<SnackEsBean> sbdNativeSearch(SnackDTO dto) throws Exception {
		
		List<SnackEsBean> snacks = new ArrayList<>();
		
		Pageable pageable = PageRequest.of(0, 2);
		
		NativeSearchQuery query = new NativeSearchQueryBuilder()
				.withQuery(QueryBuilders.queryStringQuery(dto.getName()).defaultField("name"))
				.withPageable(PageRequest.of(dto.getPageNow(), dto.getPageSize()))
				.build();

	    snacks = template.queryForList(query, SnackEsBean.class);
		
		return snacks;
	}
	
}
