package ru.tinkoff.edu.java.scrapper.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.tinkoff.edu.java.scrapper.model.jpa.LinkEntity;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface JpaLinkRepository extends JpaRepository<LinkEntity,Long> {
    Optional<LinkEntity> findByUrl(String url);

    @Query("select u.chatId from UserEntity u join u.links l where l.id = :id")
    List<Long> findChatIdsByLinkId(@Param("id") Long id);

    List<LinkEntity> findByCheckedAtLessThanOrderByCheckedAtDesc(Timestamp compareDate);

}