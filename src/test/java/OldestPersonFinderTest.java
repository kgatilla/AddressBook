import AddressBookRecord.AddressBookRecord;
import AddressBookRecord.Gender;
import AddressBookRecordProcessing.OldestPersonFinder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class OldestPersonFinderTest {

    private static AddressBookRecord[] records = null;

    @BeforeClass
    public static void setUpRecords() {
        records = new AddressBookRecord[] {
                new AddressBookRecord("James", Gender.MALE, Optional.of(LocalDate.of(12,12,12))),
                new AddressBookRecord("Jane", Gender.FEMALE, Optional.of(LocalDate.of(1,1,1))),
                new AddressBookRecord("Jim", Gender.MALE, Optional.of(LocalDate.of(14,1,1)))
        };
    }

    @AfterClass
    public static void tearDown() {
        records = null;
    }

    @Test
    public void getValue_0() {
        OldestPersonFinder opf = new OldestPersonFinder();
        assertTrue("Oldest person should return empty", !opf.getValue().isPresent());
    }

    @Test
    public void getValue_1() {
        OldestPersonFinder opf = new OldestPersonFinder();
        opf.processRecord(records[0]);
        assertTrue("Oldest person should return not return empty", opf.getValue().isPresent());
    }

    @Test
    public void getValue_2() {
        OldestPersonFinder opf = new OldestPersonFinder();
        Arrays.stream(records).forEach(opf::processRecord);

        assertTrue("Oldest person should be Jane", opf.getValue().get().equalsIgnoreCase("Jane"));
    }
}