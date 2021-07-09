package com.drt.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drt.entity.AdminExample;
import com.drt.entity.Goods;
import com.drt.entity.GoodsExample;
import com.drt.entity.Page;
import com.drt.mapper.GoodsMapper;
import com.drt.service.api.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class GoodsServiceImpl implements GoodsService {

	@Autowired
	private GoodsMapper goodsMapper;

	@Override
	public PageInfo<Goods> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword) {

		// 1.调用PageHelper的工具方法，开启分页功能
		PageHelper.startPage(pageNum, pageSize);

		// 2.执行分页查询
		List<Goods> list = goodsMapper.selectGoodsListByKeyword(keyword);

		// 3.将list封装到PageInfo对象中
		return new PageInfo<>(list);

	}

	@Override
	public void batchRemove(List<Integer> adminIdList) {
		GoodsExample goodsExample = new GoodsExample();

		goodsExample.createCriteria().andIdIn(adminIdList);

		goodsMapper.deleteByExample(goodsExample);
	}

	@Override
	public void saveGoods(Goods goods) {
		goods.setGoodsSales(0);
		goodsMapper.insert(goods);
	}

	@Override
	public Goods getGoodsById(Integer goodsId) {
		return goodsMapper.selectByPrimaryKey(goodsId);
	}
	
	@Override
	public void update(Goods goods, Integer goodsID) {
		goods.setGoodsSales(0);
		goods.setId(goodsID);
		goodsMapper.updateByPrimaryKey(goods);
	}

	@Override
	public List<Goods> queryGoodsForCategory(String food) {
		return goodsMapper.selectGoodsListByKeyword(food);
	}


}
