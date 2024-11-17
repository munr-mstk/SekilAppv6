package tr.com.turksat.sekilappv6.sekillerTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv6.sekiller.Ucgen;

public class UcgenTest {

    @DataProvider(name = "alanHesaplaDataProvider")
    public Object[][] alanHesaplaDataProvider() {
        return new Object[][]{
                {3},
                {5},
                {7}
        };
    }

    @DataProvider(name = "cevreHesaplaDataProvider")
    public Object[][] cevreHesaplaDataProvider() {
        return new Object[][]{
                {3},
                {5},
                {7}
        };
    }
    @Test(dataProvider = "alanHesaplaDataProvider")
    public void testAlanHesapla(int yukseklik) {
        Ucgen ucgen = new Ucgen(yukseklik, '*');
        double expectedAlan = ((2 * (yukseklik - 1) + 1) * yukseklik) / 2.0;
        double actualAlan = ucgen.alanHesapla();
        Assert.assertEquals(actualAlan, expectedAlan, 0.01, "Üçgenin alanı yanlış hesaplandı!");
    }

    @Test(dataProvider = "cevreHesaplaDataProvider")
    public void testCevreHesapla(int yukseklik) {
        Ucgen ucgen = new Ucgen(yukseklik, '*');
        double taban = 2 * (yukseklik - 1) + 1;
        double expectedCevre = taban + 2 * Math.sqrt(Math.pow(taban / 2.0, 2) + Math.pow(yukseklik, 2)); // Çevre formülü
        double actualCevre = ucgen.cevreHesapla();
        Assert.assertEquals(actualCevre, expectedCevre, 0.01, "Üçgenin çevresi yanlış hesaplandı!");
    }

    @Test
    public void testSembolDegistir() {
        Ucgen ucgen = new Ucgen(4, '*');
        char yeniSembol = '#';
        ucgen.sembolDegistir(yeniSembol);
        Assert.assertTrue(ucgen.toString().contains("Sembol: #"), "Sembol değiştirme işlemi başarısız!");
    }

    @Test
    public void testCiz() {
        Ucgen ucgen = new Ucgen(3, '*');
        try {
            ucgen.ciz();
        } catch (Exception e) {
            Assert.fail("Üçgenin çiziminde hata oluştu: " + e.getMessage());
        }
    }

    @Test
    public void testToString() {
        Ucgen ucgen = new Ucgen(3, '*');
        String expectedString = "Üçgen, Yükseklik: 3, Sembol: *";
        Assert.assertEquals(ucgen.toString(), expectedString, "toString metodu beklenmeyen bir sonuç döndürdü!");
    }

    @Test
    public void testToJson() {
        Ucgen ucgen = new Ucgen(5, '*');
        String json = ucgen.toJson();
        Assert.assertTrue(json.contains("\"type\":\"Üçgen\""), "JSON formatı hatalı!");
        Assert.assertTrue(json.contains("\"yukseklik\":5"), "JSON'da yükseklik hatalı!");
        Assert.assertTrue(json.contains("\"sembol\":\"*\""), "JSON'da sembol hatalı!");
    }


}


