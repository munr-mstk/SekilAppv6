package tr.com.turksat.sekilappv5.sekiller;

import tr.com.turksat.sekilappv5.parser.JsonParser;
import tr.com.turksat.sekilappv5.util.LogUtil;

public class Dikdortgen implements Sekil {

    private String type = "Dikdortgen";
    private int genislik;
    private int yukseklik;
    private char sembol;
    private static final char DEFAULT_SYMBOL = '*';

    /**
     * Dikdörtgen nesnesini başlatır.
     *
     * @param genislik Dikdörtgenin genişliğini temsil eder.
     * @param yukseklik Dikdörtgenin yüksekliğini temsil eder.
     * @param sembol Dikdörtgenin sembolünü belirler. Sıfır (0) belirtilirse varsayılan sembol kullanılır.
     */
    public Dikdortgen(int genislik, int yukseklik, char sembol) {
        this.genislik = genislik;
        this.yukseklik = yukseklik;
        this.sembol = sembol != 0 ? sembol : DEFAULT_SYMBOL;
    }

    /**
     * Dikdörtgenin özelliklerini içeren bir String döndürür.
     *
     * @return Dikdörtgenin tipi, genişliği, yüksekliği ve sembolü hakkında bilgi içeren bir String.
     */
    @Override
    public String toString() {
        return "Tip: " + type + ", Genişlik: " + genislik + ", Yükseklik: " + yukseklik + ", Sembol: " + sembol;
    }

    /**
     * Dikdörtgeni ekranda çizen bir metottur.
     *
     * Dikdörtgenin genişliği ve yüksekliği kullanılarak şekil ekranda çizilir.
     * Ayrıca dikdörtgenin alanı ve çevresi hesaplanarak loglanır.
     */
    @Override
    public void ciz() {

        for (int i = 0; i < yukseklik; i++) {
            for (int j = 0; j < genislik; j++) {
                System.out.print(sembol + " ");
            }
            System.out.println();
        }
        LogUtil.log("Dikdörtgen alanı: " + alanHesapla());
        LogUtil.log("Dikdörtgen çevresi: " + cevreHesapla());
    }

    /**
     * Dikdörtgenin sembolünü değiştirir.
     *
     * @param yeniSembol Dikdörtgenin yeni sembolüdür.
     */
    @Override
    public void sembolDegistir(char yeniSembol) {
        this.sembol = yeniSembol;
    }

    /**
     * Dikdörtgenin alanını hesaplar.
     *
     * @return Dikdörtgenin alanını döndürür.
     */
    @Override
    public double alanHesapla() {
        return genislik * yukseklik;
    }

    /**
     * Dikdörtgenin çevresini hesaplar.
     *
     * @return Dikdörtgenin çevresini döndürür.
     */
    @Override
    public double cevreHesapla() {
        return 2 * (genislik + yukseklik);
    }

    /**
     * Dikdörtgenin özelliklerini JSON formatında döndürür.
     *
     * @return Dikdörtgenin JSON formatında temsilini döndürür.
     */
    @Override
    public String toJson() {
        return JsonParser.toJson(this);
    }
}