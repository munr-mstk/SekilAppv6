package tr.com.turksat.sekilappv5.parserTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv5.parser.SekilParser;
import tr.com.turksat.sekilappv5.sekiller.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class parserTest {
    private File createTestJsonFile() throws IOException {
        File tempFile = File.createTempFile("sekil_test", ".json");
        tempFile.deleteOnExit();

        String jsonContent = "[" +
                "{\"type\": \"Kare\", \"boyut\": 5, \"sembol\": \"*\"}," +
                "{\"type\": \"Dikdortgen\", \"genislik\": 4, \"yukseklik\": 6, \"sembol\": \"#\"}," +
                "{\"type\": \"Daire\", \"cap\": 3, \"sembol\": \"@\"}," +
                "{\"type\": \"Üçgen\", \"yukseklik\": 7, \"sembol\": \"+\"}" +
                "]";

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(jsonContent);
        }

        return tempFile;
    }

    private Object getFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field field = object.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return field.get(object);
    }

    @Test
    public void testParseSekiller() throws IOException, NoSuchFieldException, IllegalAccessException {
        File jsonFile = createTestJsonFile();
        List<Sekil> sekiller = SekilParser.parseSekiller(jsonFile);

        // Genel testler
        Assert.assertNotNull(sekiller, "Şekiller listesi null olmamalıdır.");
        Assert.assertEquals(sekiller.size(), 4, "Şekiller listesinde 4 eleman olmalı.");

        // Kare testi
        Assert.assertTrue(sekiller.get(0) instanceof Kare);
        Kare kare = (Kare) sekiller.get(0);
        Assert.assertEquals(getFieldValue(kare, "boyut"), 5);
        Assert.assertEquals(getFieldValue(kare, "sembol"), '*');

        // Dikdörtgen testi
        Assert.assertTrue(sekiller.get(1) instanceof Dikdortgen);
        Dikdortgen dikdortgen = (Dikdortgen) sekiller.get(1);
        Assert.assertEquals(getFieldValue(dikdortgen, "genislik"), 4);
        Assert.assertEquals(getFieldValue(dikdortgen, "yukseklik"), 6);
        Assert.assertEquals(getFieldValue(dikdortgen, "sembol"), '#');

        // Daire testi
        Assert.assertTrue(sekiller.get(2) instanceof Daire);
        Daire daire = (Daire) sekiller.get(2);
        Assert.assertEquals(getFieldValue(daire, "cap"), 3);
        Assert.assertEquals(getFieldValue(daire, "sembol"), '@');

        // Üçgen testi
        Assert.assertTrue(sekiller.get(3) instanceof Ucgen);
        Ucgen ucgen = (Ucgen) sekiller.get(3);
        Assert.assertEquals(getFieldValue(ucgen, "yukseklik"), 7);
        Assert.assertEquals(getFieldValue(ucgen, "sembol"), '+');
    }

    @Test
    public void testEmptyJsonFile() throws IOException {
        File tempFile = File.createTempFile("empty_test", ".json");
        tempFile.deleteOnExit();

        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write("[]");
        }

        List<Sekil> sekiller = SekilParser.parseSekiller(tempFile);

        Assert.assertNotNull(sekiller, "Şekiller listesi null olmamalıdır.");
        Assert.assertEquals(sekiller.size(), 0, "Şekiller listesi boş olmalı.");
    }

}

