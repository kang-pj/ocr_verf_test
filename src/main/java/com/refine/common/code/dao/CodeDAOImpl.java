package com.refine.common.code.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CodeDAOImpl implements CodeDAO {

    @Autowired
    private SqlSession sqlSession;

    private static final String NAMESPACE = "com.refine.common.code.dao.CodeDAO";

    @Override
    public List<Map<String, Object>> getCodeList(String codeType) {
        return sqlSession.selectList(NAMESPACE + ".getCodeList", codeType);
    }

    @Override
    public String getCodeName(String codeType, String codeValue) {
        Map<String, String> params = new HashMap<>();
        params.put("codeType", codeType);
        params.put("codeValue", codeValue);
        return sqlSession.selectOne(NAMESPACE + ".getCodeName", params);
    }
}