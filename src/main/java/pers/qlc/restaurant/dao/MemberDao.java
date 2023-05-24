package pers.qlc.restaurant.dao;


import org.apache.ibatis.annotations.Mapper;
import pers.qlc.restaurant.entity.Member;

import java.util.List;

/**
 * Created with IDEA
 * author:QLC
 * Date:2018/10/1
 * Time:23:36
 */
@Mapper
public interface MemberDao {

    List<Member> findPage(Member condition);

    Member searchByPhone(Member member);

    Member searchByMemberCode(Member member);

    int insert(Member member);

    int deleteByIds(List<Long> idList);

    int update(Member member);

}
