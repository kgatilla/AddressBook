import AddressBookRecord.AddressBookRecord;
import AddressBookRecord.LineToRecordSplitter;
import AddressBookRecordProcessing.GenderCounter;
import AddressBookRecordProcessing.OlderThan;
import AddressBookRecordProcessing.OldestPersonFinder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

/**
 * Main class processing the Address Book
 */
public class AddressBookProcessor {


    public AddressBookProcessor() {}


    /**
     * Intended to separate file BufferedReader setup from its processing in order to allow testing of processing
     * @param file valid filename expected
     * @return a BufferedReader of the file
     */
    public BufferedReader createBufferedReader(String file) {

        BufferedReader res = null;

        try{
            res = new BufferedReader(new FileReader(file));
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

        return res;
    }


    /**
     * Builds the result string based on the AddressBookRecordProcessing.RecordProcessor values
     * Only adds a result section of the AddressBookRecordProcessing.RecordProcessor could produce a value
     * @param gCounter holds the counter for genders
     * @param oldest holds the oldest person identified
     * @param bthanp holds the answer to the question how many days older is X than Y
     * @param gthans holds the answer to the question how many days older is X than Y
     * @return a of format:
     * 1. Female: x Male:y - aka. the number of male and females
     * 2. James Bond - aka. the name of the oldest person
     * 3. 43 - aka. number of days X is older than Y
     * 4. 43 - aka. number of days X is older than Y
     *
     * Note: If any of the sections is missing it means the AddressBookRecordProcessing.RecordProcessor could not produce a meaningful answer.
     */
    public String buildResultString(final GenderCounter gCounter, final OldestPersonFinder oldest,
                                    final OlderThan bthanp, final OlderThan gthans) {

        StringBuilder sb = new StringBuilder();

        gCounter.getValue().ifPresent(g -> sb.append("1. ")
                                    .append("Female:").append(g.getValue())
                                    .append(" Male:").append(g.getKey()).append("\n"));

        oldest.getValue().ifPresent(m-> sb.append("2. ").append(m).append("\n"));
        bthanp.getValue().ifPresent(bp -> sb.append("3. ").append(bp).append("\n"));
        gthans.getValue().ifPresent(gt -> sb.append("4. ").append(gt).append("\n"));

        return sb.toString();
    }

    /**
     * Main processing of AddressBook with hardcoded answers to reply
     * @param br the bufferedReader from the Address Book
     * @return the formated result string or
     */
    public Optional<String> processReader(BufferedReader br) {

        Optional<String> res = Optional.empty();

        //task 1. Count the number of male and female
        GenderCounter genderCounter = new GenderCounter();
        //task 2. Find the oldest person in the book
        OldestPersonFinder ageCounter = new OldestPersonFinder();
        //task 3. how many day older is Bill than Paul
        OlderThan billThanPaul = new OlderThan("Bill", "Paul");
        //task 4. how many days older is Gemma than Sarah
        OlderThan gemmaThanSarah = new OlderThan("Gemma", "Sarah");

        try {
            String line;

             while (( line = br.readLine()) != null ) {
                Optional<AddressBookRecord> rec = LineToRecordSplitter.lineToAddressBookRecord(line);
                rec.ifPresent(r -> {
                    genderCounter.processRecord(r);
                    ageCounter.processRecord(r);
                    billThanPaul.processRecord(r);
                    gemmaThanSarah.processRecord(r);});
             }

             res = Optional.of(buildResultString(genderCounter, ageCounter, billThanPaul, gemmaThanSarah));
        }
        catch (IOException e) {
            try {
                br.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }

        return res;
    }

    /**
     * - Opens the file for processing and performs the processing.
     * - Eventually closes the depleted BufferedReader
     * @param file
     * @return
     */
    public String process(String file) {

        String result = "";
        BufferedReader br = createBufferedReader(file);

        if (br!=null)
            result = processReader(br).orElse("ERROR");

        try {
            if (br!=null)
                br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }


}
