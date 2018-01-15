package hawkingfoo.config;

import hawkingfoo.annotation.Auth;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            System.out.println("cat cast handler to HandlerMethod.class");
            return true;
        }
        // 获取注解
        Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);
        if (auth == null) {
            System.out.println("cant find @Auth in this uri:" + request.getRequestURI());
            return true;
        }
        // 从参数中取出用户身份并验证
        String admin = auth.user();
        if (!admin.equals(request.getParameter("user"))) {
            System.out.println("permission denied");
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
