package com.phuag.sample.dao;

import com.phuag.sample.model.Staff;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository(value="staffMapper")
public interface StaffMapper {
    int deleteByPrimaryKey(Integer staffId);

    int insert(Staff record);

    int insertSelective(Staff record);

    Staff selectByPrimaryKey(Integer staffId);

    int updateByPrimaryKeySelective(Staff record);

    int updateByPrimaryKey(Staff record);

    List<Staff> selectAll();
}