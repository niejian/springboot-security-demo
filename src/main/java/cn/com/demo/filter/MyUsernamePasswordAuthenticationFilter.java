package cn.com.demo.filter;

import net.sf.json.JSONObject;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义filter
 * 其中父类方法拦截login操作，并且制定login方法是POST的请求方式
 * public UsernamePasswordAuthenticationFilter() {
        super(new AntPathRequestMatcher("/login", "POST"));
    }

 * 拦截登录操作，获取该用户的权限信息
 *
 * @author niejian
 * @date 2018/7/26
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private DaoAuthenticationProvider daoAuthenticationProvider;

    // ~ Static fields/initializers
    // =====================================================================================

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;
    private String passwordParameter = SPRING_SECURITY_FORM_PASSWORD_KEY;
    private boolean postOnly = true;

    // ~ Constructors
    // ===================================================================================================

    public MyUsernamePasswordAuthenticationFilter() {

        super();
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>init MyUsernamePasswordAuthenticationFilter");
    }

    public MyUsernamePasswordAuthenticationFilter(DaoAuthenticationProvider daoAuthenticationProvider) {
        new AntPathRequestMatcher("/loginss", "POST");
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>init MyUsernamePasswordAuthenticationFilter");
    }

    /**
     * 父类的方法获取用户名，密码的方式是从request.getParameter中获得
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String param = this.getRequestBody(request);
        String userName = null;
        String password = null;
        if (!StringUtils.isEmpty(param)) {
            JSONObject paraJson = JSONObject.fromObject(param);
            userName = paraJson.getString("username");
            password = paraJson.getString("password");
        }

        if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(password)) {
            userName = userName.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    userName, password);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);
            List<AuthenticationProvider> providers = new ArrayList<>();
            //这里必须将注入的provider添加进来，不然会爆NOP
            providers.add(daoAuthenticationProvider);
            this.setAuthenticationManager(new ProviderManager(providers));
            //调用父类的验证方法
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        return null;
    }

//    protected void setDetails(HttpServletRequest request,
//                              UsernamePasswordAuthenticationToken authRequest) {
//        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//    }

    /**
     * 获取请求体中的内容
     * @param request
     * @return
     */
    private String getRequestBody(HttpServletRequest request) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder("");
        try
        {
            br = request.getReader();
            String str;
            while ((str = br.readLine()) != null)
            {
                sb.append(str);
            }
            br.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (null != br)
            {
                try
                {
                    br.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();

    }
}
