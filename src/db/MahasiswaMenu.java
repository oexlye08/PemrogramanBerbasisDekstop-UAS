package db;

import java.sql.*;
import java.util.Scanner;

public class MahasiswaMenu {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/uas";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD)) {
            createTableIfNotExists(connection);

            Scanner scanner = new Scanner(System.in);

            int choice;
            do {
                printMenu();
                System.out.print("PILIHAN > ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (choice) {
                    case 1:
                        inputData(connection, scanner);
                        break;
                    case 2:
                        tampilData(connection);
                        break;
                    case 3:
                        updateData(connection, scanner);
                        break;
                    case 0:
                        System.out.println("Keluar dari menu.");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } while (choice != 0);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTableIfNotExists(Connection connection) throws SQLException {
        String createTableQuery = "CREATE TABLE IF NOT EXISTS mahasiswa (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "nama VARCHAR(200)," +
                "alamat VARCHAR(200))";

        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableQuery);
        }
    }

    private static void printMenu() {
        System.out.println("==== MENU UTAMA ====");
        System.out.println("1. Input Data");
        System.out.println("2. Tampil Data");
        System.out.println("3. Update Data");
        System.out.println("0. Keluar");
    }

    private static void inputData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Masukkan nama mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan alamat mahasiswa: ");
        String alamat = scanner.nextLine();

        String insertQuery = "INSERT INTO mahasiswa (nama, alamat) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, nama);
            preparedStatement.setString(2, alamat);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data berhasil dimasukkan.");
            } else {
                System.out.println("Gagal memasukkan data.");
            }
        }
    }

    private static void tampilData(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM mahasiswa";

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(selectQuery)) {

            System.out.println("=== Data Mahasiswa ===");
            System.out.println("ID\tNama\tAlamat");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nama = resultSet.getString("nama");
                String alamat = resultSet.getString("alamat");

                System.out.println(id + "\t" + nama + "\t" + alamat);
            }
        }
    }

    private static void updateData(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Masukkan ID mahasiswa yang akan di-update: ");
        int idToUpdate = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Masukkan nama baru: ");
        String namaBaru = scanner.nextLine();
        System.out.print("Masukkan alamat baru: ");
        String alamatBaru = scanner.nextLine();

        String updateQuery = "UPDATE mahasiswa SET nama = ?, alamat = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, namaBaru);
            preparedStatement.setString(2, alamatBaru);
            preparedStatement.setInt(3, idToUpdate);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Data berhasil di-update.");
            } else {
                System.out.println("Gagal melakukan update. ID tidak ditemukan.");
            }
        }
    }
}
