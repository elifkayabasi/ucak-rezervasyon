# Ucak Bilet Rezervasyon Sistemi

Bu proje Java ile yapildi. Konsoldan ucak, lokasyon ve rezervasyon ekleniyor. Listeleme vs de var.

Derste anlatilan class mantigiyla yapmaya calistim. Tum veriler ArrayList'te tutuluyor. 

## Yapilanlar
- Ucak sinifi (model, marka, seri, kapasite)
- Lokasyon sinifi (ulke, sehir vs)
- Ucus sinifi (saat, kalkis-varis)
- Rezervasyon sinifi (ad, soyad, yas)
- Main sinifinda menü yapisi

## Kullanim
Java yüklü olmasi lazim.

## Calistirma
Kod tek bir dosyada (`UcakRezervasyon.java`) yazildigindan calistirmak icin:
javac UcakRezervasyon.java
java UcakRezervasyon

## Ciktilar

Rezervasyon islemleri sonrasinda `"rezervasyonlar.csv"` dosyasına kayıt yapiliyor. CSV icinde ad, soyad, yas, kalkis-varis sehirleri, saat ve ucak modeli bilgileri bulunuyor.
