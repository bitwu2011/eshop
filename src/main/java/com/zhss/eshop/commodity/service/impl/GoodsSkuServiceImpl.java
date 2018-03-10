package com.zhss.eshop.commodity.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhss.eshop.Inventory.service.InventoryService;
import com.zhss.eshop.commodity.dao.CategoryPropertyRelationshipDAO;
import com.zhss.eshop.commodity.dao.GoodsSkuDAO;
import com.zhss.eshop.commodity.dao.GoodsSkuSalePropertyValueDAO;
import com.zhss.eshop.commodity.dao.PropertyDAO;
import com.zhss.eshop.commodity.domain.CategoryPropertyRelationshipDO;
import com.zhss.eshop.commodity.domain.GoodsSkuDO;
import com.zhss.eshop.commodity.domain.GoodsSkuDTO;
import com.zhss.eshop.commodity.domain.GoodsSkuSalePropertyValueDO;
import com.zhss.eshop.commodity.domain.GoodsSkuSalePropertyValueDTO;
import com.zhss.eshop.commodity.domain.PropertyDO;
import com.zhss.eshop.commodity.service.GoodsSkuService;
import com.zhss.eshop.common.util.DateProvider;

/**
 * 商品sku管理service组件
 * @author zhonghuashishan
 *
 */
@Service
@Transactional
public class GoodsSkuServiceImpl implements GoodsSkuService {

	/**
	 * 商品sku管理DAO组件
	 */
	@Autowired
	private GoodsSkuDAO goodsSkuDAO;
	/**
	 * 属性管理DAO组件
	 */
	@Autowired
	private PropertyDAO propertyDAO;
	/**
	 * 类目与属性关系管理DAO组件
	 */
	@Autowired
	private CategoryPropertyRelationshipDAO categoryPropertyRelationDAO;
	/**
	 * 日期辅助组件
	 */
	@Autowired
	private DateProvider dateProvider;
	/**
	 * 商品sku销售属性值管理DAO组件
	 */
	@Autowired
	private GoodsSkuSalePropertyValueDAO propertyValueDAO;
	/**
	 * 库存中心接口
	 */
	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * 批量新增商品sku
	 * @param goodsSku 商品sku
	 * @throws Exception
	 */
	public void batchSave(List<GoodsSkuDTO> goodsSkus) throws Exception {
		for(GoodsSkuDTO goodsSku : goodsSkus) {
			Long goodsSkuId = saveGoodsSku(goodsSku);
			batchSavePropertyValues(goodsSkuId, goodsSku.getPropertyValues());  
			inventoryService.setSaleStockQuantity(goodsSkuId, goodsSku.getSaleStockQuantity());
		}
	}
	
	/**
	 * 批量新增商品sku的销售属性值
	 * @param propertyValues
	 * @throws Exception
	 */
	private void batchSavePropertyValues(Long goodsSkuId, 
			List<GoodsSkuSalePropertyValueDTO> propertyValues) 
			throws Exception {
		for(GoodsSkuSalePropertyValueDTO propertyValue : propertyValues) {
			propertyValue.setGoodsSkuId(goodsSkuId); 
			propertyValue.setGmtCreate(dateProvider.getCurrentTime()); 
			propertyValue.setGmtModified(dateProvider.getCurrentTime()); 
			propertyValueDAO.save(propertyValue.clone(GoodsSkuSalePropertyValueDO.class));  
		}
	}
	
	/**
	 * 新增商品sku
	 * @param goodsSku 商品sku
	 * @throws Exception
	 */
	private Long saveGoodsSku(GoodsSkuDTO goodsSku) throws Exception {
		goodsSku.setSaleProperties(getSaleProperties(goodsSku.getPropertyValues()));  
		goodsSku.setGmtCreate(dateProvider.getCurrentTime()); 
		goodsSku.setGmtModified(dateProvider.getCurrentTime()); 
		return goodsSkuDAO.save(goodsSku.clone(GoodsSkuDO.class)); 
	}
	
	/**
	 * 获取销售属性
	 * @param propertyValues 销售属性值
	 * @return
	 * @throws Exception
	 */
	private String getSaleProperties(List<GoodsSkuSalePropertyValueDTO> propertyValues) 
			throws Exception {
		StringBuilder builder = new StringBuilder("");
		
		for(int i = 0; i < propertyValues.size(); i++) {
			CategoryPropertyRelationshipDO relation = categoryPropertyRelationDAO
					.getById(propertyValues.get(i).getRelationId());
			PropertyDO property = propertyDAO.getPropertyById(relation.getPropertyId());
			
			builder.append(property.getPropertyName() + ":" + 
					propertyValues.get(i).getPropertyValue());
			
			if(i < propertyValues.size() - 1) {
				builder.append(";"); 
			}
		}
		
		return builder.toString();
	}
	
}
