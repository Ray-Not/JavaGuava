import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BaseTest extends IntegrationEnvironment {

    @Test
    void create_test() {
        Assertions.assertTrue(Postgre_container.isCreated());
        System.out.println(Postgre_container.getImage());
    }
}