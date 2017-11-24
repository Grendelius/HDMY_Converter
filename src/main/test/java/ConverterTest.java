import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before ConverterTest.Class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After ConverterTest.Class");
    }

    @Test
    public void testToHours() throws Exception {
        Converter.setNum(60);
        String result = Converter.toHours();
        assertEquals("1,0", result);
    }

    @Test
    public void testToDays() throws Exception {
        Converter.setNum(1440);
        assertEquals("1,0", Converter.toDays());
    }

    @Test
    public void testToMonths() throws Exception {
        Converter.setNum(43200);
        assertEquals("1,0", Converter.toMonths());
    }

    @Test
    public void testToYears() throws Exception {
        Converter.setNum(518400);
        assertEquals("1,0", Converter.toYears());
    }

    @Test(expected = NumberFormatException.class)
    public void testToException() {
        Converter.setNum(Long.parseLong(null));
        Converter.toHours();
        Converter.setNum(Long.parseLong("string"));
        Converter.toHours();
    }

    @Test
    public void testInfinityString() {
        Converter.setNum(Long.MAX_VALUE);
        assertEquals("infinity", Converter.toHours());
    }

}