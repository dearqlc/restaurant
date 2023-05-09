package pers.qlc.restaurant.exception;

import pers.qlc.restaurant.Enums.ResultEnum;

public class CustomException extends RuntimeException {
    private Integer code;

    public CustomException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
