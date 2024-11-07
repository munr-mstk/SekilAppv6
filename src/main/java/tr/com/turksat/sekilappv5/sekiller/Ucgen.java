package tr.com.turksat.sekilappv5.sekiller;

import tr.com.turksat.sekilappv5.parser.JsonParser;
import tr.com.turksat.sekilappv5.util.LogUtil;

public class Ucgen implements Sekil {

    private String type="Ucgen";
    private int yukseklik; // Üçgenin yüksekliği
    private char sembol; // Üçgeni temsil eden sembol
    private static final char DEFAULT_SYMBOL = '*';

    public Ucgen(int yukseklik, char sembol) {
        this.yukseklik = yukseklik; // Yüksekliği ayarla
        this.sembol = sembol == 0 ? DEFAULT_SYMBOL : sembol; // Sembolü ayarla, eğer sıfır ise varsayılan sembolü kullan
    }

    @Override
    public String toString() {
        return type+", Yükseklik: " + yukseklik + ", Sembol: " + sembol;
    }

    @Override
    public void ciz() {
        for (int i = 0; i < yukseklik; i++) {
            // Boşlukları yazdır
            for (int j = 0; j < yukseklik - i - 1; j++) {
                System.out.print("  "); // Üst kısımda boşluk bırak
            }
            // Üçgenin sembollerini yazdır
            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(sembol + " "); // Sembolü yazdır
            }
            System.out.println(); // Satır sonu
        }
        // Üçgenin alanını ve çevresini logla
        LogUtil.log("Üçgen alanı: " + alanHesapla());
        LogUtil.log("Üçgen çevresi: " + cevreHesapla());
    }

    @Override
    public void sembolDegistir(char yeniSembol) {
        this.sembol = yeniSembol; // Yeni sembolü ayarla
    }

    @Override
    public double alanHesapla() {
        int taban = 2 * (yukseklik - 1) + 1; // Üçgenin tabanı
        return (taban * yukseklik) / 2.0; // Alan hesaplama
    }

    @Override
    public double cevreHesapla() {
        int taban = 2 * (yukseklik - 1) + 1; // Üçgenin tabanı
        // Çevre = taban + 2 * (kenar uzunluğu) formülü ile hesaplanır
        return taban + 2 * Math.sqrt(Math.pow(taban / 2.0, 2) + Math.pow(yukseklik, 2));
    }

    @Override
    public String toJson() {
        return JsonParser.toJson(this);
    }
}
