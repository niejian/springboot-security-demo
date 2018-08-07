package cn.com.demo.config;/**
 * Created by niejian on 2018/8/6.
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * @author niejian
 * @date 2018/8/6
 */
@Configuration
@PropertySource({
        "classpath:jdbc.properties"
})
//value = "cn.com.demo.dao.*.mapper*",
// basePackages 必须指定该数据源分配的包路径，意思就是再该路径下的所有数据操作都是再指定的数据源下面进行的
@MapperScan( basePackages = "cn.com.demo.dao.oa.*.mapper", sqlSessionTemplateRef = "oaSqlSessionTemplate")
public class DataSource2Config {
    @Value("${jdbc.url2}")
    private String url;
    @Value("${jdbc.username2}")
    private String username;
    @Value("${jdbc.password2}")
    private String password;
    @Value("${jdbc.driver}")
    private String driverClass;

    @Bean(name = "oaDataSource")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setUrl(url);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClass);
        return dataSource;
    }


    @Bean(name = "oaSqlSessionFactory")
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("oaDataSource") DruidDataSource dataSource) throws Exception{
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        mybatisSqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath:/cn/com/demo/dao/*/*/mapper/*/*Mapper.xml"));
//        List<Interceptor> interceptors = new ArrayList<>();
//        Interceptor[] interceptorsArr = new Interceptor[1];
//        interceptorsArr[0] = paginationInterceptor();
////        interceptors.add(paginationInterceptor());
//        mybatisSqlSessionFactoryBean.setPlugins(interceptorsArr);

        return mybatisSqlSessionFactoryBean;
    }

    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("oaDataSource") DruidDataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "oaSqlSessionTemplate")
    public SqlSessionTemplate yunSqlSessionTemplate(
            @Qualifier("oaSqlSessionFactory") MybatisSqlSessionFactoryBean oaSqlSessionFactory) throws Exception{

        return new SqlSessionTemplate(oaSqlSessionFactory.getObject());
    }
}
