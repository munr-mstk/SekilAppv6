package tr.com.turksat.sekilappv5.sekillerTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv5.sekiller.Dikdortgen;

public class DikdortgenTest {

    @DataProvider(name = "dikdortgenDataProvider")
    public Object[][] dikdortgenDataProvider() {
        return new Object[][]{
                {4, 3},
                {6, 5},
                {8, 7}
        };
    }

    @Test(dataProvider = "dikdortgenDataProvider")
    public void testAlanHesapla(int genislik, int yukseklik) {
        Dikdortgen dikdortgen = new Dikdortgen(genislik, yukseklik, '*');
        double expectedAlan = genislik * yukseklik;
        double actualAlan = dikdortgen.alanHesapla();
        Assert.assertEquals(actualAlan, expectedAlan, 0.01, "Dikdörtgenin alanı yanlış hesaplandı!");
    }

    @Test(dataProvider = "dikdortgenDataProvider")
    public void testCevreHesapla(int genislik, int yukseklik) {
        Dikdortgen dikdortgen = new Dikdortgen(genislik, yukseklik, '*');
        double expectedCevre = 2 * (genislik + yukseklik); // Çevre formülü
        double actualCevre = dikdortgen.cevreHesapla();
        Assert.assertEquals(actualCevre, expectedCevre, 0.01, "Dikdörtgenin çevresi yanlış hesaplandı!");
    }

    @Test
    public void testSembolDegistir() {
        Dikdortgen dikdortgen = new Dikdortgen(4, 3, '*');
        char yeniSembol = '#';
        dikdortgen.sembolDegistir(yeniSembol);
        Assert.assertTrue(dikdortgen.toString().contains("Sembol: #"), "Sembol değiştirme işlemi başarısız!");
    }

    @Test
    public void testCiz() {
        Dikdortgen dikdortgen = new Dikdortgen(5, 3, '*');
        try {
            dikdortgen.ciz();
        } catch (Exception e) {
            Assert.fail("Dikdörtgen çizim işlemi sırasında bir hata oluştu: " + e.getMessage());
        }
    }

    @Test
    public void testToJson() {
        Dikdortgen dikdortgen = new Dikdortgen(4, 3, '*');
        String json = dikdortgen.toJson();
        Assert.assertNotNull(json, "JSON çıktısı null dönüyor!");
        Assert.assertTrue(json.contains("\"genislik\":4"), "JSON çıktısı beklenen değeri içermiyor!");
        Assert.assertTrue(json.contains("\"yukseklik\":3"), "JSON çıktısı beklenen değeri içermiyor!");
    }
}


