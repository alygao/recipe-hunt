package web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "web", "services", "dao", "controllers" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
public class RootConfig {
	@Autowired
	Environment environment;
	
	 @Bean
	 @Autowired
	 public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		 return new JdbcTemplate(dataSource);
	 }
	 
	 @Bean
	 public DataSource dataSource() {
	  JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
	  dsLookup.setResourceRef(true);
//	  DataSource dataSource = dsLookup.getDataSource("jdbc/RecipeHuntDB");
	  String recipeHuntDBJndiName = this.environment.getProperty("RECIPE_HUNT_DB_JNDI_NAME");
	  System.out.println("recipeHuntDBJndiName = " + recipeHuntDBJndiName);
	  DataSource dataSource = dsLookup.getDataSource(recipeHuntDBJndiName);
	  return dataSource;
	 }

//	@Bean
//	public DataSource dataSource2() {
//		JndiObjectFactoryBean jndiObjectFactoryBean = new JndiObjectFactoryBean();
//		jndiObjectFactoryBean.setJndiName("java:/comp/env/jdbc/RecipeHuntDB");
//		jndiObjectFactoryBean.setResourceRef(true);
//		jndiObjectFactoryBean.setProxyInterface(javax.sql.DataSource.class);
//		return (DataSource) jndiObjectFactoryBean.getObject();
//	}

	//	@Bean
//	public DataSource dataSource2() {
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setUrl("jdbc:h2:tcp://dbserver/~/test");
//		dataSource.setDriverClassName("org.h2.Driver");
//		dataSource.setUsername("sa");
//		dataSource.setPassword("password");
//		dataSource.setInitialSize(20);
//		dataSource.setMaxIdle(30);
//		return dataSource;
//	}

}
