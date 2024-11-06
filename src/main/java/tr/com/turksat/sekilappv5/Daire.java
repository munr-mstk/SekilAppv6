package tr.com.turksat.sekilappv5;



public class Daire implements Sekil {
    private String type = "Daire"; // Şeklin tipi (Daire)
    private int cap; // Dairenin çapı
    private char sembol; // Dairenin sembolü
    private static final char DEFAULT_SYMBOL = '*'; // Varsayılan sembol

    /**
     * Daire nesnesini başlatır.
     *
     * @param cap Dairenin çapını temsil eder.
     * @param sembol Dairenin sembolünü belirler. Sıfır (0) belirtilirse varsayılan sembol kullanılır.
     */
    public Daire(int cap, char sembol) {
        this.cap = cap;
        this.sembol = sembol == 0 ? DEFAULT_SYMBOL : sembol;
    }

    /**
     * Dairenin özelliklerini içeren bir String döndürür.
     *
     * @return Dairenin tipi, çapı ve sembolü hakkında bilgi içeren bir String.
     */
    @Override
    public String toString() {
        return type + ", Çap: " + cap + ", Sembol: " + sembol;
    }

    /**
     * Daireyi ekranda çizen bir metottur.
     *
     * Dairenin çapı ve sembolü kullanılarak daire şekli ekranda çizilir.
     * Ayrıca dairenin alanı ve çevresi hesaplanarak loglanır.
     */
    @Override
    public void ciz() {
        double r = cap / 2.0;
        for (int i = 0; i <= cap; i++) {
            for (int j = 0; j <= cap; j++) {

                double distance = Math.sqrt(Math.pow(i - r, 2) + Math.pow(j - r, 2));
                if (distance > r - 0.5 && distance < r + 0.5) {
                    System.out.print(sembol + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }

        LogUtil.log("Daire alanı: " + alanHesapla());
        LogUtil.log("Daire çevresi: " + cevreHesapla());
    }

    /**
     * Dairenin sembolünü değiştirir.
     *
     * @param yeniSembol Dairenin yeni sembolü.
     */
    @Override
    public void sembolDegistir(char yeniSembol) {
        this.sembol = yeniSembol;
    }

    /**
     * Dairenin alanını hesaplar.
     *
     * @return Dairenin alanını döndürür.
     */
    @Override
    public double alanHesapla() {
        double r = cap / 2.0;
        return Math.PI * r * r;
    }

    /**
     * Dairenin çevresini hesaplar.
     *
     * @return Dairenin çevresini döndürür.
     */
    @Override
    public double cevreHesapla() {
        return Math.PI * cap;
    }

    /**
     * Dairenin özelliklerini JSON formatında döndürür.
     *
     * @return Dairenin JSON formatında temsilini döndürür.
     */
    @Override
    public String toJson() {
        return JsonParser.toJson(this);
    }
}