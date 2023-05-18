import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.tinkoff.edu.java.linkparser.LinkParser;

public class LinkParserTest {

    String gitLink = "https://github.com/Ray-Not/JavaGuava";
    String invalidLink = "https://gitrub.com/";
    String invalidLink2 = "https://github.com/Ray-Not";
    String expectGitLink = "Ray-Not/JavaGuava";
    String stackLink = "https://stackoverflow.com/questions/1642028/what-is-the-operator-in-c";
    String expectStackLink = "1642028";

    LinkParser pars = new LinkParser();

    @Test
    public void validGitPars(){
        Assertions.assertNull(pars.getLink(invalidLink));
        Assertions.assertNull(pars.getLink(invalidLink2));
        Assertions.assertNotNull(pars.getLink(gitLink));
        Assertions.assertEquals(pars.getLink(gitLink), expectGitLink);
        Assertions.assertEquals(pars.getLink(stackLink), expectStackLink);
    }
}
