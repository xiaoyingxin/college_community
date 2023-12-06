package com.xiaoxin.community.dao;

import com.xiaoxin.community.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    List<Comment> selectCommentByEntity(int entityType, int entityId, int offset, int limit);

    int selectCountByEntity(int entityType, int entityId);

    int insertComment(Comment comment);

    Comment selectCommentById(int id);

    int selectUserCommentCount(int userId);

    List<Comment> selectUserComments(int userId, int offset, int limit);
}
