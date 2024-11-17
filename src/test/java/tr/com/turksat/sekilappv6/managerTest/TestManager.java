package tr.com.turksat.sekilappv6.managerTest;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv6.manager.InputManager;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.AssertJUnit.assertEquals;

public class TestManager {

    private InputStream originalIn;

    @BeforeMethod
    public void setup() {
        
        originalIn = System.in;
    }

    @AfterMethod
    public void teardown() {
        System.setIn(originalIn);
    }

    @Test(dataProvider = "validIntData")
    public void testReadInt(String input, int expected) {
        setInput(input);
        int result = InputManager.readInt("Lütfen bir sayı girin: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] validIntData() {
        return new Object[][]{
                {"5\n", 5},
                {"12\n", 12},
                {"1\n", 1}
        };
    }

    @Test(dataProvider = "invalidThenValidIntData")
    public void testReadIntWithInvalidInput(String input, int expected) {
        setInput(input);
        int result = InputManager.readInt("Lütfen bir sayı girin: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] invalidThenValidIntData() {
        return new Object[][]{
                {"abc\n5\n", 5},
                {"!@#\n10\n", 10},
                {"123abc\n7\n", 7}
        };
    }

    @Test(dataProvider = "validSymbolData")
    public void testReadChar(String input, char expected) {
        setInput(input);
        char result = InputManager.readChar("Lütfen bir sembol girin: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] validSymbolData() {
        return new Object[][]{
                {"*\n", '*'},
                {"#\n", '#'},
                {"+\n", '+'},
                {"/\n", '/'}
        };
    }

    @Test(dataProvider = "invalidThenValidSymbolData")
    public void testReadCharWithInvalidInput(String input, char expected) {
        setInput(input);
        char result = InputManager.readChar("Lütfen bir sembol girin: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] invalidThenValidSymbolData() {
        return new Object[][]{
                {"abc\n*\n", '*'},
                {"123\n#\n", '#'},
                {"@\n+\n", '+'}
        };
    }

    @Test(dataProvider = "validSelectionData")
    public void testReadValidatedSelection(String input, int expected) {
        setInput(input);
        int result = InputManager.readValidatedSelection("Lütfen bir seçim yapın: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] validSelectionData() {
        return new Object[][]{
                {"1\n", 1},
                {"12\n", 12},
                {"5\n", 5}
        };
    }

    @Test(dataProvider = "invalidThenValidSelectionData")
    public void testReadValidatedSelectionWithInvalidInput(String input, int expected) {
        setInput(input);
        int result = InputManager.readValidatedSelection("Lütfen bir seçim yapın: ");
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] invalidThenValidSelectionData() {
        return new Object[][]{
                {"abc\n1\n", 1},
                {"15\n12\n", 12},
                {"@\n9\n", 9}
        };
    }

    @Test(dataProvider = "formatChoiceData")
    public void testReadFormatChoice(String input, String expected) {
        setInput(input);
        String result = InputManager.readFormatChoice();
        assertEquals(result, expected);
    }

    @DataProvider
    public Object[][] formatChoiceData() {
        return new Object[][]{
                {"1\n", "1"},
                {"2\n", "2"}
        };
    }

    private void setInput(String data) {
        System.setIn(new ByteArrayInputStream(data.getBytes()));
        InputManager.scanner = new Scanner(System.in);
    }
}
