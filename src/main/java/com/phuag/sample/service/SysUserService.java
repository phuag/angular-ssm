package com.phuag.sample.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.phuag.sample.dao.StaffMapper;
import com.phuag.sample.dao.SysUserMapper;
import com.phuag.sample.model.Staff;
import com.phuag.sample.model.SysUser;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/3/27 0027.
 */
@Service
@Transactional
public class SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    public int insertSysUser(SysUser sysUser) {
        return sysUserMapper.insert(sysUser);
    }

    public SysUser getSysUserById(String id) {
        return sysUserMapper.selectByPrimaryKey(id);
    }
    public Page<SysUser> getAllSysUser(Pageable page) {
        PageHelper.startPage(page.getPageNumber(), page.getPageSize());
        //TODO need to add sort function ,but the pagerhelper is not support well.
        //PageHelper.orderBy("STAFF_NAME desc");
        return (Page<SysUser>) sysUserMapper.selectAll();
    }


    public SysUser getSysuserByLoginName(String username) {
        return sysUserMapper.getSysuserByLoginName(username);
    }
}
