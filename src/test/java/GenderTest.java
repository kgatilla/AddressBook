import AddressBookRecord.Gender;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenderTest {

    private static String[] goodMale = null;
    private static String[] goodFemale = null;
    private static String[] nonGenderStrings = null;

    @BeforeClass
    public static void setupArrays() {
        goodMale = new String[] {"MALE", "MALe", "Male", "male"};
        goodFemale = new String[] {"Female", "FEMALE", "female", "FeMaLe"};
        nonGenderStrings = new String[]{"Fe_ale", "Male ", "mle", "Fe", ""};

    }

    @AfterClass
    public static void tearDown() {
        goodFemale = null;
        goodMale = null;
        nonGenderStrings = null;
    }

    @Test
    public void asString_Female() {
        assertTrue("AddressBookRecord.Gender.FEMALE asString should be 'FEMALE'", Gender.FEMALE.asString().equalsIgnoreCase("FEMALE"));
    }

    @Test
    public void asString_Male() {
        assertTrue("AddressBookRecord.Gender.MALE asString should be 'MALE'", Gender.MALE.asString().equalsIgnoreCase("MALE"));
    }

    @Test
    public void fromString_goodMale_0() {
        assertTrue("'MALE' should not result in Optional.Empty.", Gender.fromString(goodMale[0]).isPresent());
        assertTrue("'MALE' should map to AddressBookRecord.Gender.MALE", Gender.fromString(goodMale[0]).filter(g -> (g == Gender.MALE)).isPresent());
    }

    @Test
    public void fromString_badMale_0() {
        assertTrue("'ale' should result in Optional.Empty.", !Gender.fromString(nonGenderStrings[0]).isPresent());
    }

    @Test
    public void fromString_goodFemale_0() {
        assertTrue("'Female' should not result in Optional.Empty.", Gender.fromString(goodFemale[0]).isPresent());
        assertTrue("'Female' should map to AddressBookRecord.Gender.FEMALE", Gender.fromString(goodFemale[0]).filter(g -> (g == Gender.FEMALE)).isPresent());
    }

    @Test
    public void fromString_badFemale_0() {
    }
}