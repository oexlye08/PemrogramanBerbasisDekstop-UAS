package polymorph;

public class Main {

    public static void main(String[] args) {
        Matematika matSegitiga = new Segitiga(5, 8);
        Matematika matPersegiPanjang = new PersegiPanjang(4, 6);

        // Menggunakan polymorphism untuk menghitung luas tanpa mengetahui tipe objek secara pasti
        System.out.println("Luas Segitiga: " + matSegitiga.luas());
        System.out.println("Luas Persegi Panjang: " + matPersegiPanjang.luas());
    }
}
