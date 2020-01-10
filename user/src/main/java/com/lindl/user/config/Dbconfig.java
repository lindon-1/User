package com.lindl.user.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.mysql.cj.jdbc.Driver;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @Description：
 * @Author: ldl
 * @CreateDate: 2019/12/30 15:02
 */
@Configuration
public class Dbconfig {

    @Bean(name = "druidDataSource")
    public DataSource druidDataSource() throws Exception{
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setPassword("123456");
        druidDataSource.setUsername("root");
        druidDataSource.setDriver(new Driver());
//        druidDataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        druidDataSource.setUrl("jdbc:mysql://localhost:3306/user?serverTimezone=UTC ");
        return druidDataSource;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(@Qualifier("druidDataSource") DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setPlugins(new ShowSqlntecptor());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml"));
        return sqlSessionFactoryBean.getObject();
    }
}
