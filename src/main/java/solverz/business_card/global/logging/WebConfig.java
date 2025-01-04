package solverz.business_card.global.logging;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ObjectProvider<ApiLoggingInterceptor> apiLoggingInterceptorProvider;

    public WebConfig(ObjectProvider<ApiLoggingInterceptor> apiLoggingInterceptorProvider) {
        this.apiLoggingInterceptorProvider = apiLoggingInterceptorProvider;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ApiLoggingInterceptor apiLoggingInterceptor = apiLoggingInterceptorProvider.getIfAvailable();
        if (apiLoggingInterceptor != null) {
            registry.addInterceptor(apiLoggingInterceptor)
                    .addPathPatterns("/api/**");
        }
    }
}