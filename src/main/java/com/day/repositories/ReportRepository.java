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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 〈一句话功能简述〉<br> 
 * 〈日报jpa示例〉
 *
 * @author hbr
 * @create 2019-12-23
 * @since 1.0.0
 */
public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report> findAllByCreatedPersonEquals(String personName);

    List<Report> findAllByCreatedGroupEquals(String groupName);
//    List<Report> findAllByCreatedAt(String createAt);
    List<Report> findAllByCreatedAtAndCreatedGroup(String createAt,String createGroup);
    List<Report> findAllByCreatedGroup(String createGroup);


    @Modifying
    @Query(value = "update Report set readed=TRUE where reportId=?1")
    void setReaded(Long id);



}
