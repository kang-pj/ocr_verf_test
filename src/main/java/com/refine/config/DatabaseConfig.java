package com.refine.config;

import com.refine.mybatis.RfSqlSessionTemplate;
import com.refine.properties.RfDbProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Setter;
// import net.sf.log4jdbc.Log4jdbcProxyDataSource; // 임시 주석
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.naming.NamingException;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ConfigurationProperties
@EnableConfigurationProperties({RfDbProperties.class})
public class DatabaseConfig {
	protected static final Logger logger = LoggerFactory.getLogger(DatabaseConfig.class);
	
	@Autowired
	private RfDbProperties rfDbProperties;
	
	@Setter private String dbMode;
	
	@Bean(name="dataSource") 
	DataSource dataSource() throws NamingException, Exception  {
		// DataSource ds = new Log4jdbcProxyDataSource( new HikariDataSource(rfDbProperties.getHikariConfig()) ); // 임시 주석
		DataSource ds = new HikariDataSource(rfDbProperties.getHikariConfig());
		
		logger.debug("*** init BasicDataSource : {}", "data source");
		return ds;
	}

	/**
	 * #####################################################
	 * # Transaction setting
	 * #####################################################
	 */

	@Bean(name="tm") 
	PlatformTransactionManager tm(@Qualifier("dataSource") DataSource ds) {
		DataSourceTransactionManager tm = new DataSourceTransactionManager(ds);
		tm.setGlobalRollbackOnParticipationFailure(false);
    	logger.debug("*** init BasicDataSource : {}", "tm");
		return tm;
	}
	
	@Bean(name="sqlSessionFactory") 
	SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource ds) throws Exception {
		SqlSessionFactoryBean sfBean = new SqlSessionFactoryBean();
		sfBean.setDataSource(ds);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sfBean.setConfigLocation(resolver.getResource(rfDbProperties.getConfigLocation()));
		sfBean.setMapperLocations(resolver.getResources(rfDbProperties.getMapperLocations()));
		
		// TypeAliasesPackage를 지정하면 이 하위 디렉터리의 클래스들은 모두 myBatis Mapper XML에서 parameter type 이나 result Type으로 사용할 수 있다.
    	logger.debug("*** init BasicDataSource : {}", "sqlSessionFactory");

    	return sfBean.getObject();
	}
	
	@Bean(name = "sqlSessionRF") 
	SqlSessionTemplate sqlSessionRF(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSF) throws Exception {
    	logger.debug("*** init BasicDataSource : {}", "sqlSessionTemplate");
		return new RfSqlSessionTemplate(sqlSF);
	}
}
