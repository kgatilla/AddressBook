public class MainApp {

    public static void main(String[] args) {
        if (args.length >= 1) {
            AddressBookProcessor processor = new AddressBookProcessor();
            String res = processor.process(args[0]);
            System.out.print(res);
        }
    }
}
