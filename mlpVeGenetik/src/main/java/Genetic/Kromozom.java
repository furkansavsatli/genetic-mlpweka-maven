package Genetic;

/**
 * Created by Furkan on 23.07.2017.
 */
public class Kromozom {

    private String kromozom;
    private double uygunlukDegeri;
    private double kumilatifDegeri;

    public String getKromozom() {
        return kromozom;
    }

    public void setKromozom(String kromozom) {
        this.kromozom = kromozom;
    }

    public double getUygunlukDegeri() {
        return uygunlukDegeri;
    }

    public void setUygunlukDegeri(double uygunlukDegeri) {
        this.uygunlukDegeri = uygunlukDegeri;
    }

    public double getKumilatifDegeri() {
        return kumilatifDegeri;
    }

    public void setKumilatifDegeri(double kumilatifDegeri) {
        this.kumilatifDegeri = kumilatifDegeri;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Learning Rate " + (binaryToInt(this.getKromozom().substring(0, UygulamaParametre.GENOM1_UZUNLUK - 1))) / (double) 100).append("\n");
        stringBuilder.append("Momentum Rate " + (binaryToInt(this.getKromozom().substring(UygulamaParametre.GENOM1_UZUNLUK, UygulamaParametre.GENOM1_UZUNLUK + UygulamaParametre.GENOM2_UZUNLUK - 1))) / (double) 100).append("\n");
        stringBuilder.append("Hidden Layer " + (binaryToInt(this.getKromozom().substring(UygulamaParametre.GENOM1_UZUNLUK + UygulamaParametre.GENOM2_UZUNLUK, UygulamaParametre.GENOM_TOTAL_UZUNLUK - 1)))).append("\n");

        return stringBuilder.toString();
    }

    private int binaryToInt(String substring) {

        return Integer.parseInt(substring, 2);
    }
}
