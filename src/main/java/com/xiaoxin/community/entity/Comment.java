package com.xiaoxin.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Comment {

        private int id;
        private int userId; // 评论的人
        private int entityType; // 评论的类型
        private int entityId; // 帖子id
        private int targetId; // 评论的目标id
        private String content;
        private int status;
        private Date createTime;

}
