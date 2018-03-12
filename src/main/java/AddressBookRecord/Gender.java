package AddressBookRecord;

import java.util.Optional;

/**
 * Simple representation of AddressBookRecord.Gender
 */
public enum Gender {
    MALE("MALE"),
    FEMALE("FEMALE");

    private String genderString;

    Gender(String gender){
        this.genderString = gender;
    }

    public String asString(){
        return this.genderString;
    }

    /**
     * Simple utility function to return the AddressBookRecord.Gender requested in the parameter
     * @param maybeGender 'MALE' or 'FEMALE' expected
     * @return a gender if input is correct, Optional.empty otherwise
     */
    public static Optional<Gender> fromString(String maybeGender) {

        if (MALE.genderString.compareToIgnoreCase(maybeGender) == 0 )
            return Optional.of(MALE);
        else if( FEMALE.genderString.compareToIgnoreCase(maybeGender) == 0)
            return Optional.of(FEMALE);
        else
            return Optional.empty();
    }
}
