package scrapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DatabaseTest extends IntegrationEnvironment{


    @Test
    @DisplayName("Тест для проверки запуска контейнера с PostgreSQL")
    public void testIfContainerIsRunning() {
        Assertions.assertTrue(POSTGRES_CONTAINER.isRunning(),"Ошибка запуска контейнера PostgreSQL");
    }

    @Test
    @DisplayName("Тест для проверки успешного применения миграций")
    void testMigrations() throws SQLException {
        try (Connection conn = DriverManager.getConnection(IntegrationEnvironment.POSTGRES_CONTAINER.getJdbcUrl(),
                IntegrationEnvironment.POSTGRES_CONTAINER.getUsername(),
                IntegrationEnvironment.POSTGRES_CONTAINER.getPassword());
             Statement statement = conn.createStatement()) {

            ResultSet resultSetUser = statement.executeQuery("SELECT * FROM \"user\"");
            Assertions.assertFalse(resultSetUser.next(), "Таблица user не создана");
            ResultSet resultSetLink = statement.executeQuery("SELECT * FROM \"link\"");
            Assertions.assertFalse(resultSetLink.next(), "Таблица link не создана");
            ResultSet resultSetUserLink = statement.executeQuery("SELECT * FROM \"user_link\"");
            Assertions.assertFalse(resultSetUserLink.next(),"Таблица user_link не создана");
        }
    }




}