package com.xiaoxin.community.dao;

import com.xiaoxin.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 通过id查询用户
    public User selectById(int id);

    // 通过username查询用户
    public User selectByUsername(String username);

    // 通过email查询用户
    public User selectByEmail(String email);

    // 添加用户
    public int insertUser(User user);

    // 更新用户状态
    public int updateStatus(int id, int status);

    // 更新用户头像
    public int updateHeader(int id, String headerUrl);

    // 更新用户密码
    public int updatePassword(int id, String password);
}
