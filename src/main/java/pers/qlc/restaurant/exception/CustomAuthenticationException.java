package pers.qlc.restaurant.exception;

import org.apache.shiro.authc.AuthenticationException;
import pers.qlc.restaurant.Enums.ResultEnum;

public class CustomAuthenticationException extends AuthenticationException {
    private Integer code;

    public CustomAuthenticationException(ResultEnum resultEnum) {
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
