package com.refine.common.code.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface CodeDAO {
    List<Map<String, Object>> getCodeList(@Param("codeType") String codeType);
    String getCodeName(@Param("codeType") String codeType, @Param("codeValue") String codeValue);
}