package com.sec.schedule.service;

import com.sec.schedule.entity.SysUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by guoqingyun on 2018/1/3.
 */
public class SecurityCustomUserService implements UserDetailsService{
    private static Logger logger = LoggerFactory.getLogger(SecurityCustomUserService.class);
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUser user = new SysUser();
        user.setUsername("admin");
        
        if(s.equals("admin")) {
            logger.info("User logined:{}", user.getUsername());
            return user;
        } else {
            throw new UsernameNotFoundException("username or password is invalidate.");
        }
    }

}
