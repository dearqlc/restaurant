package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.SysRole;
import pers.qlc.restaurant.entity.SysUser;

import java.util.List;
import java.util.Set;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:32
 */
@Mapper
public interface SysRoleDao extends tk.mybatis.mapper.common.Mapper<SysRole> {
    Set<String> findRoleNamesByUser(SysUser sysUser);

    List<SysRole> findAll();
}

