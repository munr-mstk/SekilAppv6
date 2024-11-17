package tr.com.turksat.sekilappv6.sekiller;

import tr.com.turksat.sekilappv6.parser.JsonParser;
import tr.com.turksat.sekilappv6.util.LogUtil;

public class Ucgen implements Sekil {

    private String type="Üçgen";
    private int yukseklik;
    private char sembol;
    private static final char DEFAULT_SYMBOL = '*';

    public Ucgen(int yukseklik, char sembol) {
        this.yukseklik = yukseklik;
        this.sembol = sembol == 0 ? DEFAULT_SYMBOL : sembol;
    }

    @Override
    public String toString() {
        return type+", Yükseklik: " + yukseklik + ", Sembol: " + sembol;
    }

    @Override
    public void ciz() {
        for (int i = 0; i < yukseklik; i++) {

            for (int j = 0; j < yukseklik - i - 1; j++) {
                System.out.print("  ");
            }

            for (int j = 0; j < 2 * i + 1; j++) {
                System.out.print(sembol + " ");
            }
            System.out.println();
        }

        LogUtil.log("Üçgen alanı: " + alanHesapla());
        LogUtil.log("Üçgen çevresi: " + cevreHesapla());
    }

    @Override
    public void sembolDegistir(char yeniSembol) {
        this.sembol = yeniSembol;
    }

    @Override
    public double alanHesapla() {
        int taban = 2 * (yukseklik - 1) + 1;
        return (taban * yukseklik) / 2.0;
    }

    @Override
    public double cevreHesapla() {
        int taban = 2 * (yukseklik - 1) + 1;

        return taban + 2 * Math.sqrt(Math.pow(taban / 2.0, 2) + Math.pow(yukseklik, 2));
    }

    @Override
    public String toJson() {
        return JsonParser.toJson(this);
    }
}
