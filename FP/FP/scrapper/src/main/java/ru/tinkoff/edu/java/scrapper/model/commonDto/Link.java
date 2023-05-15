package ru.tinkoff.edu.java.scrapper.model.commonDto;

import lombok.Data;
import ru.tinkoff.edu.java.scrapper.model.jpa.LinkEntity;
import java.sql.Timestamp;

@Data
public class Link {

    private Long id;
    private String url;
    private Timestamp checkedAt;
    private Timestamp ghPushedAt;
    private String ghDescription;
    private int ghForksCount;
    private Timestamp soLastEditDate;
    private int soAnswerCount;

    public static Link fromEntity(LinkEntity linkEntity) {
        Link link = new Link();
        link.setId(linkEntity.getId());
        link.setUrl(linkEntity.getUrl());
        link.setCheckedAt(linkEntity.getCheckedAt());
        link.setGhDescription(linkEntity.getGhDescription());
        link.setGhPushedAt(linkEntity.getGhPushedAt());
        Integer forks = linkEntity.getGhForksCount();
        if (forks == null) {
            link.setGhForksCount(0);
        } else {
            link.setGhForksCount(forks);
        }
        Integer answers = linkEntity.getSoAnswerCount();
        if (answers == null) {
            link.setSoAnswerCount(0);
        } else {
            link.setSoAnswerCount(answers);
        }
        link.setSoLastEditDate(linkEntity.getSoLastEditDate());
        return link;
    }

}