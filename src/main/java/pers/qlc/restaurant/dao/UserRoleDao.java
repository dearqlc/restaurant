package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.UserRole;

@Mapper
public interface UserRoleDao {
    int insert(UserRole userRole);

    int update(UserRole userRole);
}
