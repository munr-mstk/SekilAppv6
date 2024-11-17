package tr.com.turksat.sekilappv6.sekillerTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv6.sekiller.Kare;

public class KareTest {@DataProvider(name = "alanHesaplaDataProvider")
public Object[][] alanHesaplaDataProvider() {
    return new Object[][]{
            {5, 25},
            {10, 100},
            {15, 225}
    };
}

    @DataProvider(name = "cevreHesaplaDataProvider")
    public Object[][] cevreHesaplaDataProvider() {
        return new Object[][]{
                {5, 20},
                {10, 40},
                {15, 60}
        };
    }

    @Test(dataProvider = "alanHesaplaDataProvider")
    public void testAlanHesapla(int boyut, double expectedAlan) {
        Kare kare = new Kare(boyut, '*');
        double actualAlan = kare.alanHesapla();
        Assert.assertEquals(actualAlan, expectedAlan, "Karenin alanı hesaplama hatası!");
    }

    @Test(dataProvider = "cevreHesaplaDataProvider")
    public void testCevreHesapla(int boyut, double expectedCevre) {
        Kare kare = new Kare(boyut, '*');
        double actualCevre = kare.cevreHesapla();
        Assert.assertEquals(actualCevre, expectedCevre, "Karenin çevresi hesaplama hatası!");
    }

    @Test
    public void testSembolDegistir() {
        Kare kare = new Kare(5, '*');
        char yeniSembol = '#';
        kare.sembolDegistir(yeniSembol);
        Assert.assertTrue(kare.toString().contains("sembol: #"), "Sembol değiştirme başarısız!");
    }

    @Test
    public void testCiz() {
        Kare kare = new Kare(3, '*');
        try {
            kare.ciz();
        } catch (Exception e) {
            Assert.fail("Karenin çiziminde hata oluştu: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        Kare kare = new Kare(4, '*');
        String expectedString = "Kare, boyut: 4, sembol: *";
        Assert.assertEquals(kare.toString(), expectedString, "toString metodu beklenmeyen bir sonuç döndürdü!");
    }

    @Test
    public void testToJson() {
        Kare kare = new Kare(5, '*');
        String json = kare.toJson();
        Assert.assertTrue(json.contains("\"type\":\"Kare\""), "JSON formatı hatalı!");
        Assert.assertTrue(json.contains("\"boyut\":5"), "JSON'da boyut hatalı!");
        Assert.assertTrue(json.contains("\"sembol\":\"*\""), "JSON'da sembol hatalı!");
    }
}
