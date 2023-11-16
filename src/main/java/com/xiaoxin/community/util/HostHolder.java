package com.xiaoxin.community.util;

import com.xiaoxin.community.entity.User;
import org.springframework.stereotype.Component;

/**
 * 持有用户信息，代替session对象
 */
@Component
public class HostHolder {
    private ThreadLocal<User> users = new ThreadLocal<>();
    public void setUsers(User user){
        users.set(user);
    }
    public User getUser(){
        return users.get();
    }
    public void clear(){
        users.remove();
    }
}
