package com.phuag.sample.shiro;

/**
 * Created by Administrator on 2016/3/24 0024.
 */
import java.util.HashSet;
import java.util.Set;

import com.phuag.sample.model.Staff;
import com.phuag.sample.model.SysUser;
import com.phuag.sample.service.StaffService;
import com.phuag.sample.service.SysUserService;
import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    public SysUserService getSysUserService() {
        return sysUserService;
    }

    public void setSysUserService(SysUserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    @Autowired
    private SysUserService sysUserService;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        SysUser user = sysUserService.getSysuserByLoginName(username);
        if (user == null) {
            throw new UnknownAccountException();// 没找到帐号
        }
        return new SimpleAuthenticationInfo(user.getName(), // 用户名
                user.getPassword(), // 密码
                getName() // realm name
        );
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        String username = (String) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = sysUserService.getSysuserByLoginName(username);
//        Set<Role> uroles = user.getRoles();
        Set<String> perms = new HashSet<String>();
//        for (Role role : uroles) {
//            Set<Resource> resources = role.getResources();
//            for (Resource resource : resources) {
//                Object permission = resource.getPerms();
//                if (permission == null
//                        || StringUtils.isEmpty(permission.toString())) {
//                    continue;
//                }
//                perms.add(StringUtils.substringBetween(permission.toString(),
//                        "perms[", "]"));
//            }
//        }
        authorizationInfo.setStringPermissions(perms);
        return authorizationInfo;
    }
}