package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Document(indexName = "search_documents")
public class SearchDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private String mongoId;

    @Field(type = FieldType.Text, analyzer = "russian")
    private String fullText;
}
