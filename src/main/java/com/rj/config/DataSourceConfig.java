package com.rj.config;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.rj.common.DatabaseType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**DataSource、SqlSessionFactory、DataSourceTransactionManager
 * @author ruanjin
 * @since 2019/5/22 11:26
 */
@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSourceOne() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", env.getProperty("db1.jdbc.driverName"));
        properties.put("url", env.getProperty("db1.jdbc.url"));
        properties.put("username", env.getProperty("db1.jdbc.username"));
        properties.put("password", env.getProperty("db1.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(properties);
    }

    @Bean
    public DataSource dataSourceTwo() throws Exception {
        Properties properties = new Properties();
        properties.put("driverClassName", env.getProperty("db2.jdbc.driverName"));
        properties.put("url", env.getProperty("db2.jdbc.url"));
        properties.put("username", env.getProperty("db2.jdbc.username"));
        properties.put("password", env.getProperty("db2.jdbc.password"));
        return DruidDataSourceFactory.createDataSource(properties);
    }

    /**
     * @Nullable
     * private Map<Object, Object> targetDataSources;
     * @Nullable
     * private Object defaultTargetDataSource;
     *
     * resolvedDataSources这个数据结构是通过targetDataSources构建而来，存储的结构也是数据库标识和数据源的映射关系
     * @Nullable
     * private Map<Object, DataSource> resolvedDataSources;
     * @Nullable
     * private DataSource resolvedDefaultDataSource;
     */
    @Bean
    public DynamicDataSource dynamicDataSource(@Qualifier("dataSourceOne") DataSource dataSourceOne, @Qualifier("dataSourceTwo") DataSource dataSourceTwo){
        Map<Object, Object> targetDataSourceMap = new HashMap<>();
        targetDataSourceMap.put(DatabaseType.DATABASE_ONE, dataSourceOne);
        targetDataSourceMap.put(DatabaseType.DATABASE_TWO, dataSourceTwo);

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setTargetDataSources(targetDataSourceMap);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceOne);
        return dynamicDataSource;
    }

    /**
     * DataSource
     * TypeAliasesPackage
     * MapperLocations
     * ConfigLocation
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage(env.getProperty("mybatis.type-aliases-package"));
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(env.getProperty("mybatis.mapper-locations")));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * DataSource
     */
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
