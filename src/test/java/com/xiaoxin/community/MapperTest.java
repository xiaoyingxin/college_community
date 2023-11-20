package com.xiaoxin.community;

import com.xiaoxin.community.dao.DiscussPostMapper;
import com.xiaoxin.community.dao.LoginTicketMapper;
import com.xiaoxin.community.dao.UserMapper;
import com.xiaoxin.community.entity.DiscussPost;
import com.xiaoxin.community.entity.LoginTicket;
import com.xiaoxin.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MapperTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Test
    public void testSelectUserById(){
        User user = userMapper.selectById(101);
        System.out.println(user);
    }

    @Test
    public void testSelectUserByName(){
        User user = userMapper.selectByUsername("liubei");
        System.out.println(user);
    }

    @Test
    public void testSelectUserByEmail() {
        User user = userMapper.selectByEmail("2761462860@qq.com");
        System.out.println(user);
    }

    @Test
    public void testUpdateUser() {
        int rows = userMapper.updateStatus(149, 1);
        System.out.println(rows);
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> discussPosts = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for (DiscussPost discussPost : discussPosts) {
            System.out.println(discussPost);
        }
    }

    @Test
    public void testSelectDiscussPostRows(){
        int rows = discussPostMapper.selectDiscussPostRows(149                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    );
        System.out.println(rows);
    }

    @Test
    public void testSelectLoginTicket(){
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("86d1f2f709034dbcab56f04844b2816e");
        System.out.println(loginTicket);
    }

    @Test
    public void testUpdateLoginTicket(){
        System.out.println(loginTicketMapper.updateStatus("86d1f2f709034dbcab56f04844b2816e", 0));
    }




}
