package com.wonders.xlab.unittest.sample.configuration;

import com.wonders.xlab.unittest.sample.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by wangqiang on 15/8/17.
 */
@Configuration
@ComponentScan("com.wonders.xlab.unittest.sample")
@EnableJpaRepositories("com.wonders.xlab.unittest.sample.**.repository")
@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    @Profile("production")
    @Bean(name = "dataSource")
    public DataSource productionDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql:///unit_test_sample?createDatabaseIfNotExist=true");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Profile("test")
    @Bean(name = "dataSource")
    public DataSource testDataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(org.h2.Driver.class);
        dataSource.setUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("com.wonders.xlab.unittest.sample.**.entity");

        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();

        String[] activeProfiles = env.getActiveProfiles();
        if ("production".equals(activeProfiles[0])) {
            jpaVendorAdapter.setDatabase(Database.MYSQL);
        } else if ("test".equals(activeProfiles[0])) {
            jpaVendorAdapter.setDatabase(Database.H2);
        }

        jpaVendorAdapter.setGenerateDdl(true);
        jpaVendorAdapter.setShowSql(true);

        Properties props = new Properties();
        props.put("hibernate.format_sql", true);
        factoryBean.setJpaProperties(props);

        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);

        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    public static void main(String[] args) {

        System.setProperty("spring.profiles.active", "production");
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = ctx.getBean(UserService.class);
        userService.addRolesToUser(1, 1L, 3L, 4L, 5L);
        userService.removeRolesFromUser(1, 1L, 3L, 4L, 5L, 8L);
    }

}
