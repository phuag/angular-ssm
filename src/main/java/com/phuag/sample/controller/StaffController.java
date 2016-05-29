package com.phuag.sample.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phuag.sample.Constants;
import com.phuag.sample.model.Staff;
import com.phuag.sample.service.StaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2015/11/17 0017.
 */
@RestController
@RequestMapping(value = Constants.URI_API + Constants.URI_STAFF)
public class StaffController {
    private static final Logger log = LoggerFactory
            .getLogger(StaffController.class);

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Staff> getStaff(@PathVariable("id") int staffId) {
        log.debug("get staffInfo by id @" + staffId);
        Staff staff = staffService.getStaffById(staffId);
        log.debug("get staff name @" + staff.getStaffName());
        return new ResponseEntity<Staff>(staff, HttpStatus.OK);
    }

    //page stuff is config to controller layer,i wonder if it was the best way
    @RequestMapping(value = "", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<PageInfo<Staff>> getAllStaff(@PageableDefault(page = 0, size = 10, sort = "staffId", direction = Direction.DESC) Pageable page) {
        log.debug("get all staffInfo " );
        PageInfo<Staff> staff =  staffService.getAllStaff(page);
        log.debug("get allStaff, num:" + staff.getPageSize());
        return new ResponseEntity<PageInfo<Staff>>(staff, HttpStatus.OK);
    }


}
