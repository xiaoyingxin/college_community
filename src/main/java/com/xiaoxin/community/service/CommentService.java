package com.xiaoxin.community.service;

import com.xiaoxin.community.dao.CommentMapper;
import com.xiaoxin.community.entity.Comment;
import com.xiaoxin.community.util.SensitiveFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SensitiveFilter sensitiveFilter;

    @Autowired
    private DiscussPostService discussPostService;

    public List<Comment> findCommentsByEntity(int entityType, int entityId, int offset, int limit) {
        return commentMapper.selectCommentByEntity(entityType, entityId, offset, limit);
    }

    public int findCommentCount(int entityType, int entityId) {
        return commentMapper.selectCountByEntity(entityType, entityId);
    }

    public Comment findCommentById(int id) {
        return commentMapper.selectCommentById(id);
    }

    public int addComment(Comment comment) {
        if (comment == null) {
            throw new IllegalArgumentException("参数不能为空!");
        }
        // 过滤敏感词
        comment.setContent(sensitiveFilter.filter(comment.getContent()));
        // 添加评论
        int rows = commentMapper.insertComment(comment);
        // 更新帖子评论数量
        if (comment.getEntityType() == 1) {
            int count = commentMapper.selectCountByEntity(comment.getEntityType(), comment.getEntityId());
            discussPostService.updateCommentCount(comment.getEntityId(), count);
        }
        return rows;
    }
}
