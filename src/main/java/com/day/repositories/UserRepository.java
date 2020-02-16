/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: CustomerRepository
 * Author:   hzg
 * Date:     2019-12-23 14:35
 * Description: 客户jpa示例
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.day.repositories;

import com.day.pojo.Report;
import com.day.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户的jpa接口〉
 *
 * @author hbr
 * @create 2019-12-23
 * @since 1.0.0
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String personName);

    User findByid(int id);
    List<User> findAllByRole(String role);
    @Modifying
    void deleteUserByid(int id);

    @Modifying
    @Query(value = "update User set classname=?1,email=?2,groupname=?3,name=?4,password=?5,phonenumber=?6,role=?7,sex=?8,username=?9 where id=?10")
    void updateStu(String classname,String email,String groupname,String name,String password,String phonenumber,String role,String sex, String username,int id);

    @Modifying
    @Query(value = "update User set email=?1,name=?2,password=?3,phonenumber=?4,role=?5,sex=?6,username=?7 where id=?8")
    void updateTea(String email,String name,String password,String phonenumber,String role,String sex, String username,int id);

    @Modifying
    @Query(value = "select name from User where role='ROLE_Student' or role='ROLE_GroupLeader'")
    List<User> nameStatu();

    @Modifying
    @Query(value = "select createdPerson from Report where createdAt=?1")
    List<User> ReportStatu(String createdAt);




}
