package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "Authors")
public class Author {

    @Id
    private String id;

    private String name;
}
