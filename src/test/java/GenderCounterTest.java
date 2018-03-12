import AddressBookRecord.AddressBookRecord;
import AddressBookRecord.Gender;
import AddressBookRecordProcessing.GenderCounter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class GenderCounterTest {

    private static AddressBookRecord[] records = null;

    @BeforeClass
    public static void setUpRecords() {
        records = new AddressBookRecord[] {
                new AddressBookRecord("James", Gender.MALE, LocalDate.of(12,12,12)),
                new AddressBookRecord("Jane", Gender.FEMALE, LocalDate.of(1,1,1))
        };
    }

    @AfterClass
    public static void tearDown() {
        records = null;
    }

    @Test
    public void getMaleCount_1() {
        assertTrue("Male count should be 1.", new GenderCounter().processRecord(records[0]).getMaleCount() == 1);
    }

    @Test
    public void getMaleCount_0() {
        assertTrue("Male count should be 0.", new GenderCounter().processRecord(records[1]).getMaleCount() == 0);
    }

    @Test
    public void getFemaleCount_0() {
        assertTrue("Female count should be 0.", new GenderCounter().processRecord(records[0]).getFemaleCount() == 0);
    }

    @Test
    public void getFemaleCount_1() {
        assertTrue("Female count should be 1.", new GenderCounter().processRecord(records[1]).getFemaleCount() == 1);
    }

    @Test
    public void getValue_1_1() {
        GenderCounter gc = new GenderCounter();
        gc.processRecord(records[0])
          .processRecord(records[1]);

        assertTrue("Male and Female count shoud both be 1.", (gc.getValue().get().getKey() == 1 && gc.getValue().get().getValue() == 1));
    }
}