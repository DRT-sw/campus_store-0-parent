package com.drt.test;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.drt.entity.Goods;
import com.drt.mapper.GoodsMapper;
import com.drt.util.GoodsCategory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml" })
public class GoodsTest {

	@Autowired
	private GoodsMapper goodsMapper;

	@Test
	public void testMybatis() {

		Goods goods = new Goods(null, "aaa", BigDecimal.valueOf(10), 9999, 9, 
				"static/img/default.jpg",
				GoodsCategory.OTHER);
		goodsMapper.insert(goods);

	}
	
	@Test
	public void test() {
		
		for (int i = 1; i < 5; i++) {
			Goods goods = new Goods
					(null, "冰红茶"+i, BigDecimal.valueOf(10+i), i, i,  
							"img/product-7.jpg", GoodsCategory.DRINK);
			
			goodsMapper.insert(goods);
		}
		
	}
	
	@Test
	public void test2() {
		
		for (int i = 1; i < 5; i++) {
			Goods goods = new Goods
					(null, "荷花"+i, BigDecimal.valueOf(10+i), i, i,  
							"img/product-8.jpg", GoodsCategory.CIGARETTES);
			
			goodsMapper.insert(goods);
		}
		
	}
	
	@Test
	public void test4() {
		
		for (int i = 1; i < 100; i++) {
			Goods goods = new Goods
					(null, "日炎"+i, BigDecimal.valueOf(10+i), i, i,  
							"img/product-9.jpg", GoodsCategory.OTHER);
			
			goodsMapper.insert(goods);
		}
		
	}
	
	
	
	

	@Test
	public void testMybatis2() {

		Goods goods = new Goods(160, "aaa", BigDecimal.valueOf(10), 9999, 10, "static/img/default.jpg",
				GoodsCategory.FOOD);
		goodsMapper.updateByPrimaryKeySelective(goods);

	}
	
	
	
	
	@Test
	public void test3() {
		
		List<Goods> list = goodsMapper.selectGoodsListByKeyword(GoodsCategory.FOOD);
		for (Goods goods : list) {
			System.out.println(goods.toString());
		}
		
	}

}
