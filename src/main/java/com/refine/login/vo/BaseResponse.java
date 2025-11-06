package com.refine.login.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@JsonPropertyOrder()
public class BaseResponse <T>{

    @JsonProperty("status")
    private String status = "ok";

    @JsonProperty("value")
    private T value;

}
