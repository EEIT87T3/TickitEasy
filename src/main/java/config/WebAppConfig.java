package config;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

//相當於mvc-servlet.xml的Java程式組態
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"admin", "config", "cwdfunding", "event", "member", "order", "post", "product", "test", "util"})
public class WebAppConfig implements WebMvcConfigurer {
	
	@Bean
	public MappingJackson2JsonView jsonView() {
		MappingJackson2JsonView jv = new MappingJackson2JsonView();
		jv.setPrettyPrint(true);
		return jv;
	}
	
	@Bean
	public ContentNegotiatingViewResolver cnViewResolver() {
		ContentNegotiatingViewResolver cnv = new ContentNegotiatingViewResolver();
		ArrayList<View> lists = new ArrayList<View>();
		lists.add(jsonView());
		cnv.setDefaultViews(lists);
		return cnv;
	}
	
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/mycss/**").addResourceLocations("/WEB-INF/resources/mycss/");
		registry.addResourceHandler("/js/**").addResourceLocations("/WEB-INF/resources/js/");
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "membersmain.controller");
		registry.addViewController("/wonderland").setViewName("loginSystem");
	}

	@Bean
	public SessionLocaleResolver localeResolver() {
		return new SessionLocaleResolver();
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasename("i18n.message");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}
	
	@Bean
	public InternalResourceViewResolver irViewResolver() {
		InternalResourceViewResolver irv1 = new InternalResourceViewResolver("/WEB-INF/pages/",".jsp");
		irv1.setOrder(6);
		return irv1;
	}
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("locale");
		registry.addInterceptor(lci).addPathPatterns("/**");
	}

	
}
