package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.model.jpa.LinkEntity;
import ru.tinkoff.edu.java.scrapper.model.jpa.UserEntity;

import java.util.List;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity,Long> {

    @Query("select link from UserEntity u join u.links link where u.chatId = :chatId")
    List<LinkEntity> findAllLinksByChat(@Param("chatId") Long chatId);


    @Query("select u from UserEntity u join fetch u.links link where u.chatId = :chatId")
    Optional<UserEntity> findByChatIdWithLinks(@Param("chatId") Long chatId);


}