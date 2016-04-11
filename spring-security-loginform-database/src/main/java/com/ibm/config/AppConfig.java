package com.ibm.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
//import org.springframework.orm.hibernate3.HibernateTemplate;
//import org.springframework.orm.hibernate5.HibernateTemplate;
//import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;


@EnableWebMvc
@Configuration
@ComponentScan({ "com.ibm.*" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
public class AppConfig extends WebMvcConfigurerAdapter{
	
	@Value( "${jdbc.url}" ) private String dbUrl;
    @Value( "${jdbc.driverClassName}" ) private String dbDriverClassName;
    @Value( "${jdbc.username}" ) private String dbUsername;
    @Value( "${jdbc.password}" ) private String dbPassword;
	
    
    
    
    @Bean
    public static PropertyPlaceholderConfigurer properties(){
      PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
      ClassPathResource[] resources = new ClassPathResource[ ]
        { new ClassPathResource( "com/ibm/config/db.properties" ) };
      ppc.setLocations( resources );
      ppc.setIgnoreUnresolvablePlaceholders( true );
      return ppc;
    }
    
	@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder
        	.scanPackages("com.ibm")
            .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }
	
	private Properties getHibernateProperties() {
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
        prop.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        return prop;
    }
	
	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {
		
		BasicDataSource ds = new BasicDataSource();
		
	    ds.setDriverClassName(dbDriverClassName);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUsername);
		ds.setPassword(dbPassword);
		
		return ds;
	}
	
	@Bean
    public HibernateTransactionManager txManager() {
//        return new HibernateTemplate(sessionFactory());
        return new HibernateTransactionManager(sessionFactory());
    }
		
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver resolver=new CommonsMultipartResolver();
	    resolver.setDefaultEncoding("utf-8");
	    return resolver;
	}
	
}