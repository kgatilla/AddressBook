import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LargeBookProcessingTest {

    private class MyLargeBufferedReader extends BufferedReader {

        private int linesToMock=0;

        public MyLargeBufferedReader(Reader in, int sz) {
            super(in, sz);
        }

        public MyLargeBufferedReader(Reader in) {
            super(in);
        }

        public void setLinesToMock(int lines){
            this.linesToMock = lines;
        }

        @Override
        public String readLine() throws IOException {
            if (linesToMock>0){
                linesToMock--;
                return "Bill McKnight, Male, 16/03/77";
            }
            return null;
        }
    }

    @Ignore
    @Test
    public void largeTest() {
        MyLargeBufferedReader dummyReader = new MyLargeBufferedReader(new StringReader("TOTO"));
        dummyReader.setLinesToMock(1_000_000); //100 mill 1 min 23 sec
        AddressBookProcessor pr = new AddressBookProcessor();

        pr.processReader(dummyReader).ifPresent(System.out::println);

    }

}
