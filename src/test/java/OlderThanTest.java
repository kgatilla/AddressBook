import AddressBookRecord.AddressBookRecord;
import AddressBookRecordProcessing.OlderThan;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static AddressBookRecord.AddressBookRecord.*;
import static AddressBookRecord.Gender.*;
import static org.junit.Assert.*;

public class OlderThanTest {

    private static AddressBookRecord[] records = null;

    @BeforeClass
    public static void setUpRecords() {
        records = new AddressBookRecord[] {
                new AddressBookRecord("James Lame", MALE, Optional.of(LocalDate.of(13,12,12))),
                new AddressBookRecord("Jane", FEMALE, Optional.of(LocalDate.of(1,1,1))),
                new AddressBookRecord("Jim Bloke", MALE, Optional.of(LocalDate.of(14,12,12)))
        };
    }

    @AfterClass
    public static void tearDown() {
        records = null;
    }


    @Test
    public void done_not_0() {
        OlderThan jamesThanJim = new OlderThan("James", "Jim");

        jamesThanJim.processRecord(records[0]);
        assertFalse("AddressBookRecordProcessing.OlderThan should not be yet done.", jamesThanJim.done());
    }


    @Test
    public void done_not_1() {
        OlderThan jamesThanJim = new OlderThan("James", "Jim");

        jamesThanJim.processRecord(records[0]);
        jamesThanJim.processRecord(records[1]);
        assertFalse("AddressBookRecordProcessing.OlderThan should not be yet done.", jamesThanJim.done());
    }

    @Test
    public void done_not_sameName() {
        OlderThan jamesThanJim = new OlderThan("James", "James");

        jamesThanJim.processRecord(records[0]);
        jamesThanJim.processRecord(records[1]);
        assertTrue("AddressBookRecordProcessing.OlderThan should be DONE when two names are the same.", jamesThanJim.done());
    }

    @Test
    public void done_0() {
        OlderThan jamesThanJim = new OlderThan("James", "Jim");

        Arrays.stream(records).forEach(jamesThanJim::processRecord);

        assertTrue("AddressBookRecordProcessing.OlderThan should be DONE.", jamesThanJim.done());
    }


    @Test
    public void getValue() {
        OlderThan jamesThanJim = new OlderThan("James", "Jim");
        Arrays.stream(records).forEach(jamesThanJim::processRecord);
        assertEquals(365, jamesThanJim.getValue().get().longValue() );
    }
}