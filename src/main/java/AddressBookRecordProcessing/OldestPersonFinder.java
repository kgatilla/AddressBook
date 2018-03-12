package AddressBookRecordProcessing;

import AddressBookRecord.AddressBookRecord;

import java.util.Optional;

public class OldestPersonFinder implements RecordProcessor<OldestPersonFinder, String> {

    private AddressBookRecord rec = null;

    public OldestPersonFinder(){}

    @Override
    public OldestPersonFinder processRecord(AddressBookRecord record) {

        if (rec == null || rec.getBirthDate().isAfter(record.getBirthDate()))
            rec = record;
        
        return this;
    }

    @Override
    public Optional<String> getValue() {
        if (this.rec == null)
            return Optional.empty();
        return Optional.ofNullable(this.rec.getFullName());
    }
}
