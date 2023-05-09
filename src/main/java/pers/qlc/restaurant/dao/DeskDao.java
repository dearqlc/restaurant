package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.Desk;

import java.util.List;

@Mapper
public interface DeskDao extends tk.mybatis.mapper.common.Mapper<Desk> {
    List<Desk> findPage(Desk desk);

    Desk findById(Integer deskId);

    Desk findByDeskCode(Desk desk);

    int insert(Desk desk);

    int update(Desk desk);

    int deleteByIds(List<Integer> idList);
}
