package pers.qlc.restaurant.dao;


import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.MemberCategory;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface MemberCategoryDao extends tk.mybatis.mapper.common.Mapper<MemberCategory> {

    List<MemberCategory> findAll();

    MemberCategory findById(Long id);

    int insert(MemberCategory memberCategory);

    int update(MemberCategory memberCategory);

    int deleteByIds(List<Long> idList);

    MemberCategory findByMcName(MemberCategory memberCategory);

}
