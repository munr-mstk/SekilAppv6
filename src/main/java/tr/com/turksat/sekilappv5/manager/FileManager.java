package tr.com.turksat.sekilappv5.manager;

import tr.com.turksat.sekilappv5.io.OkumaYazma;
import tr.com.turksat.sekilappv5.sekiller.Sekil;
import java.io.*;
import java.util.List;

public class FileManager {

    public enum Format {
        NORMAL, JSON
    }

    private static Format format;

    public FileManager(Format format) {
        this.format = format;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public static void dosyadanSekilleriOku(String dosyaYolu, List<Sekil> sekilListesi) throws IOException {
        if (format == Format.JSON) {
            OkumaYazma.dosyadanSekilleriJsonOku(dosyaYolu, sekilListesi);
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(dosyaYolu))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Sekil sekil = Sekil.fromString(line);
                    sekilListesi.add(sekil);
                }
            } catch (IOException e) {
                throw new IOException("Dosyadan okuma sırasında hata oluştu: " + e.getMessage(), e);
            }
        }
    }

    public static void listeyiDosyayaYaz(String dosyaYolu, List<Sekil> sekilListesi) throws IOException {
        if (format == Format.JSON) {
            OkumaYazma.listeyiDosyayaJsonYaz(dosyaYolu, sekilListesi);
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaYolu))) {
                for (Sekil sekil : sekilListesi) {
                    // Şekli normal formatta yazma işlemi yapılacak.
                    writer.write(sekil.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new IOException("Dosyaya yazma sırasında hata oluştu: " + e.getMessage(), e);
            }
        }
    }
}
