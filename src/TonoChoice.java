public class TonoChoice {
    public static void main(String[] args) {
        int nilaiTono = 80;

        // Memeriksa kriteria untuk menerima calon pendaftar
        if (nilaiTono > 81) {
            System.out.println("Tono diterima di PT A");
        } else if (nilaiTono >= 71 && nilaiTono <= 80) {
            System.out.println("Tono diterima di PT B");
        } else if (nilaiTono >= 61 && nilaiTono <= 70) {
            System.out.println("Tono diterima di PT C");
        } else {
            System.out.println("Tono diterima di PT D");
        }
    }
}
