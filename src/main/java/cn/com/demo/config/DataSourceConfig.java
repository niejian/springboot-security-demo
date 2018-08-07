package cn.com.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

/**
 * @author niejian
 * @date 2018/7/24
 */

@Configuration
@PropertySource({
        "classpath:jdbc.properties"
})
@MapperScan(basePackages = "cn.com.demo.dao.yun.*.mapper", sqlSessionTemplateRef = "yunSqlSessionTemplate")
public class DataSourceConfig {
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.driver}")
    private String driverClass;

    @Bean(name = "yunDataSource")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }

    /*
   * 分页插件，自动识别数据库类型
   * 多租户，请参考官网【插件扩展】
   */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean(name = "yunMybatisSqlSessionFactoryBean")
    public MybatisSqlSessionFactoryBean yunSqlSessionFactoryBean(@Qualifier("yunDataSource") DruidDataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/cn/com/demo/dao/*/*/mapper/*/*Mapper.xml"));

//        List<Interceptor> interceptors = new ArrayList<>();
        Interceptor[] interceptorsArr = new Interceptor[1];
        interceptorsArr[0] = paginationInterceptor();
//        interceptors.add(paginationInterceptor());
        mybatisSqlSessionFactoryBean.setPlugins(interceptorsArr);

        return mybatisSqlSessionFactoryBean;
    }



    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("yunDataSource") DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "yunSqlSessionTemplate")
    public SqlSessionTemplate yunSqlSessionTemplate(
            @Qualifier("yunMybatisSqlSessionFactoryBean") MybatisSqlSessionFactoryBean yunSqlSessionFactoryBean) throws Exception{

        return new SqlSessionTemplate(yunSqlSessionFactoryBean.getObject());
    }

    @Bean
    public JdbcDaoImpl securityJdbcDao() {
        JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
        jdbcDao.setDataSource(dataSource());

        return jdbcDao;

    }
}
