package com.refine.login.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@JsonPropertyOrder()
@EqualsAndHashCode(callSuper = false)
public class BaseError extends BaseResponse<BaseError.NestedError> {

    @Data
    static class NestedError {

        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;

        NestedError(String code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    @SerializedName("error")
    @JsonProperty("error")
    private NestedError nestedError;

    public BaseError(String code, String message) {
        super.setStatus("fail");
        this.nestedError = new NestedError(code, message);
    }

}
