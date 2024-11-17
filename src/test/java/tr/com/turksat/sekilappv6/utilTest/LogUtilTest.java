package tr.com.turksat.sekilappv6.utilTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv6.util.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogUtilTest {
    private String captureSystemOut(Runnable codeToRun) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalSystemOut = System.out;
        System.setOut(new PrintStream(outputStream));
        codeToRun.run();
        System.setOut(originalSystemOut);

        return outputStream.toString().trim();
    }


    @DataProvider(name = "messageDataProvider")
    public Object[][] messageDataProvider() {
        return new Object[][]{

                {'#', "Test mesajı 1", "#  Test mesajı 1"},
                {'@', "Başka bir test mesajı", "@  Başka bir test mesajı"},
                {'$', "Özel karakter içeren mesaj", "$  Özel karakter içeren mesaj"},
                {'*', "Sembol için log mesajı", "*  Sembol için log mesajı"}
        };
    }


    @Test(dataProvider = "messageDataProvider")
    public void testLogWithSymbolAndMessage(char symbol, String message, String expectedOutput) {

        String actualMessage = captureSystemOut(() -> LogUtil.log(symbol, message));
        Assert.assertEquals(actualMessage, expectedOutput, "Sembol ve mesaj yanlış yazdırıldı!");
    }

    @Test
    public void testLogWithMessage() {
        String expectedMessage = "Test message";
        String actualMessage = captureSystemOut(() -> LogUtil.log(expectedMessage));
        Assert.assertEquals(actualMessage, expectedMessage, "Mesaj doğru yazdırılmadı!");
    }
}

