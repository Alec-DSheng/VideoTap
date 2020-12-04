package org.nee.ny.video.recording.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @Author: alec
 * Description:
 * @date: 13:31 2020-11-10
 */
@Configuration
@MapperScan(basePackages = DataBasePoolConfiguration.PACKAGE,sqlSessionFactoryRef = "sqlSessionFactory")
public class DataBasePoolConfiguration {

    static final String PACKAGE = "org.nee.ny.video.recording.moudle";

    private final DataSourceProperties dataSourceProperties;

    private final DataBasePoolProperties dataBasePoolProperties;

    public DataBasePoolConfiguration(DataSourceProperties dataSourceProperties, DataBasePoolProperties dataBasePoolProperties) {
        this.dataSourceProperties = dataSourceProperties;
        this.dataBasePoolProperties = dataBasePoolProperties;
    }

    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUrl(dataSourceProperties.getUrl());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        //连接池配置
        dataSource.setMaxActive(dataBasePoolProperties.getMaxActive());
        dataSource.setMinIdle(dataBasePoolProperties.getMinIdle());
        dataSource.setInitialSize(dataBasePoolProperties.getInitialSize());
        dataSource.setMaxWait(dataBasePoolProperties.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(dataBasePoolProperties.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(dataBasePoolProperties.getMinEvictableIdleTimeMillis());
        dataSource.setTestWhileIdle(dataBasePoolProperties.getTestWhileIdle());
        dataSource.setTestOnBorrow(dataBasePoolProperties.getTestOnBorrow());
        dataSource.setTestOnReturn(dataBasePoolProperties.getTestOnReturn());

        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        return dataSource;
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory ds1SqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setVfs(SpringBootVFS.class);
        sessionFactoryBean.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        sessionFactoryBean.setTypeAliasesPackage( DataBasePoolConfiguration.PACKAGE);
        return sessionFactoryBean.getObject();
    }
}
