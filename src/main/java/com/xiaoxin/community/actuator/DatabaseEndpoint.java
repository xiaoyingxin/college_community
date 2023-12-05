package com.xiaoxin.community.actuator;

import com.xiaoxin.community.util.CommunityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;

@Component
@Endpoint(id = "database")
public class DatabaseEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(DatabaseEndpoint.class);

    //尝试获取连接
    @Autowired
    private DataSource dataSource;

    @ReadOperation //只能通过get请求访问
    public String checkConnection(){
        try (
                Connection connection = dataSource.getConnection();
                ){
            return CommunityUtil.getJSONString(0,"获取连接成功!");
        }catch (Exception e){
            logger.error("获取连接失败",e.getMessage());
            return CommunityUtil.getJSONString(1,"获取连接失败!");
        }
    }

}
