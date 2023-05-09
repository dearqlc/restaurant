package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.Goods;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface GoodsDao extends tk.mybatis.mapper.common.Mapper<Goods> {
    List<Goods> findPage(Goods goods);

    Goods findById(Integer goodsId);

    Goods findByGoodsName(Goods goods);

    List<Goods> findByGoodsIds(List<Integer> goodsIdList);

    int insert(Goods goods);

    int update(Goods goods);

    int deleteByIds(List<Integer> idList);
}
