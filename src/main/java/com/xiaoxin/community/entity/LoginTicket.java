package com.xiaoxin.community.entity;

import lombok.Data;
import lombok.ToString;

import java.util.Date;
@Data
@ToString
public class LoginTicket {
    private int id;
    private int userId;
    private String ticket;
    private int status; // 0-有效; 1-无效;
    private Date expired;
}
