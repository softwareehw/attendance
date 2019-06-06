package tokenSec;

import tokenSec.VerifyTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) new VerifyTokenInterceptor())
                .addPathPatterns("/**")
                //登录接口不用通过拦截器否则会形成死循环，因为第一次登录没有token信息会一直跳转到登录接口
                .excludePathPatterns("/login/verify");
        super.addInterceptors(registry);
    }
}
