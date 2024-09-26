package config;

import java.io.File;

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
	        // 使用系統臨時目錄
	        String tempDir = System.getProperty("java.io.tmpdir");
	        
	        // 創建一個專門的子目錄用於文件上傳
	        File uploadTempDir = new File(tempDir, "tickiteasy-uploads");
	        if (!uploadTempDir.exists()) {
	            uploadTempDir.mkdirs();
	        }

	        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(
	            uploadTempDir.getAbsolutePath(),
	            5 * 1024 * 1024,  // 5MB 最大文件大小
	            10 * 1024 * 1024, // 10MB 最大請求大小
	            0 // 大小閾值，超過後將內容寫入磁盤
	        );
	        registration.setMultipartConfig(multipartConfigElement);
	    }
}
