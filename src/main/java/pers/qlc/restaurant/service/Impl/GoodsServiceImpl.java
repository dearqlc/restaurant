package pers.qlc.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qlc.restaurant.Enums.ResultEnum;
import pers.qlc.restaurant.dao.GoodsDao;
import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.Goods;
import pers.qlc.restaurant.exception.CustomException;
import pers.qlc.restaurant.service.GoodsService;
import pers.qlc.restaurant.utils.SplitIdsUtil;

import java.util.Date;
import java.util.List;

/**
 * 菜普管理
 */
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;

    /**
     * 分页查询以及分页条件查询
     *
     * @param pageInfo
     * @return
     */
    @Override
    public CustomPageInfo<Goods> findPage(CustomPageInfo<Goods> pageInfo) {
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        Goods goods = pageInfo.getT();
        if (goods.getGoodsCategory() != null) {
            if (goods.getGoodsCategory().getCategoryId() != null) {
                //新菜
                if (goods.getGoodsCategory().getCategoryId() == -2) {
                    goods.getGoodsCategory().setCategoryId(null);
                    goods.setTypeState(2);
                    //特色菜
                } else if (goods.getGoodsCategory().getCategoryId() == -1) {
                    goods.getGoodsCategory().setCategoryId(null);
                    goods.setTypeState(3);
                }
            }
        }
        //此处调用查询后，返回值会返回到page中
        goodsDao.findPage(goods);
        //采用构造方法创建，传入page会自动计算一些值
        return new CustomPageInfo<>(page);
    }

    /**
     * 根据goodsId查询
     *
     * @param goodsId
     * @return
     */
    @Override
    public Goods findById(Integer goodsId) {
        return goodsDao.findById(goodsId);
    }

    /**
     * 添加菜品
     *
     * @param goods
     */
    @Override
    public void insert(Goods goods) throws CustomException {
        Goods findGoods = goodsDao.findByGoodsName(goods);
        if (findGoods != null) {
            throw new CustomException(ResultEnum.GOODS_NAME_IS_EXIST);
        }
        goods.setCreateTime(new Date());
        goodsDao.insert(goods);
    }

    /**
     * 修改菜品
     *
     * @param goods
     */
    @Override
    public void update(Goods goods) {
        Goods findGoods = goodsDao.findByGoodsName(goods);
        if (findGoods != null) {
            throw new CustomException(ResultEnum.GOODS_NAME_IS_EXIST);
        }
        goods.setModifyTime(new Date());
        goodsDao.update(goods);
    }

    /**
     * 根据id删除菜品
     *
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
        List<Integer> idList = SplitIdsUtil.splitStrIdsByInt(strIds);
        int effect = goodsDao.deleteByIds(idList);
        if (effect <= 0) {
            throw new CustomException(ResultEnum.DEL_DB_FAIL);
        }
    }

}
