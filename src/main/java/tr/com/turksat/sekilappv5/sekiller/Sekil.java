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
    public static Sekil fromString(String line) {
        try {
            String[] parts = line.split(", ");
            if (parts[0].contains("Kare")) {
                int boyut = Integer.parseInt(parts[1].split(": ")[1]);
                char sembol = parts[2].split(": ")[1].charAt(0);
                return new Kare(boyut, sembol);
            } else if (parts[0].contains("Dikdortgen")) {
                int genislik = Integer.parseInt(parts[1].split(": ")[1]);
                int yukseklik = Integer.parseInt(parts[2].split(": ")[1]);
                char sembol = parts[3].split(": ")[1].charAt(0);
                return new Dikdortgen(genislik, yukseklik, sembol);
            } else if (parts[0].contains("Daire")) {
                int cap = Integer.parseInt(parts[1].split(": ")[1]);
                char sembol = parts[2].split(": ")[1].charAt(0);
                return new Daire(cap, sembol);
            } else if (parts[0].contains("Ucgen")) {
                int yukseklik = Integer.parseInt(parts[1].split(": ")[1]);
                char sembol = parts[2].split(": ")[1].charAt(0);
                return new Ucgen(yukseklik, sembol);
            } else {
                LogUtil.log("Hatalı veya tanımlanamayan satır atlandı: " + line);
                return null;
            }
        } catch (Exception e) {
            LogUtil.log("Hata oluştu: " + e.getMessage() + " için satır: " + line);
            return null;
        }
    }


}