import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.DirectoryResourceAccessor;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class IntegrationEnvironment {

    static final protected PostgreSQLContainer Postgre_container;

    static {
        Postgre_container = new PostgreSQLContainer<>("postgres:14");
        Postgre_container.start();

        try {
            Connection connection = DriverManager.getConnection(
                    Postgre_container.getJdbcUrl(),
                    Postgre_container.getUsername(),
                    Postgre_container.getPassword());
            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
            Liquibase liquibase = new liquibase.Liquibase(
                    "master.xml",
                    new DirectoryResourceAccessor(Path.of(".")
                            .toAbsolutePath()
                            .getParent()
                            .getParent()
                            .resolve("migrations")),
                    database);
            liquibase.update(new Contexts(), new LabelExpression());
        } catch (SQLException | LiquibaseException | FileNotFoundException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
