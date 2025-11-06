package com.refine.common.code.service;

import com.refine.common.code.dao.CodeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeDAO codeDAO;

    @Override
    public List<Map<String, Object>> getCodeList(String codeType) {
        return codeDAO.getCodeList(codeType);
    }

    @Override
    public String getCodeName(String codeType, String codeValue) {
        return codeDAO.getCodeName(codeType, codeValue);
    }
}