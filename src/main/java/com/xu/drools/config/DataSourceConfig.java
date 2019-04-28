package com.xu.drools.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@MapperScan(basePackages = DataSourceConfig.PACKAGE, sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {
	static final String PACKAGE = "com.xu.drools.dao";

	@Primary
	@Bean(name = "DataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource DataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "SqlSessionFactory")
	@Primary
	public SqlSessionFactory dbSqlSessionFactory(@Qualifier("DataSource") DataSource DataSource) throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(DataSource);
		return sessionFactory.getObject();
	}
}