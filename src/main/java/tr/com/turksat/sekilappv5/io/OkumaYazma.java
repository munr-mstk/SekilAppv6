package tr.com.turksat.sekilappv5.io;

import com.google.gson.Gson;
import tr.com.turksat.sekilappv5.parser.SekilParser;
import tr.com.turksat.sekilappv5.sekiller.Sekil;
import tr.com.turksat.sekilappv5.util.LogUtil;


import java.io.*;
import java.util.List;

public class OkumaYazma {
    private static double toplamAlan = 0;
    private static double toplamCevre = 0;
    private static final Gson gson = new Gson();

    // JSON formatında dosyadan okuma
    public static void dosyadanSekilleriJsonOku(String dosyaAdi, List<Sekil> sekilListesi) {
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

    // JSON formatında listeyi dosyaya yazma
    public static void listeyiDosyayaJsonYaz(String dosyaAdi, List<Sekil> sekilListesi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            String json = gson.toJson(sekilListesi);
            bw.write(json);
            LogUtil.log("Şekil listesi başarıyla dosyaya kaydedildi.");
        } catch (IOException e) {
            LogUtil.log("Dosya yazılırken hata oluştu: " + e.getMessage());
        }
    }


    public static void listeyiDosyayaNormalYaz(String dosyaAdi, List<Sekil> sekilListesi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            for (Sekil sekil : sekilListesi) {
                bw.write(sekil.toString());
                bw.newLine();
            }
            LogUtil.log("Şekil listesi başarıyla normal formatta dosyaya kaydedildi.");
        } catch (IOException e) {
            LogUtil.log("Dosya yazılırken hata oluştu: " + e.getMessage());
        }
    }


    public static void dosyadanSekilleriNormalOku(String dosyaAdi, List<Sekil> sekilListesi) {
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Sekil sekil = Sekil.fromString(line);
                sekilListesi.add(sekil);
            }
            LogUtil.log("Şekiller başarıyla normal formatta dosyadan okundu.");
        } catch (IOException e) {
            LogUtil.log("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }
}
