package cs.vsu.ru.galimov.tasks.articleviewers3parseservice.model;

import lombok.Data;

@Data
public class DateArchive {
    private String info;

    private String link;

    public DateArchive(String info, String link) {
        this.info = info;
        this.link = link;
    }

    public DateArchive() {
    }
}
