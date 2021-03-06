package com.taiji.jilin.service.impl;

import com.taiji.jilin.common.dto.ApplicationMemberDto;
import com.taiji.jilin.common.entity.Member;
import com.taiji.jilin.mapper.MemberMapper;
import com.taiji.jilin.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

/**
 * @author 单继重
 * @since 2017/9/15 19:26
 */

@Service(value = "memberService")
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberMapper memberMapper;

    public void newMember(Member member) throws Exception {
        memberMapper.newMember(member);
    }

    public int checkUsername(String username) throws Exception{
        return memberMapper.checkUsername(username);
    }

    public int queryIdByUsn(String username) throws Exception {
        return memberMapper.queryIdByUsn(username);
    }

    public Member queryById(int id) throws Exception {
        Member member = memberMapper.queryById(id);
        if (!(member.getBirth() == null || member.getBirth().equals(""))){
            int nowYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.parseInt(member.getBirth().substring(0,4));
            int age = nowYear - year;
            member.setAge(String.valueOf(age));
        }
        return member;
    }

    public List<Member> queryAll(int organization, int isAdmin, int status, int admin, int isJudge, int isCoach, int isInstructor, int grade) throws Exception {
        List<Member> list = memberMapper.queryAll(organization,isAdmin,status,admin,isJudge,isCoach,isInstructor,grade);
        for (int i = 0 ; i < list.size() ; i ++){
            Member member = list.get(i);
            if (!(member.getBirth() == null || member.getBirth().equals(""))){
                int nowYear = Calendar.getInstance().get(Calendar.YEAR);
                int year = Integer.parseInt(member.getBirth().substring(0,4));
                int age = nowYear - year;
                member.setAge(String.valueOf(age));
            }
            list.set(i,member);
        }
        return list;
    }

    public void delById(int id) throws Exception {
        memberMapper.delById(id);
    }

    public void update(Member member) throws Exception{
        memberMapper.update(member);
    }

    public Member queryByUsnAndPsw(Member member) throws Exception {
        Member member1 = memberMapper.queryByUsnAndPsw(member);
        if (member1 != null && member.getBirth() != null) {
            int nowYear = Calendar.getInstance().get(Calendar.YEAR);
            int year = Integer.parseInt(member1.getBirth().substring(0,4));
            int age = nowYear - year;
            member1.setAge(String.valueOf(age));
        }
        return member1;
    }

    public void audit(int[] ids) throws Exception {
        memberMapper.audit(ids);
    }

    public void unAudit(int[] ids) throws Exception {
        memberMapper.unAudit(ids);
    }

    public void updatePsw(Member member) throws Exception {
        memberMapper.updatePsw(member);
    }

    public List<ApplicationMemberDto> queryByOrganizationId(int organizationId, int activityId) throws Exception {
        List<ApplicationMemberDto> list = memberMapper.queryByOrganizationId(organizationId,activityId);
        for (int i = 0 ; i < list.size() ; i ++){
            ApplicationMemberDto member = list.get(i);
            if (!(member.getBirth() == null || member.getBirth().equals(""))){
                int nowYear = Calendar.getInstance().get(Calendar.YEAR);
                int year = Integer.parseInt(member.getBirth().substring(0,4));
                int age = nowYear - year;
                member.setAge(String.valueOf(age));
            }
            list.set(i,member);
        }
        return list;
    }
}
