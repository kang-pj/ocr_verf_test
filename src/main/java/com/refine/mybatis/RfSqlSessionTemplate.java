package com.refine.mybatis;

import org.mybatis.spring.SqlSessionTemplate;
import org.apache.ibatis.session.SqlSessionFactory;

public class RfSqlSessionTemplate extends SqlSessionTemplate {
    public RfSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        super(sqlSessionFactory);
    }
}
