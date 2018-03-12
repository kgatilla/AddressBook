import java.io.File;

public class MainApp {

    public static boolean isExistingFile(String maybeFile){

        File f = new File(maybeFile);
        if (!f.exists() || !f.isFile()) {
            System.out.println("Error: <" + maybeFile + "> cannot be used as input!");
            return false;
        }
        if (f.isDirectory()) {
            System.out.println("Error: Given path <" + maybeFile + ">is a directory! File path needed!");
            return false;
        }
        return true;
    }

    public static boolean argsCheck(String[] args) {

        if (args.length != 1)
            return false;

        return isExistingFile(args[0]);
    }

    public static void main(String[] args) {
        if (argsCheck(args)) {
            AddressBookProcessor processor = new AddressBookProcessor();
            String res = processor.process(args[0]);
            System.out.print(res);
        }
    }
}
