package pers.qlc.restaurant.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.qlc.restaurant.Enums.ResultEnum;
import pers.qlc.restaurant.dao.MemberCategoryDao;
import pers.qlc.restaurant.dao.MemberDao;
import pers.qlc.restaurant.entity.CustomPageInfo;
import pers.qlc.restaurant.entity.Member;
import pers.qlc.restaurant.exception.CustomException;
import pers.qlc.restaurant.service.MemberService;
import pers.qlc.restaurant.utils.SplitIdsUtil;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private MemberCategoryDao memberCategoryDao;

    /**
     * 修改会员
     *
     * @param member
     */
    @Override
    public void update(Member member) {
        Member findMember = memberDao.searchByPhone(member);
        if (findMember != null) {
            throw new CustomException(ResultEnum.PHONE_IS_EXIST);
        }
        member.setModifyTime(new Date());
        int effect = memberDao.update(member);
    }

    /**
     * 添加会员
     *
     * @param member
     */
    @Override
    public void addMember(Member member) {
        Member findMember = memberDao.searchByMemberCode(member);
        if (findMember != null) {
            throw new CustomException(ResultEnum.USER_IS_EXIST);
        }
        findMember = memberDao.searchByPhone(member);
        if (findMember != null) {
            throw new CustomException(ResultEnum.PHONE_IS_EXIST);
        }
        member.setCreateTime(new Date());
        memberDao.insert(member);
    }

    /**
     * 多条件分页查询
     *
     * @param pageInfo
     * @return
     */
    @Override
    public CustomPageInfo<Member> findPage(CustomPageInfo<Member> pageInfo) {
        //PageHelper的分页参数
        Page page = PageHelper.startPage(pageInfo.getPageNum(), pageInfo.getPageSize());
        memberDao.findPage(pageInfo.getT());
        //采用构造方法创建对象,
        return new CustomPageInfo<>(page);
    }

    /**
     * 根据memberId查找会员
     *
     * @param member
     * @return
     */
    @Override
    public Member findMemberById(Member member) {
        List<Member> memberList = memberDao.findPage(member);
        return memberList.get(0);
    }

    /**
     * 根据会员号查询会员
     *
     * @param member
     * @return
     */
    @Override
    public Member findMemberByMemberCode(Member member) {
        return memberDao.searchByMemberCode(member);
    }

    /**
     * 根据id删除会员
     *
     * @param strIds
     */
    @Override
    public void deleteByIds(String strIds) {
        List<Long> idList = SplitIdsUtil.splitStrIds(strIds);
        int effect = memberDao.deleteByIds(idList);
        if (effect < 0) {
            throw new CustomException(ResultEnum.DEL_DB_FAIL);
        }
    }

}
