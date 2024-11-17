package tr.com.turksat.sekilappv6;

import tr.com.turksat.sekilappv6.io.OkumaYazma;
import tr.com.turksat.sekilappv6.manager.FileManager;
import tr.com.turksat.sekilappv6.manager.InputManager;
import tr.com.turksat.sekilappv6.manager.OutputManager;
import tr.com.turksat.sekilappv6.sekiller.*;
import tr.com.turksat.sekilappv6.util.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class Main {
    private static String java_calismalarim;
    private static List<Sekil> sekilListesi = new ArrayList<>();

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream input = Main.class.getClassLoader().getResourceAsStream("config.properties")) {
            properties.load(input);
            java_calismalarim = properties.getProperty("data.file.path");
            OutputManager.print("Dosya Adı: " + java_calismalarim);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Sekil seciliSekil = null;
        char sembol = '*';

        while (true) {
            try {
                OutputManager.print("");
                OutputManager.print("Seçenekler:");
                OutputManager.print("1: Küçük Kare çiz");
                OutputManager.print("2: İstediğin boyutta kare çiz");
                OutputManager.print("3: Dikdörtken çiz");
                OutputManager.print("4: Üçgen çiz");
                OutputManager.print("5: Daire çiz");
                OutputManager.print("6: Toplam alan ve çevre bilgilerini göster");
                OutputManager.print("7: Alan ve çevre hesaplamalarını sıfırla");
                OutputManager.print("8: Yeni Sembol ");
                OutputManager.print("9: Şekilleri dosyadan oku");
                OutputManager.print("10: Şekilleri dosyalara kaydet");
                OutputManager.print("11: Listeyi temizle");
                OutputManager.print("12: Çıkış");
                OutputManager.printWithPrompt("Seçiminiz: ");

                int secim = InputManager.readValidatedSelection("");

                switch (secim) {
                    case 1:
                        seciliSekil = new Kare(3, sembol);
                        sekilListesi.add(seciliSekil);
                        seciliSekil.ciz();
                        break;
                    case 2:
                        int kareBoyut = InputManager.readUnrestrictedInt("Kare boyutunu girin: ");
                        seciliSekil = new Kare(kareBoyut, sembol);
                        sekilListesi.add(seciliSekil);
                        seciliSekil.ciz();
                        break;
                    case 3:
                        int genislik = InputManager.readUnrestrictedInt("Dikdörtgen genişliğini girin: ");
                        int yukseklik = InputManager.readUnrestrictedInt("Dikdörtgen yüksekliğini girin: ");
                        seciliSekil = new Dikdortgen(genislik, yukseklik, sembol);
                        sekilListesi.add(seciliSekil);
                        seciliSekil.ciz();
                        break;
                    case 4:
                        int yukselik = InputManager.readUnrestrictedInt("Üçgenin yüksekliğini girin: ");
                        seciliSekil = new Ucgen(yukselik, sembol);
                        sekilListesi.add(seciliSekil);
                        seciliSekil.ciz();
                        break;
                    case 5:
                        int cap = InputManager.readUnrestrictedInt("Dairenin çapını girin: ");
                        seciliSekil = new Daire(cap, sembol);
                        sekilListesi.add(seciliSekil);
                        seciliSekil.ciz();
                        break;
                    case 6:
                        hesaplaToplamAlanVeCevre();
                        break;
                    case 7:
                        sekilListesi.clear();
                        OutputManager.print("Şekil listesi sıfırlandı.");
                        break;
                    case 8:
                        LogUtil.log("Seçebileceğin semboller * , #, +, / dır ");
                        sembol = InputManager.readChar("Yeni sembolü girin: ");

                        if (seciliSekil != null) {
                            seciliSekil.sembolDegistir(sembol);
                            seciliSekil.ciz();
                        }
                        break;
                    case 9:
                        OutputManager.print("Seçenek 1 (JSON) veya Seçenek 2 (Düz dosya) okuma işlemi başlıyor...");
                        int okumaSecim = InputManager.readInt("Seçiminizi yapın (1: JSON, 2: Düz dosya): ");
                        if (okumaSecim == 1) {
                            OkumaYazma.dosyadanSekilleriJsonOku("sekillerJson.txt", sekilListesi);
                            OutputManager.print("Şekiller JSON dosyasından başarıyla okundu.");
                        } else if (okumaSecim == 2) {
                            OkumaYazma.dosyadanSekilleriNormalOku("sekiller.txt", sekilListesi);
                            OutputManager.print("Şekiller düz dosyadan başarıyla okundu.");
                        }
                        break;
                    case 10:
                        int yazmaSecim = InputManager.readInt("1: Şekilleri JSON dosyasına yaz\n2: Şekilleri düz dosyasına yaz\nSeçiminiz: ");
                        if (yazmaSecim == 1) {
                            OkumaYazma.listeyiDosyayaJsonYaz("sekillerJson.txt", sekilListesi);
                            OutputManager.print("Şekiller JSON dosyasına yazıldı.");
                        } else if (yazmaSecim == 2) {
                            FileManager.listeyiDosyayaYaz("sekiller.txt", sekilListesi);
                            OutputManager.print("Şekiller düz dosyasına yazıldı.");
                        }
                        break;
                    case 11:
                        sekilListesi.clear();
                        OutputManager.print("Şekil listesi sıfırlandı.");
                        break;
                    case 12:
                        OutputManager.print("Programdan çıkılıyor...");
                        InputManager.closeScanner();
                        return;
                    default:
                        OutputManager.print("Geçersiz seçim. Lütfen 1 ile 12 arasında bir sayı girin.");
                }
            } catch (Exception e) {
                OutputManager.print("Beklenmeyen bir hata oluştu: " + e.getMessage());
            }
        }
    }

    private static void hesaplaToplamAlanVeCevre() {
        double toplamAlan = 0;
        double toplamCevre = 0;

        for (Sekil sekil : sekilListesi) {
            toplamAlan += sekil.alanHesapla();
            toplamCevre += sekil.cevreHesapla();
        }

        OutputManager.print("Toplam Alan: " + toplamAlan);
        OutputManager.print("Toplam Çevre: " + toplamCevre);
    }
}