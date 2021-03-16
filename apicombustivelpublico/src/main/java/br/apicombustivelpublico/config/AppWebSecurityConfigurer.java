package br.apicombustivelpublico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CharacterEncodingFilter;

import br.apicombustivelpublico.repository.IUsuarioRepository;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter {


	@Autowired
	private AutenticacaoService autenticacaoService;

	@Autowired
	private ServicoToken tokenService;

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	// método para injetar configurações de login REST
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	// Configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	// configurações de autorização
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
        .formLogin().disable()
        .httpBasic().disable()
        .authorizeRequests().antMatchers(HttpMethod.GET, "/**").permitAll()
        .antMatchers(HttpMethod.POST, "/**").permitAll()
        .anyRequest().authenticated().and().cors().and().csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository),
		UsernamePasswordAuthenticationFilter.class);
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	      .ignoring()
	        .antMatchers("/**"); // configuração temporária para acessar paǵinas sem login
	}

	@Bean
	public FilterRegistrationBean<CharacterEncodingFilter> encodingFilterRegistration() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		FilterRegistrationBean<CharacterEncodingFilter> filterRegistrationBean = new FilterRegistrationBean<CharacterEncodingFilter>(
				encodingFilter);
		filterRegistrationBean.setName("CharacterEncodingFilter");
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.setAsyncSupported(true);
		return filterRegistrationBean;
	}
		
	
	
}
