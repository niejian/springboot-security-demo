package cn.com.demo.config;/**
 * Created by niejian on 2018/7/25.
 */

import cn.com.demo.filter.JwtAuthenticationTokenFilter;
import cn.com.demo.filter.MyUsernamePasswordAuthenticationFilter;
import cn.com.demo.service.impl.CustUserDetailService;
import cn.com.demo.service.impl.JWTUserDetailService;
import cn.com.demo.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author niejian
 * @date 2018/7/25
 */
//@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Bean
    public CustUserDetailService custUserDetailService(){
        return new CustUserDetailService();
    }

    @Autowired
    private JWTUserDetailService jwtUserDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(custUserDetailService()).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence rawPassword) {
//                return rawPassword.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                if (rawPassword.toString().equalsIgnoreCase(encodedPassword)) {
//                    return true;
//                }
//                return false;
//            }
//        });
//        authenticationManagerBuilder.userDetailsService(custUserDetailService())
//        .passwordEncoder(new BCryptPasswordEncoder());
        authenticationManagerBuilder.userDetailsService(jwtUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

//    @Bean
//    public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
//        return new JwtAuthenticationTokenFilter();
//    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();

    }


    /**
     *   1.首先当我们要自定义Spring Security的时候我们需要继承自WebSecurityConfigurerAdapter来完成，相关配置重写对应 方法即可。
         2.我们在这里注册CustomUserService的Bean，然后通过重写configure方法添加我们自定义的认证方式。
         3.在configure(HttpSecurity http)方法中，我们设置了登录页面，而且登录页面任何人都可以访问，然后设置了登录失败地址，也设置了注销请求，注销请求也是任何人都可以访问的。
         4.permitAll表示该请求任何人都可以访问，.anyRequest().authenticated(),表示其他的请求都必须要有权限认证。
         5.这里我们可以通过匹配器来匹配路径，比如antMatchers方法，假设我要管理员才可以访问admin文件夹下的内容，我可以这样来写：.antMatchers("/admin/**").hasRole("ROLE_ADMIN")，也可以设置admin文件夹下的文件可以有多个角色来访问，写法如下：.antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN","ROLE_USER")
         6.可以通过hasIpAddress来指定某一个ip可以访问该资源,假设只允许访问ip为210.210.210.210的请求获取admin下的资源，写法如下.antMatchers("/admin/**").hasIpAddress("210.210.210.210")
         7.更多的权限控制方式参看下表：
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(
                        "/login",
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
                // 对于获取token的rest api要允许匿名访问
                .antMatchers("/auth/**").permitAll()

                //.authorizeRequests().antMatchers("**/**").hasRole("admin")
                //除以上请求不需要拦截外，
                //.and()
                .anyRequest().authenticated()
                //.and()
                 //   .formLogin().loginPage("/login")
                    //设置默认登录成功跳转页面
                    //.defaultSuccessUrl("/index", true)
                    //.successHandler(myAuthenticationSuccessHandler)
//                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                .and()
                    .csrf() //CSRF(Cross-site request forgery)跨站请求伪造
                        .disable()
                ;
        // 禁用缓存
        http.headers().cacheControl();
        //默认是调用 内置的UsernamePasswordAuthenticationFilter，而它接收参数只是从类似get方式传递的参数。而requestBody似乎就
        // 获取不到。这个方法是将自定义的过滤器来替换掉security默认的过滤器的作用 --未集成SWT
         http.addFilterAt(new MyUsernamePasswordAuthenticationFilter(daoAuthenticationProvider()), UsernamePasswordAuthenticationFilter.class);

        //集成SWT
        //http.addFilterAt(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(custUserDetailService());
        return daoAuthenticationProvider;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Autowired//注意这个方法是注入的
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(custUserDetailService());
    }
}
