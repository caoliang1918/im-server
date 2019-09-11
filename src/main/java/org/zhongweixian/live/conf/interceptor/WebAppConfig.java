package org.zhongweixian.live.conf.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.zhongweixian.live.ImServerApplication;

/**
 *
 */
@Configuration
@ComponentScan(basePackageClasses = ImServerApplication.class, useDefaultFilters = true)
public class WebAppConfig implements WebMvcConfigurer {

    @Autowired
    private AuthorInterceptor authorInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorInterceptor).addPathPatterns("/admin/**").addPathPatterns("/user/**").excludePathPatterns("");
    }

    /**
     * url传入小数点数据转换异常
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false);
    }
}
