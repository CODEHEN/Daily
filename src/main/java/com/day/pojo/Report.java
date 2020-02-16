/**
 * Copyright (C), 2019-2019, 软件卓越人才班
 * FileName: Report
 * Author:   hzg
 * Date:     2019-12-27 11:20
 * Description: 日报周报
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.day.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 〈一句话功能简述〉<br> 
 * 〈日报周报〉
 *
 * @author hzg
 * @create 2019-12-27
 * @since 1.0.0
 */
@Data
@Entity
@Table(name = "t_reports")
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long reportId;
    /**
     * 报告类型，日报或者周报
     */
    private Integer reportType;

    /**
     * 工作内容
     */
    private String workContent;
    /**
     * 遇到的困难
     */
    private String difficulty;
    /**
     * 解决方法
     */
    private String solution;
    /**
     * 心得体会
     */
    private String experience;
    /**
     * 后续计划
     */
    private String plan;

    private String createdAt;
    /**
     * 写日报的人归属的小组
     */
    private String createdGroup;

    /**
     * 写日报的人
     */
    private String createdPerson;

    /**
     * 写日报的人的id
     */
    private int createdId;

    private boolean readed;


}
