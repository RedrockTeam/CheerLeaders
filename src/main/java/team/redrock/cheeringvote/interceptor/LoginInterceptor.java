package team.redrock.cheeringvote.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import team.redrock.cheeringvote.exception.ValidException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author 陌花采撷
 */
@Slf4j
public class LoginInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ValidException {


        String path = request.getServletPath();

        String errorPath = ".*/(error).*";
        String resourcePath = ".*/(pic).*";
        if(path.matches(resourcePath)){
            return true;
        }
        if(path.matches(errorPath)){
            throw new ValidException("Internal Server Error");
        }

        return true;
    }

}

