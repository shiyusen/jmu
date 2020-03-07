package io.github.shiyusen.jmu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @author 石玉森
 * @create 2020-03-01 13:27
 **/

@Configuration
@MapperScan(basePackages = "io.github.shiyusen.jmu.business.mapper", sqlSessionTemplateRef = "jmuSqlSessionTemplate")
public class JmuDataSourceConfig {

    @Bean(name = "jmuDataSource")
    @ConfigurationProperties(prefix = "jmu.datasource")
    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
        return new DruidDataSource();
    }

    @Bean(name = "jmuSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("jmuDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:META-INF/mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "jmuTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("jmuDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "jmuSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("jmuSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}