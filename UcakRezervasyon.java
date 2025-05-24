
import java.util.*;
import java.io.*;

class Ucak {
    String model, marka, seriNo;
    int kapasite, dolu = 0;

    Ucak(String model, String marka, String seriNo, int kapasite) {
        this.model = model;
        this.marka = marka;
        this.seriNo = seriNo;
        this.kapasite = kapasite;
    }

    boolean koltukAyir() {
        if (dolu < kapasite) {
            dolu++;
            return true;
        }
        return false;
    }

    public String toString() {
        return model + " | " + marka + " | Seri: " + seriNo + " | Kapasite: " + kapasite + " | Dolu: " + dolu;
    }
}

class Lokasyon {
    String ulke, sehir, havalimani;
    boolean aktif;

    Lokasyon(String ulke, String sehir, String havalimani, boolean aktif) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havalimani = havalimani;
        this.aktif = aktif;
    }

    public String toString() {
        return sehir + " - " + ulke + " (" + havalimani + ")";
    }
}

class Ucus {
    Lokasyon kalkis, varis;
    String saat;
    Ucak ucak;

    Ucus(Lokasyon kalkis, Lokasyon varis, String saat, Ucak ucak) {
        this.kalkis = kalkis;
        this.varis = varis;
        this.saat = saat;
        this.ucak = ucak;
    }

    public String toString() {
        return kalkis.sehir + " -> " + varis.sehir + " (" + saat + ") [" + ucak.model + "]";
    }
}

class Rezervasyon {
    String ad, soyad;
    int yas;
    Ucus ucus;

    Rezervasyon(String ad, String soyad, int yas, Ucus ucus) {
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.ucus = ucus;
    }

    public String toString() {
        return ad + " " + soyad + " (" + yas + ") - " + ucus;
    }
}

public class UcakRezervasyon {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Ucak> ucaklar = new ArrayList<>();
        ArrayList<Lokasyon> lokasyonlar = new ArrayList<>();
        ArrayList<Ucus> ucuslar = new ArrayList<>();
        ArrayList<Rezervasyon> rezervasyonlar = new ArrayList<>();

        while (true) {
            System.out.println("\n1) Ucak Ekle\n2) Lokasyon Ekle\n3) Ucus Ekle\n4) Rezervasyon Yap\n5) Listele\n6) Kaydet ve Cikis");
            int secim = sc.nextInt();
            sc.nextLine();

            if (secim == 1) {
                System.out.print("Model: "); String m = sc.nextLine();
                System.out.print("Marka: "); String ma = sc.nextLine();
                System.out.print("Seri No: "); String sn = sc.nextLine();
                System.out.print("Kapasite: "); int kp = sc.nextInt(); sc.nextLine();
                ucaklar.add(new Ucak(m, ma, sn, kp));
            } else if (secim == 2) {
                System.out.print("Ulke: "); String u = sc.nextLine();
                System.out.print("Sehir: "); String s = sc.nextLine();
                System.out.print("Havalimani: "); String h = sc.nextLine();
                System.out.print("Aktif mi? (true/false): "); boolean a = sc.nextBoolean(); sc.nextLine();
                lokasyonlar.add(new Lokasyon(u, s, h, a));
            } else if (secim == 3) {
                System.out.println("Kalkis (index):");
                for (int i = 0; i < lokasyonlar.size(); i++) System.out.println(i + ": " + lokasyonlar.get(i));
                int k = sc.nextInt();

                System.out.println("Varis (index):");
                for (int i = 0; i < lokasyonlar.size(); i++) if (i != k) System.out.println(i + ": " + lokasyonlar.get(i));
                int v = sc.nextInt();

                System.out.println("Ucak (index):");
                for (int i = 0; i < ucaklar.size(); i++) System.out.println(i + ": " + ucaklar.get(i));
                int u = sc.nextInt(); sc.nextLine();

                System.out.print("Saat: "); String st = sc.nextLine();
                ucuslar.add(new Ucus(lokasyonlar.get(k), lokasyonlar.get(v), st, ucaklar.get(u)));
            } else if (secim == 4) {
                System.out.println("Ucus sec:");
                for (int i = 0; i < ucuslar.size(); i++) System.out.println(i + ": " + ucuslar.get(i));
                int uci = sc.nextInt(); sc.nextLine();

                if (ucuslar.get(uci).ucak.koltukAyir()) {
                    System.out.print("Ad: "); String ad = sc.nextLine();
                    System.out.print("Soyad: "); String soyad = sc.nextLine();
                    System.out.print("Yas: "); int yas = sc.nextInt(); sc.nextLine();
                    rezervasyonlar.add(new Rezervasyon(ad, soyad, yas, ucuslar.get(uci)));
                    System.out.println("Rezervasyon eklendi.");
                } else {
                    System.out.println("Uçakta yer kalmamış.");
                }
            } else if (secim == 5) {
                System.out.println("Ucaklar:");
                for (Ucak u : ucaklar) System.out.println(u);
                System.out.println("Lokasyonlar:");
                for (Lokasyon l : lokasyonlar) System.out.println(l);
                System.out.println("Ucuslar:");
                for (Ucus u : ucuslar) System.out.println(u);
                System.out.println("Rezervasyonlar:");
                for (Rezervasyon r : rezervasyonlar) System.out.println(r.ad + "," + r.soyad + "," + r.yas + "," + r.ucus.kalkis.sehir + "," + r.ucus.varis.sehir + "," + r.ucus.saat + "," + r.ucus.ucak.model);
            } else if (secim == 6) {
                try (PrintWriter out = new PrintWriter("rezervasyonlar.csv")) {
                    out.println("Ad,Soyad,Yas,Kalkis,Varis,Saat,UcakModel");
                    for (Rezervasyon r : rezervasyonlar) out.println(r.ad + "," + r.soyad + "," + r.yas + "," + r.ucus.kalkis.sehir + "," + r.ucus.varis.sehir + "," + r.ucus.saat + "," + r.ucus.ucak.model);
                    System.out.println("Dosyaya kaydedildi. Cikis yapiliyor...");
                } catch (Exception e) {
                    System.out.println("Kayit hatasi.");
                }
                break;
            }
        }

        sc.close();
    }
}
