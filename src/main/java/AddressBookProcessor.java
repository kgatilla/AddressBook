import AddressBookRecord.AddressBookRecord;
import AddressBookRecord.LineToRecordSplitter;
import AddressBookRecordProcessing.GenderCounter;
import AddressBookRecordProcessing.OlderThan;
import AddressBookRecordProcessing.OldestPersonFinder;
import AddressBookRecordProcessing.AddressBookQuestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

/**
 * Main class processing the Address Book
 */
public class AddressBookProcessor {


    public AddressBookProcessor() {}


    private TreeMap<Integer, AddressBookQuestion> buildQuestionsList() {
        TreeMap<Integer, AddressBookQuestion> questions = new TreeMap<>();
        questions.put(1, new GenderCounter()); //task 1. Count the number of male and female
        questions.put(2, new OldestPersonFinder()); //task 2. Find the oldest person in the book
        questions.put(3, new OlderThan("Bill", "Paul")); //task 3. how many day older is Bill than Paul
        questions.put(4, new OlderThan("Gemma", "Sarah")); //task 4. how many days older is Gemma than Sarah

        return questions;
    }
    /**
     * Builds the result string based on the AddressBookRecordProcessing.AddressBookQuestion values
     * Only adds a result section of the AddressBookRecordProcessing.AddressBookQuestion could produce a value
     * @return a of format:
     * 1. Female: x Male:y - aka. the number of male and females
     * 2. James Bond - aka. the name of the oldest person
     * 3. 43 - aka. number of days X is older than Y
     * 4. 43 - aka. number of days X is older than Y
     *
     * Note: If any of the sections is missing it means the AddressBookRecordProcessing.AddressBookQuestion could not produce a meaningful answer.
     */
    private String buildResultString(Map<Integer, AddressBookQuestion> questions) {

        StringBuilder sb = new StringBuilder();

        questions.forEach((k,v) -> v.getResponse().ifPresent(
                response -> sb.append("<").append(k).append(">. <")
                        .append(response).append(">").append("\n")));

        String res = sb.toString();
        return res;
    }

    /**
     * Main processing of AddressBook with hardcoded answers to reply
     * @param br the bufferedReader from the Address Book
     * @return the formated result string or
     */
    public Optional<String> processReader(final BufferedReader br) {

        Optional<String> res = Optional.empty();

        TreeMap<Integer, AddressBookQuestion> questions = buildQuestionsList();

        try {
            String line;

             while (( line = br.readLine()) != null ) {
                Optional<AddressBookRecord> rec = LineToRecordSplitter.lineToAddressBookRecord(line);
                rec.ifPresent(r -> questions
                        .forEach((k,v) -> v.processRecord(r)));
             }

             res = Optional.of(buildResultString(questions));
        }
        catch (IOException e) {
           e.printStackTrace();
        }

        return res;
    }

    /**
     * - Opens the file for processing and performs the processing.
     * - Eventually closes the depleted BufferedReader
     * @param filePath
     * @return
     */
    public String process(String filePath) {

        Optional<BufferedReader> br = AddressBookFileUtility.createBufferedReader(filePath);

        String result = br.flatMap(this::processReader)
                            .orElse("ERROR");

        br.ifPresent(x -> {
                try {
                    x.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        return result;
    }


}
