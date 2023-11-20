package com.xiaoxin.community.service;

import com.xiaoxin.community.dao.LoginTicketMapper;
import com.xiaoxin.community.dao.UserMapper;
import com.xiaoxin.community.entity.LoginTicket;
import com.xiaoxin.community.entity.User;
import com.xiaoxin.community.util.CommunityConstant;
import com.xiaoxin.community.util.CommunityUtil;
import com.xiaoxin.community.util.HostHolder;
import com.xiaoxin.community.util.MailClient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService implements CommunityConstant {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private HostHolder hostHolder;

    @Value("${community.path.domain}")
    private String domain;

    @Value("${server.servlet.context-path}")
    private String contextPath;

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    //注册判断user的合理性
    public Map<String, Object> register(User user){
        Map<String, Object> map = new HashMap<>();

        //空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        if (StringUtils.isBlank(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空");
            return map;
        }
        if (StringUtils.isBlank(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空");
            return map;
        }

        //验证账号
        User u = userMapper.selectByUsername(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "该账号已存在");
            return map;
        }

        //验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已被注册");
            return map;
        }

        //注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword()+user.getSalt()));
        user.setType(0);
        user.setStatus(0);
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", (int)(Math.random()*1000)));
        user.setCreateTime(new Date());
        userMapper.insertUser(user);

        //激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        //http://localhost:8080/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        return map;
    }

    //激活账号
    public int activation(int userId, String code) {
        User user = userMapper.selectById(userId);
        if (user.getStatus() == 1) {
            return ACTIVATION_REPEAT;
        }else if (user.getActivationCode().equals(code)) {
            userMapper.updateStatus(userId, 1);
            return ACTIVATION_SUCCESS;
        }else {
            return ACTIVATION_FAILURE;
        }
    }

    //登录业务
     public Map<String, Object> login(String username, String password, int expiredSeconds){
            Map<String, Object> map = new HashMap<>();

            //空值处理
            if (StringUtils.isBlank(username)) {
                map.put("usernameMsg", "账号不能为空!");
                return map;
            }
            if (StringUtils.isBlank(password)) {
                map.put("passwordMsg", "密码不能为空!");
                return map;
            }

            //验证账号
            User user = userMapper.selectByUsername(username);
            if (user == null) {
                map.put("usernameMsg", "该账号不存在!");
                return map;
            }

            //验证状态
            if (user.getStatus() == 0) {
                map.put("usernameMsg", "该账号未激活!");
                return map;
            }

            //验证密码
            password = CommunityUtil.md5(password + user.getSalt());
            if (!user.getPassword().equals(password)) {
                map.put("passwordMsg", "密码不正确!");
                return map;
            }

            //生成登录凭证
            LoginTicket loginTicket = new LoginTicket();
            loginTicket.setUserId(user.getId());
            loginTicket.setTicket(CommunityUtil.generateUUID());
            loginTicket.setStatus(0);
            loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
            loginTicketMapper.insertLoginTicket(loginTicket);

            map.put("ticket", loginTicket.getTicket());
            return map;
     }

     public void logout(String ticket) {
         loginTicketMapper.updateStatus(ticket, 1);
     }

     public LoginTicket findLoginTicket(String ticket){
        return loginTicketMapper.selectByTicket(ticket);
     }

    public void updateHeader(int id, String headerUrl) {
        userMapper.updateHeader(id, headerUrl);
    }

    public int updatePassword(int id, String password){
        return userMapper.updatePassword(id, password);
    }

    public Map<String, Object> changePassword(String oldPassword, String newPassword, String confirmPassword) {
        Map<String, Object> map = new HashMap<>();
        User user = hostHolder.getUser();
        //判断空值
        if (StringUtils.isBlank(oldPassword)){
            map.put("oldPasswordMsg","旧密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(newPassword)){
            map.put("newPasswordMsg","新密码不能为空!");
            return map;
        }
        if (StringUtils.isBlank(confirmPassword)){
            map.put("confirmPasswordMsg","确认密码不能为空!");
            return map;
        }
        //验证原始密码是否正确
        if (!CommunityUtil.md5(oldPassword+user.getSalt()).equals(user.getPassword())){
            map.put("oldPasswordMsg","密码不正确!");
            return map;
        }
        //验证确认密码是否等于新密码
        if (!newPassword.equals(confirmPassword)){
            map.put("confirmPasswordMsg","两次输入密码不相等!");
            return map;
        }
        //更新密码
        userMapper.updatePassword(user.getId(),CommunityUtil.md5(newPassword+user.getSalt()));
        return map;
    }

    public User findUserByName(String username){
        return userMapper.selectByUsername(username);
    }
}
