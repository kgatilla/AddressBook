import AddressBookRecord.LineToRecordSplitter;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LineToRecordSplitterTest {

    private static String[] goodDates = null;
    private static String[] badDates = null;

    @BeforeClass
    public static void setupArrays(){
        goodDates = new String[] {"11/11/22","01/01/01", "01/01/99"};
        badDates = new String[] {"1/1/1", "1/02/02", "02/2/02", "02/02/2",
                "00/01/01", "01/00/01",
                "/01/01", "01//01", "01/01/",
                "-1/01/01", "01/-1/01", };
    }


    @AfterClass
    public static void tearDown() {
        goodDates=null;
        badDates=null;
    }

    @Test
    public void dateFromString_goodString_0() {
        assertTrue("String conversion to date failed for:" + goodDates[0],
                LineToRecordSplitter.dateFromString(goodDates[0]).isPresent());
    }

    @Test
    public void dateFromString_goodString_1() {
        assertTrue("String conversion to date failed for:" + goodDates[1],
                LineToRecordSplitter.dateFromString(goodDates[1]).isPresent());
    }

    @Test
    public void dateFromString_goodString_2() {
        assertTrue("String conversion to date failed for:" + goodDates[2],
                LineToRecordSplitter.dateFromString(goodDates[2]).isPresent());
    }


    @Test
    public void dateFromString_badString_0() {
        assertTrue("String conversion to date succeeded for:" + badDates[0],
                !LineToRecordSplitter.dateFromString(badDates[0]).isPresent());
    }

    @Test
    public void dateFromString_badString_1() {
        assertTrue("String conversion to date succeeded for:" + badDates[1],
                !LineToRecordSplitter.dateFromString(badDates[1]).isPresent());
    }

    @Test
    public void dateFromString_badString_2() {
        assertTrue("String conversion to date succeeded for:" + badDates[2],
                !LineToRecordSplitter.dateFromString(badDates[2]).isPresent());
    }


    private static final String[] goodRecords = {
            "Bill McKnight, Male, 16/03/77",
            "Paul Robinson, Male, 15/01/85",
            "Gemma Lane, Female, 20/11/91",
            "Sarah Stone, Female, 20/09/80",
            "Wes Jackson, Male, 14/08/74"};

    @Test
    public void lineToAddressBookRecord_goodRecord_0() {
        assertTrue("Cannot convert good line to AddressBookRecord.AddressBookRecord:" + goodRecords[0],
        LineToRecordSplitter.lineToAddressBookRecord(goodRecords[0]).isPresent());
    }

    @Test
    public void lineToAddressBookRecord_goodRecord_1() {
        assertTrue("Cannot convert good line to AddressBookRecord.AddressBookRecord:" + goodRecords[1],
                LineToRecordSplitter.lineToAddressBookRecord(goodRecords[1]).isPresent());
    }
    @Test
    public void lineToAddressBookRecord_goodRecord_2() {
        assertTrue("Cannot convert good line to AddressBookRecord.AddressBookRecord:" + goodRecords[2],
                LineToRecordSplitter.lineToAddressBookRecord(goodRecords[2]).isPresent());
    }
    @Test
    public void lineToAddressBookRecord_goodRecord_3() {
        assertTrue("Cannot convert good line to AddressBookRecord.AddressBookRecord:" + goodRecords[3],
                LineToRecordSplitter.lineToAddressBookRecord(goodRecords[3]).isPresent());
    }
    @Test
    public void lineToAddressBookRecord_goodRecord_4() {
        assertTrue("Cannot convert good line to AddressBookRecord.AddressBookRecord:" + goodRecords[4],
                LineToRecordSplitter.lineToAddressBookRecord(goodRecords[4]).isPresent());
    }
}