package tr.com.turksat.sekilappv6.io;

import com.google.gson.Gson;
import tr.com.turksat.sekilappv6.parser.SekilParser;
import tr.com.turksat.sekilappv6.sekiller.Sekil;
import tr.com.turksat.sekilappv6.util.LogUtil;


import java.io.*;
import java.util.List;
import java.util.Scanner;

public class OkumaYazma {
    private static double toplamAlan = 0;
    private static double toplamCevre = 0;
    private static final Gson gson = new Gson();


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


    public static void listeyiDosyayaJsonYaz(String dosyaAdi, List<Sekil> sekilListesi) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(dosyaAdi))) {
            String json = gson.toJson(sekilListesi);
            bw.write(json);
            LogUtil.log("Şekil listesi başarıyla dosyaya kaydedildi.");
        } catch (IOException e) {
            LogUtil.log("Dosya yazılırken hata oluştu: " + e.getMessage());
        }
    }

    public static void dosyadanSekilleriNormalOku(String dosyaAdi, List<Sekil> sekilListesi) {
        try {

            File file = new File(dosyaAdi);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Sekil yeniSekil = Sekil.fromString(line);

                if (yeniSekil != null) {
                    sekilListesi.add(yeniSekil);
                    toplamAlan += yeniSekil.alanHesapla();
                    toplamCevre += yeniSekil.cevreHesapla();

                    System.out.println(yeniSekil);
                } else {

                    LogUtil.log("Hatalı veya tanımlanamayan satır atlandı: " + line);
                }
            }
            LogUtil.log("Şekiller başarıyla düz dosyadan okundu ve listeye eklendi.");
            scanner.close();
        } catch (Exception e) {
            LogUtil.log("Dosya okunurken hata oluştu: " + e.getMessage());
        }
    }
}