/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: AuthSuccessHandler
 * Author:   hzg
 * Date:     2019-12-27 12:28
 * Description: 登陆成功后的处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.day.config;

import com.day.pojo.User;
import com.day.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

/**
 * 〈一句话功能简述〉<br> 
 * 〈登陆成功后，按不同的角色进行跳转处理〉
 *
 * @author hzg
 * @create 2019-12-27
 * @since 1.0.0
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Resource
    UserRepository userRepository;

    @Autowired
    HttpSession session;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest arg0, HttpServletResponse arg1,
                                        Authentication authentication) throws IOException, ServletException {


        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails)principal).getUsername();
        User user=userRepository.findByUsername(username);
        session.setAttribute("user",user);

//User user= userRepository.findByUsername(authorities.)
        redirectStrategy.sendRedirect(arg0, arg1, "/index");

    }

}