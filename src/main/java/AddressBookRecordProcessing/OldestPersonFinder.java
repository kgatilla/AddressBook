package AddressBookRecordProcessing;

import AddressBookRecord.AddressBookRecord;

import java.util.Optional;

public class OldestPersonFinder implements AddressBookQuestion<OldestPersonFinder, String> {

    private AddressBookRecord oldestPerson = null;

    public OldestPersonFinder(){}

    @Override
    public OldestPersonFinder processRecord(AddressBookRecord record) {

        if (oldestPerson == null)
            oldestPerson = record;
        else
            oldestPerson.getBirthDate()
                    .ifPresent( bd1 -> record.getBirthDate()
                            .ifPresent(bd2 -> {
                                if(bd1.isAfter(bd2))
                                        oldestPerson = record;}));
        
        return this;
    }

    @Override
    public Optional<String> getValue() {
        if (this.oldestPerson == null)
            return Optional.empty();
        return Optional.ofNullable(this.oldestPerson.getFullName());
    }

    @Override
    public Optional<String> getResponse() {
        return getValue().map(name -> "The oldest person is " + name);
    }
}
