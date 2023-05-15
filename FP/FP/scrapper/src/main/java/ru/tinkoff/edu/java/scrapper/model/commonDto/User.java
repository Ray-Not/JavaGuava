package ru.tinkoff.edu.java.scrapper.model.commonDto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.tinkoff.edu.java.scrapper.model.jpa.UserEntity;

@Data
@NoArgsConstructor
public class User {

    public User(Long chatId, String username, String firstName, String lastName) {
        this.chatId = chatId;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    private Long chatId;
    private String username;
    private String firstName;
    private String lastName;
    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();

        userEntity.setChatId(user.getChatId());
        userEntity.setUsername(user.getUsername());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());

        return userEntity;
    }
}
