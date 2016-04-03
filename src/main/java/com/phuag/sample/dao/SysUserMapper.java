package com.phuag.sample.dao;

import com.phuag.sample.model.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository(value="sysUserMapper")
public interface SysUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);

    List<SysUser> selectAll();

    SysUser getSysuserByLoginName(String loginName);
}