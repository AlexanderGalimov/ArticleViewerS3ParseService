package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.component;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class PdfToTextExtractor {

    public String extractTextFromPdf(String pdfContent) throws IOException {
        try (InputStream in = openPdfStream(pdfContent)) {
            PDDocument document = PDDocument.load(in);
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            String result = pdfTextStripper.getText(document);
            document.close();
            return result;
        }
    }

    private InputStream openPdfStream(String pdfLink) throws IOException {
        URL url = new URL(pdfLink);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestProperty("User-Agent", "my-agent");
        httpURLConnection.connect();
        return httpURLConnection.getInputStream();
    }

}
