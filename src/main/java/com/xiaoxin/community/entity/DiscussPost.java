package com.xiaoxin.community.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type; // 0-普通; 1-置顶;
    private int status; // 0-正常; 1-精华; 2-拉黑;
    private Date createTime;
    private int commentCount;
    private double score;
}
