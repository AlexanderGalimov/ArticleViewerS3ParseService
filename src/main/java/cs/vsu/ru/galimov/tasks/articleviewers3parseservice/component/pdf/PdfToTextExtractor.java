package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component.pdf;

import cs.vsu.ru.galimov.tasks.articleviewers3parseservice.minio.MinioTemplate;
import io.minio.errors.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class PdfToTextExtractor {

    private final MinioTemplate template;

    @Autowired
    public PdfToTextExtractor(MinioTemplate template) {
        this.template = template;
    }

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
