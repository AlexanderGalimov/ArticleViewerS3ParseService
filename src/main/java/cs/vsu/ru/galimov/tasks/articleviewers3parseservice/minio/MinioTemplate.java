package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.properties.MinioProperties;
import io.minio.BucketExistsArgs;
import io.minio.GetObjectArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.RemoveObjectArgs;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class MinioTemplate {

    private final MinioClient minioClient;

    private final String bucketName;

    @Autowired
    public MinioTemplate(@Qualifier("minioProperties") MinioProperties properties) {
        this.minioClient = MinioClient.builder()
                .endpoint(properties.getUrl())
                .credentials(properties.getAccessKey(), properties.getSecretKey())
                .build();
        this.bucketName = properties.getBucketName();
        createBucketIfNotExists(bucketName);
    }

    public InputStream downloadFile(String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InsufficientDataException, ErrorResponseException, InternalException, InvalidResponseException, XmlParserException {
        return minioClient.getObject(GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    public void deleteFile(String objectName) throws IOException, InvalidKeyException, NoSuchAlgorithmException, ServerException, InsufficientDataException, ErrorResponseException, InternalException, InvalidResponseException, XmlParserException {
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build());
    }

    private void createBucketIfNotExists(String bucketName) {
        try {
            boolean bucketExists = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
            if (!bucketExists) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            }
        } catch (Exception e) {
            System.out.println("Error in createBucketIfNotExists" + e.getMessage());
        }
    }
}
