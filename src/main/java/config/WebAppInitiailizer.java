package config;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration.Dynamic;

//相當於web.xml的Java程式組態
public class WebAppInitiailizer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override  //設定註冊相當於beans.config.xml的Java程式組態類別
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
//		return null;
	}

	@Override  //設定註冊相當於mvc-servlet.xml的Java程式組態類別
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
//		return null;
	}

	@Override  //設定 DispathcerServlet mapping url-patterns
	protected String[] getServletMappings() {
		return new String[] {"/"};
//		return null;
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter cef = new CharacterEncodingFilter("UTF-8",true);
		return new Filter[] {cef};
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("c:/temp/upload/"));
	}

}
