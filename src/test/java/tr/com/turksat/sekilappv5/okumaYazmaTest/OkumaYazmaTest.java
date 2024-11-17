package tr.com.turksat.sekilappv5.okumaYazmaTest;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv5.io.OkumaYazma;
import tr.com.turksat.sekilappv5.manager.FileManager;
import tr.com.turksat.sekilappv5.sekiller.Daire;
import tr.com.turksat.sekilappv5.sekiller.Kare;
import tr.com.turksat.sekilappv5.sekiller.Sekil;
import tr.com.turksat.sekilappv5.sekiller.Ucgen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OkumaYazmaTest {

    private static final String TEST_FILE_JSON = "testSekiller.json";
    private static final String TEST_FILE_NORMAL = "testSekiller.txt";
    private List<Sekil> sekilListesi;

    @BeforeMethod
    public void setUp() {
        sekilListesi = new ArrayList<>();
    }



    @DataProvider(name = "jsonFileProvider")
    public Object[][] jsonFileProvider() {
        return new Object[][]{
                {"valid.json", """
                [
                    {"type":"Kare","boyut":19,"sembol":"/"},
                    {"type":"Daire","yaricap":14,"sembol":"#"}
                    {"type":"Ucgen","yukseklik":35,"sembol":"*"}
                    
                ]
                """, true},

        };
    }

    @DataProvider(name = "normalFileProvider")
    public Object[][] normalFileProvider() {
        return new Object[][]{
                {"valid.txt", """
                Kare, boyut: 19, sembol: /
                Daire, boyut: 14, sembol: #
                Ucgen, yukseklik: 35,sembol:*
                
                """, true}

        };
    }

    // ** Testler **

    @Test(dataProvider = "jsonFileProvider")
    public void testDosyadanSekilleriJsonOku(String fileName, String content, boolean expectedResult) throws IOException {
        // Dosya oluştur
        if (content != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(content);
            }
        }

        boolean success = true;
        try {
            OkumaYazma.dosyadanSekilleriJsonOku(fileName, sekilListesi);
        } catch (Exception e) {
            success = false;
        }

        Assert.assertEquals(success, expectedResult, "Beklenen sonuç ile uyuşmuyor.");
        if (expectedResult) {
            System.out.println("Okunan şekiller: " + sekilListesi);
        }
    }

    @Test
    public void testListeyiDosyayaJsonYaz() {
        sekilListesi.add(new Kare(19, '/'));
        sekilListesi.add(new Daire(14, '#'));
        sekilListesi.add(new Ucgen(35, '*'));

        OkumaYazma.listeyiDosyayaJsonYaz(TEST_FILE_JSON, sekilListesi);

        File file = new File(TEST_FILE_JSON);
        Assert.assertTrue(file.exists(), "JSON dosyası oluşturulmalı.");
    }

    @Test(dataProvider = "normalFileProvider")
    public void testDosyadanSekilleriNormalOku(String fileName, String content, boolean expectedResult) throws IOException {
        // Dosya oluştur
        if (content != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                writer.write(content);
            }
        }

        boolean success = true;
        try {
            OkumaYazma.dosyadanSekilleriNormalOku(fileName, sekilListesi);
        } catch (Exception e) {
            success = false;
        }

        Assert.assertEquals(success, expectedResult, "Beklenen sonuç ile uyuşmuyor.");
        if (expectedResult) {
            System.out.println("Okunan şekiller: " + sekilListesi);
        }
    }

    @Test
    public void testListeyiDosyayaYaz() throws IOException {
        sekilListesi.add(new Kare(19, '/'));
        sekilListesi.add(new Daire(14, '#'));
        sekilListesi.add(new Ucgen(35, '*'));

        FileManager manager = new FileManager(FileManager.Format.NORMAL);
        manager.listeyiDosyayaYaz(TEST_FILE_NORMAL, sekilListesi);

        File file = new File(TEST_FILE_NORMAL);
        Assert.assertTrue(file.exists(), "Normal dosyası oluşturulmalı.");
    }

    @AfterMethod
    public void tearDown() {
        sekilListesi.clear();
        new File(TEST_FILE_JSON).delete();
        new File(TEST_FILE_NORMAL).delete();
    }
}
