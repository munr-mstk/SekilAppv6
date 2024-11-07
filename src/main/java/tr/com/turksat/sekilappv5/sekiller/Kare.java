package tr.com.turksat.sekilappv5.sekiller;

import tr.com.turksat.sekilappv5.parser.JsonParser;
import tr.com.turksat.sekilappv5.util.LogUtil;

public class Kare implements Sekil {

    private String type = "Kare";
    private int boyut;
    private char sembol;
    private static final char DEFAULT_SYMBOL = '*';

    /**
     * Kare nesnesini başlatır.
     *
     * @param boyut Karenin kenar uzunluğunu temsil eder.
     * @param sembol Karenin sembolünü belirler. Sıfır (0) belirtilirse varsayılan sembol kullanılır.
     */
    public Kare(int boyut, char sembol) {
        this.boyut = boyut;
        this.sembol = sembol != 0 ? sembol : DEFAULT_SYMBOL;
    }

    /**
     * Karenin özelliklerini içeren bir String döndürür.
     *
     * @return Karenin tipi, boyutu ve sembolü hakkında bilgi içeren bir String.
     */
    @Override
    public String toString() {
        return type + ", boyut: " + boyut + ", sembol: " + sembol;
    }

    /**
     * Karenin şeklinin ekranda çizilmesini sağlar.
     *
     * Karenin kenar uzunluğu kadar satır ve sütun kullanılarak sembol ile şekil çizilir.
     * Ayrıca karenin alanı ve çevresi hesaplanarak loglanır.
     */
    @Override
    public void ciz() {

        for (int i = 0; i < boyut; i++) {

            for (int j = 0; j < boyut; j++) {
                System.out.print(sembol + " ");
            }
            // Bir satır tamamlandıktan sonra alt satıra geç
            System.out.println();
        }
        // Karenin alanı ve çevresi hesaplanıp loglanır
        LogUtil.log("Kare alanı: " + alanHesapla());
        LogUtil.log("Kare çevresi: " + cevreHesapla());
    }

    /**
     * Karenin sembolünü değiştirir.
     *
     * @param yeniSembol Karenin yeni sembolüdür.
     */
    @Override
    public void sembolDegistir(char yeniSembol) {
        this.sembol = yeniSembol;
    }

    /**
     * Karenin alanını hesaplar.
     *
     * @return Karenin alanını döndürür.
     */
    @Override
    public double alanHesapla() {
        return boyut * boyut; // Alan = boyut * boyut
    }

    /**
     * Karenin çevresini hesaplar.
     *
     * @return Karenin çevresini döndürür.
     */
    @Override
    public double cevreHesapla() {
        return 4 * boyut; // Çevre = 4 * boyut
    }

    /**
     * Karenin özelliklerini JSON formatında döndürür.
     *
     * @return Karenin JSON formatında temsilini döndürür.
     */
    @Override
    public String toJson() {
        return JsonParser.toJson(this); // JSON formatına dönüştür
    }
}
