package Genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Furkan on 23.07.2017.
 */
public class GenetikAlgoritma {


    public void algoritma() {

        try {
            List<Kromozom> kromozomHavuzu = new ArrayList<Kromozom>();
            List<Kromozom> kromozomHavuzuYeniNesil = new ArrayList<Kromozom>();
            List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis = new ArrayList<Kromozom>();

            List<String> randomKromozomListesi = GenetikUtils.KromozomOlusturucu(UygulamaParametre.RANDOM_KROMOZOM_SAYISI, UygulamaParametre.GENOM_TOTAL_UZUNLUK);


            System.out.println("Random kromozom hazırlanması ve uygunluk değerlerinin atanması********");
            randomKromozomHazirla(randomKromozomListesi,kromozomHavuzu);

            for (int z = 0; z < GenetikAlgoritmaParametre.iterasyonSayisi; z++) {

                System.out.println();
                System.out.println(z+". Kumilatif değer ataması********");
                kumilatifDegerAtamasi(kromozomHavuzu);

                System.out.println();
                System.out.println(z+". Rus ruleti kromozom seçimi başlar*******");
                rusRuletiIleKromozomSecimi(kromozomHavuzuYeniNesil,kromozomHavuzu);
                System.out.println(z+". Rus ruleti kromozom seçimi biter*******");

                System.out.println();
                System.out.println(z+". Çaprazlama başlar*******");
                kromozomCaprazla(kromozomHavuzuYeniNesil,kromozomHavuzuYeniNesilCaprazlanmis);
                System.out.println(z+". Çaprazlama biter*******");

                System.out.println();
                System.out.println(z+". Mutasyon başlar*******");
                mutasyon(kromozomHavuzuYeniNesilCaprazlanmis);
                System.out.println(z+". Mutasyon biter*******");

                System.out.println();
                System.out.println(z+". Doğal seçilim başlar*******");
                dogalSecilim(kromozomHavuzuYeniNesilCaprazlanmis,kromozomHavuzu);
                System.out.println(z+". Doğal seçilim biter*******");


                kromozomHavuzu = kromozomHavuzuYeniNesilCaprazlanmis;
                kromozomHavuzuYeniNesil = new ArrayList<Kromozom>();
                kromozomHavuzuYeniNesilCaprazlanmis = new ArrayList<Kromozom>();
                System.out.println();
                System.out.println("Yeni iterasyon geçilir...");
                System.out.println();
            }
            System.out.println("Sonuç...");
            for (Kromozom kromozom : kromozomHavuzu) {

                System.out.println("Kromozom " + kromozom.getKromozom() + " Uygunluk değeri " + kromozom.getUygunlukDegeri());
            }
            Kromozom enElitKromozom = elitKromozomuSec(kromozomHavuzu);
            System.out.println();
            System.out.println("En iyi sonuç -- Kromozom " + enElitKromozom.getKromozom() + " Uygunluk değeri " + enElitKromozom.getUygunlukDegeri());

           System.out.print(enElitKromozom);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void dogalSecilim(List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis, List<Kromozom> kromozomHavuzu) {
        System.out.println("Zayıf Olan Elenir**** ELİTİZM");

        Kromozom elitKromozom = elitKromozomuSec(kromozomHavuzu);
        kromozomHavuzuYeniNesilCaprazlanmis.add(elitKromozom);
        System.out.println("Elit Kromozom " + elitKromozom.getKromozom());
    }

    private void mutasyon(List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis) {

        System.out.println("Mutasyon seçilen kromozomda uygulanır*******");
        int kromozomSirasi = mutasyonUygulanacakKromozomSec(kromozomHavuzuYeniNesilCaprazlanmis);

        System.out.println("Mutasyona Uğrayacak Kromozom " + kromozomHavuzuYeniNesilCaprazlanmis.get(kromozomSirasi).getKromozom());

        Kromozom kromozomMutasyonUgradi = mutasyonUygula(kromozomHavuzuYeniNesilCaprazlanmis, kromozomSirasi);
        System.out.println("Mutasyona Uğramış Kromozom   " + kromozomMutasyonUgradi.getKromozom());
    }

    private void rusRuletiIleKromozomSecimi(List<Kromozom> kromozomHavuzuYeniNesil, List<Kromozom> kromozomHavuzu) {
        for (int i = 0; i < UygulamaParametre.CAPRAZLANACAK_KROMOZOM_SAYISI; i++) {
            Kromozom caprazlanacak = rusRuletiSecimi(kromozomHavuzu);
            System.out.println("Çaprazlama için seçilen " + i + ". kromozom " + caprazlanacak.getKromozom());
            kromozomHavuzuYeniNesil.add(caprazlanacak);
        }
    }

    private void kromozomCaprazla(List<Kromozom> kromozomHavuzuYeniNesil, List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis) {

        for (int i = 0; i < UygulamaParametre.CAPRAZLANACAK_KROMOZOM_SAYISI; i = i + 2) {
            Kromozom caprazlanmisKromozom = caprazla(kromozomHavuzuYeniNesil.get(i), kromozomHavuzuYeniNesil.get(i + 1));
            caprazlanmisKromozom.setUygunlukDegeri(uygunlukDegerHesaplama(caprazlanmisKromozom.getKromozom()));
            kromozomHavuzuYeniNesilCaprazlanmis.add(caprazlanmisKromozom);
            System.out.println("Çaprazlanan kromozomun ilk kısmı " + kromozomHavuzuYeniNesil.get(i).getKromozom() + " + " + kromozomHavuzuYeniNesil.get(i + 1).getKromozom() + "-->> " + caprazlanmisKromozom.getKromozom() + " Uygunluk değeri " + caprazlanmisKromozom.getUygunlukDegeri());

            System.out.print(caprazlanmisKromozom);

            caprazlanmisKromozom = caprazla(kromozomHavuzuYeniNesil.get(i + 1), kromozomHavuzuYeniNesil.get(i));
            caprazlanmisKromozom.setUygunlukDegeri(uygunlukDegerHesaplama(caprazlanmisKromozom.getKromozom()));
            kromozomHavuzuYeniNesilCaprazlanmis.add(caprazlanmisKromozom);
            System.out.println("Çaprazlanan kromozomun ikinci kısmı " + kromozomHavuzuYeniNesil.get(i + 1).getKromozom() + " + " + kromozomHavuzuYeniNesil.get(i).getKromozom() + "-->> " + caprazlanmisKromozom.getKromozom() + " Uygunluk değeri " + caprazlanmisKromozom.getUygunlukDegeri());

            System.out.print(caprazlanmisKromozom);
        }
    }

    private void randomKromozomHazirla(List<String> randomKromozomListesi, List<Kromozom> kromozomHavuzu) {

        Kromozom kromozom = new Kromozom();

        String gen1 = randomKromozomListesi.get(0);
        kromozom.setKromozom(gen1);
        kromozom.setUygunlukDegeri(uygunlukDegerHesaplama(gen1));
        kromozomHavuzu.add(kromozom);

        String gen2 = randomKromozomListesi.get(1);
        kromozom = new Kromozom();
        kromozom.setKromozom(gen2);
        kromozom.setUygunlukDegeri(uygunlukDegerHesaplama(gen2));
        kromozomHavuzu.add(kromozom);

        String gen3 = randomKromozomListesi.get(2);
        kromozom = new Kromozom();
        kromozom.setKromozom(gen3);
        kromozom.setUygunlukDegeri(uygunlukDegerHesaplama(gen3));
        kromozomHavuzu.add(kromozom);

        String gen4 = randomKromozomListesi.get(3);
        kromozom = new Kromozom();
        kromozom.setKromozom(gen4);
        kromozom.setUygunlukDegeri(uygunlukDegerHesaplama(gen4));
        kromozomHavuzu.add(kromozom);

        String gen5 = randomKromozomListesi.get(4);
        kromozom = new Kromozom();
        kromozom.setKromozom(gen5);
        kromozom.setUygunlukDegeri(uygunlukDegerHesaplama(gen5));
        kromozomHavuzu.add(kromozom);

        System.out.println("random gen1 " + gen1.toString());
        System.out.println("random gen2 " + gen2.toString());
        System.out.println("random gen3 " + gen3.toString());
        System.out.println("random gen4 " + gen4.toString());
        System.out.println("random gen5 " + gen5.toString());


    }

    private int binaryToInt(String substring) {

        return Integer.parseInt(substring, 2);
    }

    private Kromozom mutasyonUygula(List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis, int kromozomSirasi) {
        Random generator = new Random();
        int kromozomuBozulcakGen = generator.nextInt(UygulamaParametre.GENOM_TOTAL_UZUNLUK);
        System.out.println("Mutasyona Uğrayacak Kromozom Sırası " + kromozomuBozulcakGen);
        String degisecekKromozom = kromozomHavuzuYeniNesilCaprazlanmis.get(kromozomSirasi).getKromozom();
        String degisecekGen = degisecekKromozom.substring(kromozomuBozulcakGen, kromozomuBozulcakGen + 1);

        if ("1".equals(degisecekGen)) {
            degisecekGen = "0";
        } else {
            degisecekGen = "1";
        }
        degisecekKromozom = degisecekKromozom.substring(0, kromozomuBozulcakGen) + degisecekGen + degisecekKromozom.substring(kromozomuBozulcakGen + 1, degisecekKromozom.length());

        kromozomHavuzuYeniNesilCaprazlanmis.get(kromozomSirasi).setKromozom(degisecekKromozom);
        return kromozomHavuzuYeniNesilCaprazlanmis.get(kromozomSirasi);
    }

    private int mutasyonUygulanacakKromozomSec(List<Kromozom> kromozomHavuzuYeniNesilCaprazlanmis) {
        Random generator = new Random();
        int kromozomSirasi = generator.nextInt(kromozomHavuzuYeniNesilCaprazlanmis.size());

        return kromozomSirasi;
    }

    private Kromozom elitKromozomuSec(List<Kromozom> kromozomHavuzu) {

        Kromozom elit = new Kromozom();

        for (Kromozom kromozom : kromozomHavuzu) {
            if (elit.getUygunlukDegeri() < kromozom.getUygunlukDegeri()) {
                elit = kromozom;
            }

        }
        return elit;
    }

    private Kromozom caprazla(Kromozom kromozom, Kromozom kromozom1) {
        Kromozom caprazlananKromozom = new Kromozom();
        caprazlananKromozom.setKromozom(kromozom.getKromozom().substring(0, UygulamaParametre.CAPRAZLANACAK_KROMOZOM_SIRASI) + kromozom1.getKromozom().substring(UygulamaParametre.CAPRAZLANACAK_KROMOZOM_SIRASI, UygulamaParametre.GENOM_TOTAL_UZUNLUK));
        return caprazlananKromozom;
    }

    //Başarı oranı uygunluk değeri
    public double uygunlukDegerHesaplama(String genom) {
        Double learningRate = (binaryToInt(genom.substring(0, UygulamaParametre.GENOM1_UZUNLUK))) / (double) 100;
        Double momentumRate = (binaryToInt(genom.substring(UygulamaParametre.GENOM1_UZUNLUK, UygulamaParametre.GENOM1_UZUNLUK + UygulamaParametre.GENOM2_UZUNLUK))) / (double) 100;
        ;
        String hiddenLayer = String.valueOf(binaryToInt(genom.substring(UygulamaParametre.GENOM1_UZUNLUK + UygulamaParametre.GENOM2_UZUNLUK, UygulamaParametre.GENOM1_UZUNLUK + UygulamaParametre.GENOM2_UZUNLUK + UygulamaParametre.GENOM3_UZUNLUK)));


        try {

            return GenetikAlgoritmaParametre.UygunlukFonsiyonu(MlpWeka.mlp(learningRate, momentumRate, hiddenLayer));

        } catch (Exception ex)

        {
            ex.printStackTrace();
        }


        return 0;
    }

    public Kromozom rusRuletiSecimi(List<Kromozom> kromozomList) {


        Random generator = new Random();
        int ruletDegeri = generator.nextInt((int) (kromozomList.get(kromozomList.size() - 1).getKumilatifDegeri()));

        for (Kromozom kromozom : kromozomList) {
            if (ruletDegeri <= kromozom.getKumilatifDegeri() && ruletDegeri > kromozom.getKumilatifDegeri() - kromozom.getUygunlukDegeri()) {
                return kromozom;
            }
        }

        return null;
    }

    public List<Kromozom> kumilatifDegerAtamasi(List<Kromozom> kromozomList) {

        double total = 0;
        for (Kromozom kromozom : kromozomList) {
            total = total + kromozom.getUygunlukDegeri();
            kromozom.setKumilatifDegeri(total);

        }

        return kromozomList;
    }


    public static void main(String args[]) {

        GenetikAlgoritma genetikAlgoritma = new GenetikAlgoritma();
        genetikAlgoritma.algoritma();

    }
}
