package team.redrock.cheeringvote.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import team.redrock.cheeringvote.exception.ValidException;
import team.redrock.cheeringvote.pojo.response.ErrorResponse;

/**
 * @author 陌花采撷
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandlerAdvice {
    @ExceptionHandler(ValidException.class)
    public ErrorResponse handleException(ValidException e) {
        String msg =  e.getMessage();
        if(msg.equals("Fail to authorize")){
            return new ErrorResponse(416, e.getMessage());
        }
        if(msg.equals("Fail to get total cheerleaders")){
            return new ErrorResponse(417,e.getMessage());
        }
        if("Fail to get openid".equals(msg)){
            return new ErrorResponse(418, e.getMessage());
        }
        if(msg.equals("Index out of bound")){
            return new ErrorResponse(419, e.getMessage());
        }


        return  new ErrorResponse(415,e.getMessage());
    }
}