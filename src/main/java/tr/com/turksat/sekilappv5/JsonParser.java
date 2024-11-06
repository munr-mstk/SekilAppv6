package tr.com.turksat.sekilappv5;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class JsonParser {

    private static final Gson gson = new Gson();

    /**
     * Java nesnesini JSON formatına dönüştürür.
     *
     * @param object Dönüştürülecek Java nesnesi.
     * @return JSON formatında bir String döndürür.
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * JSON formatındaki bir String'i belirtilen sınıf türüne dönüştürür.
     *
     * @param json JSON formatındaki String.
     * @param classOfT Dönüştürülecek sınıfın tipi.
     * @param <T> Dönüştürülecek nesne türü.
     * @return JSON verisini belirtilen sınıf türünde döndüren nesne.
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * JSON formatındaki bir String'i, belirtilen sınıf türündeki nesnelerden oluşan bir listeye dönüştürür.
     *
     * @param json JSON formatındaki String.
     * @param classOfT Listeye dönüştürülecek nesnelerin sınıfı.
     * @param <T> Liste elemanlarının türü.
     * @return JSON verisinden oluşan liste.
     */
    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<List<T>>(){}.getType());
    }

    /**
     * JSON formatındaki bir Reader'ı, JSON dizisine (JsonArray) dönüştürür.
     *
     * @param reader JSON verisini okuyan Reader nesnesi.
     * @return Okunan JSON dizisini döndüren JsonArray nesnesi.
     */
    public static JsonArray parseReader(Reader reader) {
        return gson.fromJson(reader, JsonArray.class);
    }
}
