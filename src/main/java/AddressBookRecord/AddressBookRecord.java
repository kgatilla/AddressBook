package AddressBookRecord;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Simple representation for each entry in the address book
 */
public class AddressBookRecord {
    private final String name;
    private final Gender gender;
    private final Optional<LocalDate> birthDate;

    public AddressBookRecord(String name, Gender gender, Optional<LocalDate> birthDate) {
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getFullName() {
        return this.name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public Optional<LocalDate> getBirthDate() {
        return this.birthDate;
    }
}
