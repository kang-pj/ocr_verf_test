package com.refine.common.code.service;

import java.util.List;
import java.util.Map;

public interface CodeService {
    List<Map<String, Object>> getCodeList(String codeType);
    String getCodeName(String codeType, String codeValue);
}