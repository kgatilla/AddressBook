import org.junit.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Optional;

import static org.junit.Assert.*;

public class AddressBookProcessorTest {

    private static BufferedReader br = null;
    private static String content = null;

    @Before
    public void prep(){
        content = "Bill McKnight, Male, 16/03/77\n" +
                "Paul Robinson, Male, 15/01/85\n" +
                "Gemma Lane, Female, 20/11/91\n" +
                "Sarah Stone, Female, 20/09/80\n" +
                "Wes Jackson, Male, 14/08/74";

        br = new BufferedReader(new StringReader(content));
    }

    @After
    public void tearDown() {
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        br = null;
        content = null;
    }

    @Ignore
    @Test
    public void buildResultString() {

    }


    @Test
    public void processReader() {
        AddressBookProcessor processor = new AddressBookProcessor();
        Optional<String> res = (processor.processReader(br));
        assertTrue("Result shoud not be Optional.empty", res.isPresent());
    }
}