package com.xiaoxin.community.dao;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class AlphaDAOByMybatisImpl implements AlphaDAO{
    @Override
    public String select() {
        return "Mybatis";
    }
}
