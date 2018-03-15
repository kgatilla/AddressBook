package AddressBookRecordProcessing;

import AddressBookRecord.AddressBookRecord;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;


/**
 * processor to identify a pair of persons where the first person is expected to be older than the second person
 *
 * Only returns a value if both persons have been found and first is older than second
 */
public class OlderThan implements AddressBookQuestion<OlderThan, Long> {

    private AddressBookRecord first = null;
    private AddressBookRecord second = null;

    private final String nameOfFirstPerson;
    private final String nameOfSecondPercon;

    /**
     * @param nameOfFirstPerson FIRST name of the first person
     * @param nameOfSecondPercon FIRST name of the second person
     */
    public OlderThan(String nameOfFirstPerson, String nameOfSecondPercon) {
        this.nameOfFirstPerson = nameOfFirstPerson;
        this.nameOfSecondPercon = nameOfSecondPercon;
    }

    /**
     * @return true if both people have been found and the names are different
     */
    public boolean done() {
        return (first!=null && second != null) || (nameOfSecondPercon.equalsIgnoreCase(nameOfFirstPerson));
    }


    /**
     * Simply tries to match the input record to any of the first or second person names provided in the ctor
     * @param record the AddressBookRecordProcessed
     * @return this
     */
    @Override
    public OlderThan processRecord(AddressBookRecord record) {

        if (!done()) {
            String[] fullName = record.getFullName().split(" ");

            if (first == null && nameOfFirstPerson.compareToIgnoreCase(fullName[0]) == 0)
                first = record;

            if (second == null && nameOfSecondPercon.compareToIgnoreCase(fullName[0]) == 0)
                second = record;
        }
        return this;
    }

    /**
     * @return how much older the first person is than the second in days or Optional.empty
     * if either person has not been found or if the first person is not older than the second
     */
    @Override
    public Optional<Long> getValue() {
        return getHowManyDaysOlder();
    }

    @Override
    public Optional<String> getResponse() {
        return getValue().map(days-> first.getFullName() + " is " + days + " days older than " + second.getFullName());
    }


    /**
     * @return how much older the first person is than the second in days or Optional.empty
     * if either person has not been found or if the first person is not older than the second
     */
    public Optional<Long> getHowManyDaysOlder() {
        if (first!=null && second != null) {
            Optional<Long> res = first.getBirthDate()
                    .flatMap(bd1 -> second.getBirthDate()
                            .flatMap(bd2 -> daysOlderThan(bd1, bd2)));

            return res;
        }
        return Optional.empty();
    }

    private Optional<Long> daysOlderThan(LocalDate d1, LocalDate d2){
        if (d1.isBefore(d2))
            return Optional.of(ChronoUnit.DAYS.between(d1,d2));
        else
            return Optional.empty();

    }
}
