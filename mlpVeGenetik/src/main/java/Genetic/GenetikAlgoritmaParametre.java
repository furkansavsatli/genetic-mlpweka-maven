package Genetic;

/**
 * Created by Furkan on 23.07.2017.
 */
public class GenetikAlgoritmaParametre {

    public static double  mutasyonIhtimali=0.1;
    public static int  iterasyonSayisi=100;
    //başarılı oranın olasılığının artırılması için kullanılır
    public static double UygunlukFonsiyonu(double x){
        return 4*x;
    }


}
