package com.xiaoxin.community.dao;

import org.springframework.stereotype.Repository;

@Repository("alphaHibernate")
public class AlphaDAOImpl implements AlphaDAO{
    @Override
    public String select() {
        return "Hibernate";
    }
}
