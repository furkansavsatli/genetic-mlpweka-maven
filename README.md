# genetic-mlpweka-maven
Genetik algoritma ile weka kütüphanesini kullanırken en optimum multilayer perceptron parametrelerinin seçilmesi ve bu sonuçlarla multilayer perceptronun sonuçlandırılması uygulamasıdır.

Veri olarak https://www.kaggle.com/c/titanic datası kullanılmıştır.

Öğrenme algoritmaları için https://weka.wikispaces.com/Use+WEKA+in+your+Java+code weka kütüphanesi kullanılmıştır.

Genetik algoritma adımları

1 İstenilen sayıda random kromozomlar üretilmesi

2 Üretilen kromozomların MLP algoritmasında çalıştırılması ve başarı oranlarının hesaplanması(MLP yerine başka bir algoritma da kullanılabilir.)

3 Başarı oranlarının uygunluk fonksiyonundan geçirilmesi

4 Uygunluk değerlerine göre kumilatif oranlara sahip olan kromozomların rus ruleti yöntemiyle seçilmesi.

5 Seçilen kromozomların çaprazlanması

6 Mutasyon

7 Doğal Seleksiyon(Elitizm)

8 Döngü bitene kadar 2'den devam



Uygulama için gerekenler

1 Java sdk

2 IntelliJ IDEA veya Eclipse gibi bir ide

3 Maven(IDEAda default mevcut)

Çalıştırma

1 Maven clean install 
2 Run
