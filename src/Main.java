import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            printMenu();
            System.out.print("PILIHAN > ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            switch (choice) {
                case 1:
                    System.out.println("Anda memilih menu 1.");
                    break;
                case 2:
                    System.out.println("Anda memilih menu 2.");
                    break;
                case 3:
                    System.out.println("Anda memilih menu 3.");
                    break;
                case 0:
                    System.out.println("Keluar dari menu.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (choice != 0);
    }

    private static void printMenu() {
        System.out.println("==== MENU UTAMA ====");
        System.out.println("1. Input Data");
        System.out.println("2. Tampil Data");
        System.out.println("3. Update Data");
        System.out.println("0. Keluar");
    }
}