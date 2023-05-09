package pers.qlc.restaurant.dao;

import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.SysMenu;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:31
 */
@Mapper
public interface SysMenuDao extends tk.mybatis.mapper.common.Mapper<SysMenu> {
    SysMenu findById(Long menuId);

    /**
     * 根据用户查询菜单
     *
     * @param sysMenu
     * @return
     */
    List<SysMenu> findList(SysMenu sysMenu);
}
