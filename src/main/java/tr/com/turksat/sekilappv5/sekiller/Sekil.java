package tr.com.turksat.sekilappv5.sekiller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import tr.com.turksat.sekilappv5.util.LogUtil;

public interface Sekil {




    void ciz();
    void sembolDegistir(char yeniSembol);
    double alanHesapla();
    double cevreHesapla();

    String toJson(); // JSON formatında döndür

    static Sekil fromJson(String json) {
        Gson gson = new Gson();
        JsonObject jsonObject = com.google.gson.JsonParser.parseString(json).getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        try {
            switch (type) {
                case "Kare":
                    return gson.fromJson(json, Kare.class);
                case "Dikdortgen":
                    return gson.fromJson(json, Dikdortgen.class);
                case "Daire":
                    return gson.fromJson(json, Daire.class);
                case "Üçgen":
                    return gson.fromJson(json, Ucgen.class);
                default:
                    LogUtil.log("Geçersiz şekil tipi: " + type);
                    return null;
            }
        } catch (JsonParseException e) {
            LogUtil.log("Hata oluştu: " + e.getMessage());
            return null;
        }
    }

    static Sekil fromString(String sekilStr) {
        try {
            String[] parts = sekilStr.split(", ");
            String type = parts[0].trim();
            String values = parts[1].trim();

            switch (type) {
                case "Kare":
                    int boyut = Integer.parseInt(values.split(":")[1].trim());
                    return new Kare(boyut, '*');
                case "Dikdortgen":
                    String[] dimensions = values.split(":")[1].trim().split(",");
                    int genislik = Integer.parseInt(dimensions[0].trim());
                    int yukseklik = Integer.parseInt(dimensions[1].trim());
                    return new Dikdortgen(genislik, yukseklik, '*');
                case "Daire":
                    int cap = Integer.parseInt(values.split(":")[1].trim());
                    return new Daire(cap, '*');
                case "Üçgen":
                    int yukseklikUcgen = Integer.parseInt(values.split(":")[1].trim());
                    return new Ucgen(yukseklikUcgen, '*');
                default:
                    LogUtil.log("Geçersiz şekil tipi: " + type);
            }
        } catch (Exception e) {
            LogUtil.log("Hata oluştu: " + e.getMessage() + " için satır: " + sekilStr);
        }

        return null;
    }
}