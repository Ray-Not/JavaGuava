package ru.tinkoff.edu.java.scrapper.model.jpa;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "link")
@Data
public class LinkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", unique = true, nullable = false)
    private String url;

    @Column(name = "checked_at", nullable = false)
    private Timestamp checkedAt;

    @Column(name = "gh_pushed_at")
    private Timestamp ghPushedAt;

    @Column(name = "gh_description")
    private String ghDescription;

    @Column(name = "gh_forks_count")
    private Integer ghForksCount;

    @Column(name = "so_last_edit_date")
    private Timestamp soLastEditDate;

    @Column(name = "so_answer_count")
    private Integer soAnswerCount;

}