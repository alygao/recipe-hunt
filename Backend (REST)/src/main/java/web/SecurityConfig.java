package web;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	public static final String DEF_USERS_BY_USERNAME_QUERY =
			"SELECT USERNAME, PASSWORD, ACTIVE AS ENABLED " + 
			"FROM MODERATORS " +
			"WHERE USERNAME = ?";
	public static final String DEF_AUTHORITIES_BY_USERNAME_QUERY =
			"SELECT USERNAME, CASE LEVEL WHEN 1 THEN 'ROLE_MOD' ELSE 'ROLE_ADM' END AS AUTHORITY " +
			"FROM MODERATORS " +
			"WHERE USERNAME = ?";

	@Autowired
	private DataSource dataSource;

//	@Autowired
//	private UserDetailsService userService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	@Autowired
	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("==========================================");
		System.out.println("DEF_USERS_BY_USERNAME_QUERY = " + DEF_USERS_BY_USERNAME_QUERY);
		System.out.println("DEF_AUTHORITIES_BY_USERNAME_QUERY = " + DEF_AUTHORITIES_BY_USERNAME_QUERY);
		
		auth
//		.inMemoryAuthentication()
//			.withUser("user").password("password").roles("USER").and()
//			.withUser("admin").password("password").roles("USER", "ADMIN");
		.jdbcAuthentication()
			.dataSource(this.dataSource)
			.usersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY)
			.authoritiesByUsernameQuery(DEF_AUTHORITIES_BY_USERNAME_QUERY)
			.passwordEncoder ( passwordEncoder() );
//		.userDetailsService( userService ).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new StandardPasswordEncoder( "53cr3t");
		return encoder;
	}
	
	public static void main(String[] args) {
		PasswordEncoder pe = new StandardPasswordEncoder( "53cr3t");
		System.out.println ( pe.encode( "123456" ) );
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
//				.antMatchers("/moderators/**").access("hasRole('ROLE_CUST')")
//				.antMatchers("/cart/**").access("hasRole('ROLE_MGR')")
				.antMatchers("/admin/**").access("hasRole('ROLE_ADM')")
				.antMatchers("/auth/**").authenticated()
//				.antMatchers("/auth/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/ingredients/**").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.DELETE, "/ingredients/**").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.PUT, "/meals/**").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.POST, "/meals").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.DELETE, "/meals/**").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.PUT, "/recipes/*").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.GET, "/recipes").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.DELETE, "/recipes/*").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.GET, "/recipes/unverified").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.GET, "/recipes/verified").permitAll()
				.antMatchers(HttpMethod.GET, "/recipes/*").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.DELETE, "/reviews/*").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.GET, "/reviews/unverified").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.GET, "/reviews/verified").permitAll()
				.antMatchers(HttpMethod.GET, "/reviews/*").access("hasRole('ROLE_MOD')")
				.antMatchers(HttpMethod.PUT, "/reviews/*").access("hasRole('ROLE_MOD')")
				
			
				
				
				
				.anyRequest().permitAll()
			.and()
				.httpBasic().realmName("recipe-hunt")
//			    .formLogin().loginPage("/LoginPage").loginProcessingUrl("/j_spring_security_check").failureUrl("/LoginPage?error").defaultSuccessUrl("/home")
//			    .usernameParameter("username").passwordParameter("password")
//			.and()
//			    .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/LoginPage?logout")
//			.and()
//				.exceptionHandling().accessDeniedPage("/403")
//			.and()
//				.rememberMe()
//				.tokenValiditySeconds(2419200)
//				.key("AlyssaWebStoreKey")
			.and()
				.csrf().disable()
			;
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
	        web.ignoring().antMatchers("/resources/**", "/index.html", "/login.html", "/partials/**", "/template/**", "/",
	                "/error/**");
	    }

}
