package com.clyon.document;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
* @Description
* @author Caoxuyang 
* @date 2018年12月28日 下午2:44:42 
*/
public class CommonUse {
	
/** 分页查询
	
	PageData<MerchantListVO> mlVOPD = new PageData<>();
	
	StringBuilder sb = new StringBuilder("SELECT m.id,m.name,m.merchant_code merchantCode,m.contact_man contactMan, "
			+ " m.telephone,m.create_time createTime,m.create_type createType,m.state, "
			+ " p.audit_status FROM t_merchant m LEFT JOIN t_merchant_progress p ON "
			+ " p.merchant_id = m.id WHERE m.is_delete = 0 ");
	
	if(!StringUtil.isBlank(blDTO.getName())) {
		sb.append(" AND m.name LIKE concat('%',:name,'%') ");
	}
	
	Map<String, Object> params = new HashMap<>();

	PageData<MerchantVO> pData = merchantDao.findPageBySql(sb.toString(), params, MerchantVO.class,
			new PageData<MerchantVO>(page, pageSize), " ORDER BY create_time DESC");
			
	
	pData.setCurrentPage(pData.getCurrentPage());
	pData.setPageSize(pData.getPageSize());
	pData.setTotalRecord(pData.getTotalRecord());
	
*/
	
	
/** copyBean
    import org.springframework.beans.BeanUtils
	
	BeanUtils.copyProperties(source, target);
	
*/
	
/** findBeanBySql
	 * StringBuffer sql = new StringBuffer();
	sql.append("select * from t_dict_brand where id in ?1 order by sort");

	List<Brand> brands = merchantDao.findBeanBySql(sql.toString(), new Object[] { ids }, Brand.class);
*/

}
