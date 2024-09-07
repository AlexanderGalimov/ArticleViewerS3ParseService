package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model;

import lombok.Data;

@Data
public class Archive {
    private String link;

    private ArchiveType type;

    public Archive(String link, ArchiveType type) {
        this.link = link;
        this.type = type;
    }
}
