package tr.com.turksat.sekilappv5.parser;

import com.google.gson.*;
import tr.com.turksat.sekilappv5.sekiller.Sekil;
import tr.com.turksat.sekilappv5.sekiller.Daire;
import tr.com.turksat.sekilappv5.sekiller.Dikdortgen;
import tr.com.turksat.sekilappv5.sekiller.Kare;
import tr.com.turksat.sekilappv5.sekiller.Ucgen;

import java.io.*;
import java.util.*;

public class SekilParser {

    /**
     * JSON formatındaki bir dosyayı okuyarak içerisindeki şekilleri bir listeye dönüştürür.
     *
     * @param file Şekillerin bulunduğu JSON formatındaki dosya.
     * @return Şekilleri içeren listeyi döndürür.
     */
    public static List<Sekil> parseSekiller(File file) {
        List<Sekil> sekilListesi = new ArrayList<>();
        Gson gson = new Gson();

        try (Reader reader = new FileReader(file)) {
            JsonArray jsonArray = JsonParser.parseReader(reader).getAsJsonArray();

            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                Sekil sekil = createSekilFromJson(jsonObject, gson);

                if (sekil != null) {
                    sekilListesi.add(sekil);
                }
            }
        } catch (IOException e) {
            System.out.println("Dosya okunurken hata oluştu: " + e.getMessage());
        }

        return sekilListesi;
    }

    /**
     * JSON nesnesine göre uygun şekil sınıfını oluşturur.
     *
     * @param jsonObject JSON nesnesi, şekil bilgilerini içerir.
     * @param gson Gson nesnesi, JSON verisini Java nesnelerine dönüştürmek için kullanılır.
     * @return Oluşturulan şekil nesnesi ya da geçersiz tip durumunda null.
     */
    private static Sekil createSekilFromJson(JsonObject jsonObject, Gson gson) {
        if (jsonObject.has("type")) {
            String type = jsonObject.get("type").getAsString();
            switch (type) {
                case "Kare":
                    return gson.fromJson(jsonObject, Kare.class);
                case "Dikdortgen":
                    return gson.fromJson(jsonObject, Dikdortgen.class);
                case "Daire":
                    return gson.fromJson(jsonObject, Daire.class);
                case "Üçgen":
                    return gson.fromJson(jsonObject, Ucgen.class);
                default:
                    System.out.println("Geçersiz şekil tipi: " + type);
                    return null;
            }
        } else {
            System.out.println("JSON nesnesinde 'type' alanı bulunamadı.");
            return null;
        }
    }
}
