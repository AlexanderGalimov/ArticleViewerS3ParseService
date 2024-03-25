package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio.MinioTemplate;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class NameParser {

    private final MinioTemplate template;

    @Autowired
    public NameParser(MinioTemplate template) {
        this.template = template;
    }

    public String getS3Link(String name) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return parseLink(template.downloadFile(name));
    }

    private String parseLink(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf("http");
                if (index != -1) {
                    stringBuilder.append(line.substring(index));
                }
            }
        }
        return stringBuilder.toString();
    }
}
