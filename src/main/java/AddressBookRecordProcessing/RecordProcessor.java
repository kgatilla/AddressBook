package AddressBookRecordProcessing;

import AddressBookRecord.AddressBookRecord;

import java.util.Optional;

/**
 * Interface for processors that:
 * process one AddressBookRecord.AddressBookRecord at a time
 * @param <T> the processor class
 * @param <V> the value that should be returned in getValue
 */
public interface RecordProcessor<T, V> {

    /**
     * functionality for processing one record
     * @param record the AddressBookRecordProcessed
     * @return
     */
    public T processRecord(AddressBookRecord record);

    /**
     *
     * @return a value if the processor managed to compute the needed value empty otherwise
     */
    public Optional<V> getValue();
}
