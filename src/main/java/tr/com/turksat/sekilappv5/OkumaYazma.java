package tr.com.turksat.sekilappv5;

import com.google.gson.Gson;

import java.io.*;
import java.util.List;

public class OkumaYazma {
    private static double toplamAlan = 0;
    private static double toplamCevre = 0;
    private static final Gson gson = new Gson();

    /**
     * Dosyadan şekilleri okur ve listeye ekler.
     *
     * @param dosyaAdi Dosyanın adı ve yolunu belirtir.
     * @param sekilListesi Şekillerin ekleneceği listeyi belirtir.
     */
    public static void dosyadanSekilleriOku(String dosyaAdi, List<Sekil> sekilListesi) {
        try {
            File file = new File(dosyaAdi);
            List<Sekil> sekiller = SekilParser.parseSekiller(file);

            for (Sekil yeniSekil : sekiller) {
                sekilListesi.add(yeniSekil);
                toplamAlan += yeniSekil.alanHesapla();
                toplamCevre += yeniSekil.cevreHesapla();

                System.out.println(yeniSekil); // Şekli ekrana yazdır
            }
            LogUtil.log("Şekiller başarıyla dosyadan okundu ve listeye eklendi.");
        } catch (Exception e) {
            LogUtil.log("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }

    /**
     * Şekil listesini belirtilen dosyaya JSON formatında yazar.
     *
     * @param dosyaAdi Dosyanın adı ve yolunu belirtir.
     * @param sekilListesi Yazılacak şekil listesini belirtir.
     */
    public static void listeyiDosyayaYaz(String dosyaAdi, List<Sekil> sekilListesi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            String json = gson.toJson(sekilListesi);
            bw.write(json);
            LogUtil.log("Şekil listesi başarıyla dosyaya kaydedildi.");
        } catch (IOException e) {
            LogUtil.log("Dosya yazılırken hata oluştu: " + e.getMessage());
        }
    }

    /**
     * Şekil listesini ve toplam alan/çevre değerlerini sıfırlar.
     *
     * @param sekilListesi Şekil listesinin sıfırlanacağı listeyi belirtir.
     */
    public static void listeyiSifirla(List<Sekil> sekilListesi) {
        sekilListesi.clear(); // Listeyi temizle
        toplamAlan = 0; // Toplam alanı sıfırla
        toplamCevre = 0; // Toplam çevreyi sıfırla
        LogUtil.log("Liste ve toplam alan/çevre başarıyla sıfırlandı.");
    }

    /**
     * Toplam alanı döndürür.
     *
     * @return Toplam alanı döndürür.
     */
    public static double getToplamAlan() {
        return toplamAlan;
    }

    /**
     * Toplam çevreyi döndürür.
     *
     * @return Toplam çevreyi döndürür.
     */
    public static double getToplamCevre() {
        return toplamCevre;
    }
}
