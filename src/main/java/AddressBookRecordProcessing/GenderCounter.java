package AddressBookRecordProcessing;

import AddressBookRecord.AddressBookRecord;
import AddressBookRecord.Gender;
import javafx.util.Pair;

import java.util.Optional;

/**
 * Processor to count number of Male and Female in the file
 */
public class GenderCounter implements AddressBookQuestion<GenderCounter, Pair<Integer, Integer>> {

    private int maleCount = 0;
    private int femaleCount = 0;

    public GenderCounter(){}

    /**
     * @param record the AddressBookRecordProcessed
     * @return this
     */
    @Override
    public GenderCounter processRecord(AddressBookRecord record) {

        if (record.getGender()== Gender.FEMALE)
            ++femaleCount;
        else
            ++maleCount;

        return this;
    }

    /**
     * @return the Pair holds the number of Males in the key position and the number of Female in the value position
     */
    @Override
    public Optional<Pair<Integer, Integer>> getValue() {
        return Optional.of( new Pair<>(this.getMaleCount(), this.getFemaleCount()));
    }

    @Override
    public Optional<String> getResponse() {
        return getValue().map( mf -> "Male:" + mf.getKey() + " Female:" + mf.getValue());
    }

    public int getMaleCount() {
        return this.maleCount;
    }

    public int getFemaleCount() {
        return this.femaleCount;
    }
}
