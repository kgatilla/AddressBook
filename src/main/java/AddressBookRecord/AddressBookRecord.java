package AddressBookRecord;

import java.time.LocalDate;

/**
 * Simple representation for each entry in the address book
 */
public class AddressBookRecord {
    private final String name;
    private final Gender gender;
    private final LocalDate birthDate;

    public AddressBookRecord(String name, Gender gender, LocalDate birthDate) {
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

    public LocalDate getBirthDate() {
        return this.birthDate;
    }
}
