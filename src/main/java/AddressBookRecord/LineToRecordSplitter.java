package AddressBookRecord;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import static AddressBookRecord.Gender.*;

/**
 * Utility class to transform Address Book lines into AddressBookRecord.AddressBookRecord
 */
public class LineToRecordSplitter {

    private static final String SEPARATOR = ",";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

    private LineToRecordSplitter() {}

    /**
     * @param ddmmyy expects 'dd/mm/yy' format
     * @return Either the date if conversion was successful, or Optional.empty
     */
    public static Optional<LocalDate> dateFromString(String ddmmyy){
        LocalDate localDate = null;
        try {
            localDate = LocalDate.parse(ddmmyy, formatter);
        }
        catch (DateTimeParseException ex) {/**/}

        return Optional.ofNullable(localDate);
    }

    /**
     * @param line one line of the Address Book being scanned
     * @return a full AddressBookRecord.AddressBookRecord if all line could be well process,
     * or Optional.empty
     */
    public static Optional<AddressBookRecord> lineToAddressBookRecord(final String line) {

        String[] fields = line.split(SEPARATOR);

        if (fields.length!=3)
            return Optional.empty();

        String name = fields[0].trim();
        Optional<Gender> gender = fromString(fields[1].trim());
        Optional<LocalDate> birthDate = dateFromString(fields[2].trim());

        Optional<AddressBookRecord> rec =
                gender.map(g -> new AddressBookRecord(name, g, birthDate));

        return rec;
    }



}
