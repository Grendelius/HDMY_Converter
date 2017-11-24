import junit.framework.TestCase;
import org.junit.*;

public class ConverterTest extends TestCase {
    Converter converter;

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
        String result = Converter.toDays();
        assertEquals("1,0", result);
    }

    @Test
    public void testToMonths() throws Exception {
        Converter.setNum(43200);
        String result = Converter.toMonths();
        assertEquals("1,0", result);
    }

    @Test
    public void testToYears() throws Exception {
        Converter.setNum(518400);
        String result = Converter.toYears();
        assertEquals("1,0", result);
    }

    @Test(expected = ArithmeticException.class)
    public void testToException() {
        Converter.setNum(0);
        Converter.toHours();
    }

    @Test(timeout = 500)
    public void timeStapTest() {
        while (true);
    }


}