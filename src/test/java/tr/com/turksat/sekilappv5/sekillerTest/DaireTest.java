package tr.com.turksat.sekilappv5.sekillerTest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tr.com.turksat.sekilappv5.sekiller.Daire;


public class DaireTest {

    @DataProvider(name = "alanHesaplaDataProvider")
    public Object[][] alanHesaplaDataProvider() {
        return new Object[][]{
                {10, Math.PI * 25},
                {20, Math.PI * 100},
                {30, Math.PI * 225},
        };
    }

    @DataProvider(name = "cevreHesaplaDataProvider")
    public Object[][] cevreHesaplaDataProvider() {
        return new Object[][]{
                {10, Math.PI * 10},
                {20, Math.PI * 20},
                {30, Math.PI * 30},
        };
    }
    @Test(dataProvider = "alanHesaplaDataProvider")
    public void testAlanHesapla(int cap, double expectedAlan) {
        Daire daire = new Daire(cap, '*');
        double actualAlan = daire.alanHesapla();
        Assert.assertEquals(actualAlan, expectedAlan, 0.01, "Daire alanı hesaplama hatası!");
    }
    @Test(dataProvider = "cevreHesaplaDataProvider")
    public void testCevreHesapla(int cap, double expectedCevre) {
        Daire daire = new Daire(cap, '*');
        double actualCevre = daire.cevreHesapla();
        Assert.assertEquals(actualCevre, expectedCevre, 0.01, "Daire çevresi hesaplama hatası!");
    }
    @Test
    public void testSembolDegistir() {
        Daire daire = new Daire(10, '*');
        char yeniSembol = '#';
        daire.sembolDegistir(yeniSembol);
        Assert.assertEquals(daire.toString(), "Daire, Çap: 10, Sembol: #", "Sembol değiştirme hatası!");
    }

    @Test
    public void testCiz() {
        Daire daire = new Daire(10, '*');
        try {
            daire.ciz();
        } catch (Exception e) {
            Assert.fail("Daire çiziminde hata oluştu: " + e.getMessage());
        }
    }


}
