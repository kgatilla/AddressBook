import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class AddressBookFileUtility {

    /**
     * Intended to separate file BufferedReader setup from its processing in order to allow testing of processing
     * @param filePath valid filename expected
     * @return a BufferedReader of the file
     */
    public static Optional<BufferedReader> createBufferedReader(final String filePath) {

        if (!isExistingFile(filePath))
            return Optional.empty();

        BufferedReader res = null;
        try{
            res = new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            try {
                if (res!=null)
                    res.close();
                res = null;
            } catch (IOException e1) {
                res = null;
                e1.printStackTrace();
            }
            e.printStackTrace();
        }

        return Optional.ofNullable(res);
    }

    /**
     * Checks if parameter is a file path
     * @param maybeFile
     * @return true if given parameter is an existing file.
     */
    public static boolean isExistingFile(String maybeFile){

        File f = new File(maybeFile);
        if (!f.exists() || !f.isFile() || f.isDirectory()) {
            return false;
        }

        return true;
    }


}
