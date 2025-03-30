package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.extractor;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio.MinioTemplate;
import io.minio.errors.ErrorResponseException;
import io.minio.errors.InsufficientDataException;
import io.minio.errors.InternalException;
import io.minio.errors.InvalidResponseException;
import io.minio.errors.ServerException;
import io.minio.errors.XmlParserException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
@RequiredArgsConstructor
public class PdfToTextExtractor {

    private final MinioTemplate template;

    public String extractTextFromPdf(String uuid) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        try (InputStream in = template.downloadFile(uuid)) {
            PDDocument document = PDDocument.load(in);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String result = pdfTextStripper.getText(document);
            document.close();
            return result;
        }
    }
}
