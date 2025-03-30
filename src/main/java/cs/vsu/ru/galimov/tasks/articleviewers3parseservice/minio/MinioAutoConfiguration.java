package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.properties.MinioProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioAutoConfiguration {

    private final MinioProperties properties;

    @Bean
    @ConditionalOnMissingBean(MinioTemplate.class)
    @ConditionalOnProperty(name = "minio.url")
    public MinioTemplate template() {
        return new MinioTemplate(properties);
    }
}


