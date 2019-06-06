package tokenSec;

import tokenSec.CookieUtil;
import tokenSec.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class VerifyTokenInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(VerifyTokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        try {
            String token = CookieUtil.get(request, "token").getValue();
            Long userId = JWTUtil.getUserId(token);
            logger.info("****userId = {}*****",userId);
            //todo 校验token是否合法，如果不合法拦截器做处理
        }catch (Exception e){
            //发生异常跳转到指定接口或做其他处理
            response.sendRedirect(request.getContextPath()+"/login/verify");
        }
        return true;
    }
}
