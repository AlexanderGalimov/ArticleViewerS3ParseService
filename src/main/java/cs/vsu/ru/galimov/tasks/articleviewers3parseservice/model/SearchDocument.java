package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "search_documents")
public class SearchDocument {

    @Id
    @Field(type = FieldType.Keyword)
    private String mongoId;

    @Field(type = FieldType.Text, analyzer = "russian")
    private String fullText;
}
