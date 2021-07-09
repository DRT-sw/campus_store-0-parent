package com.drt.service.api;

import java.util.List;

import com.drt.entity.Goods;
import com.drt.entity.Page;
import com.github.pagehelper.PageInfo;

public interface GoodsService {

	PageInfo<Goods> queryForKeywordSearch(Integer pageNum, Integer pageSize, String keyword);

	void batchRemove(List<Integer> adminIdList);

	void saveGoods(Goods goods);

	Goods getGoodsById(Integer goodsId);

	void update(Goods goods, Integer goodsID);

	List<Goods> queryGoodsForCategory(String food);

}
