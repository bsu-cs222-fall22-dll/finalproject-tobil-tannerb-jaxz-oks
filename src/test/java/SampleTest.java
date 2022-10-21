import edu.bsu.cs222.Template;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    public void testCreateTemplate(){
        String memeTitle = "Meme01";
        Template template = new Template(memeTitle);
        Assertions.assertTrue(true);
    }

}
