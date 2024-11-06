package tr.com.turksat.sekilappv5;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

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
}