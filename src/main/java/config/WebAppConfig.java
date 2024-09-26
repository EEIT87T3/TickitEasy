package config;

import java.io.File;
import java.util.ArrayList;

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
    public String uploadDirectory() {
        String uploadDir = System.getProperty("user.home") + File.separator + "TickitEasy" + File.separator + "uploads";
        File dir = new File(uploadDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return uploadDir;
    }
	
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
		registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
		registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
		registry.addResourceHandler("/mycss/**").addResourceLocations("/WEB-INF/resources/mycss/");
		registry.addResourceHandler("/jslib/**").addResourceLocations("/WEB-INF/resources/jslib/");
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/resources/").addResourceLocations("/");
		
		// [cwdfunding]訪問/cwdfunding/images/靜態資源
	    registry.addResourceHandler("/cwdfunding/images/**").addResourceLocations("/cwdfunding/images/");		
	    
		// 繞行、取得在 WEB-INF 底下的靜態資源
		registry.addResourceHandler("/**").addResourceLocations("/WEB-INF/pages/");
		registry.addResourceHandler("/admin/**").addResourceLocations("/WEB-INF/pages/admin/");
        registry.addResourceHandler("/member/**").addResourceLocations("/WEB-INF/pages/member/");
        
        // 添加上傳目錄的資源處理器
        String uploadDir = uploadDirectory();
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + uploadDir + File.separator);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
//		registry.addRedirectViewController("/", "membersmain.controller");
//		registry.addViewController("/wonderland").setViewName("loginSystem");
		registry.addRedirectViewController("/", "/admin/login");
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
		InternalResourceViewResolver irv = new InternalResourceViewResolver("/WEB-INF/pages/",".jsp");
		irv.setOrder(1);
		return irv;
	}
//	@Bean
//	public InternalResourceViewResolver irViewResolverHtml() {
//	    InternalResourceViewResolver irv = new InternalResourceViewResolver("/WEB-INF/pages/", ".html");
//	    irv.setOrder(2); // 在 JSP 之後
//	    return irv;
//	}
	// 沒用？
	
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
