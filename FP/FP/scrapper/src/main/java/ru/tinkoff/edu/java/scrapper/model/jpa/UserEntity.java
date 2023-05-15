package ru.tinkoff.edu.java.scrapper.model.jpa;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
public class UserEntity {

    @Id
    @Column(name = "chat_id")
    private Long chatId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_link",
               joinColumns = @JoinColumn(name = "chat_id"),
               inverseJoinColumns = @JoinColumn(name = "link_id"))
    private List<LinkEntity> links;
}