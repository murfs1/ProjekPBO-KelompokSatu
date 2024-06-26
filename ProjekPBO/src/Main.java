import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/restoran_db";
    private static final String USER = "root";
    private static final String PASS = "";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            RestoranService restoranService = new RestoranService(conn);
            Scanner scanner = new Scanner(System.in);
            int pilihan;

            do {
                System.out.println("Menu Utama:");
                System.out.println("1. Pelanggan");
                System.out.println("2. Menu");
                System.out.println("3. Pesanan");
                System.out.println("4. Keluar");
                System.out.print("Pilihan: ");
                pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        menuPelanggan(restoranService, scanner);
                        break;
                    case 2:
                        menuMenu(restoranService, scanner);
                        break;
                    case 3:
                        menuPesanan(restoranService, scanner);
                        break;
                    case 4:
                        System.out.println("Keluar...");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } while (pilihan != 4);

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void menuPelanggan(RestoranService restoranService, Scanner scanner) throws SQLException {
        int pilihan;
        do {
            System.out.println("Menu Pelanggan:");
            System.out.println("1. Tambah Pelanggan");
            System.out.println("2. Tampilkan Semua Pelanggan");
            System.out.println("3. Update Pelanggan");
            System.out.println("4. Hapus Pelanggan");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahPelanggan(restoranService, scanner);
                    break;
                case 2:
                    tampilkanSemuaPelanggan(restoranService);
                    break;
                case 3:
                    updatePelanggan(restoranService, scanner);
                    break;
                case 4:
                    hapusPelanggan(restoranService, scanner);
                    break;
                case 5:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void menuMenu(RestoranService restoranService, Scanner scanner) throws SQLException {
        int pilihan;
        do {
            System.out.println("Menu Menu:");
            System.out.println("1. Tambah Menu");
            System.out.println("2. Tampilkan Semua Menu");
            System.out.println("3. Update Menu");
            System.out.println("4. Hapus Menu");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahMenu(restoranService, scanner);
                    break;
                case 2:
                    tampilkanSemuaMenu(restoranService);
                    break;
                case 3:
                    updateMenu(restoranService, scanner);
                    break;
                case 4:
                    hapusMenu(restoranService, scanner);
                    break;
                case 5:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void menuPesanan(RestoranService restoranService, Scanner scanner) throws SQLException {
        int pilihan;
        do {
            System.out.println("Menu Pesanan:");
            System.out.println("1. Buat Pesanan");
            System.out.println("2. Tampilkan Semua Pesanan");
            System.out.println("3. Update Pesanan");
            System.out.println("4. Hapus Pesanan");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilihan: ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    buatPesanan(restoranService, scanner);
                    break;
                case 2:
                    tampilkanSemuaPesanan(restoranService);
                    break;
                case 3:
                    updatePesanan(restoranService, scanner);
                    break;
                case 4:
                    hapusPesanan(restoranService, scanner);
                    break;
                case 5:
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 5);
    }

    private static void tambahPelanggan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pelanggan: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nama: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat: ");
        String alamat = scanner.nextLine();
        System.out.print("No Telepon: ");
        String noTelepon = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(id, nama, alamat, noTelepon);
        restoranService.tambahPelanggan(pelanggan);
        System.out.println("Pelanggan berhasil ditambahkan!");
    }

    private static void tambahMenu(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Menu: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nama Menu: ");
        String nama = scanner.nextLine();
        System.out.print("Harga: ");
        float harga = scanner.nextFloat();

        Menu menu = new Menu(id, nama, harga);
        restoranService.tambahMenu(menu);
        System.out.println("Menu berhasil ditambahkan!");
    }

    private static void buatPesanan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pesanan: ");
        int id = scanner.nextInt();
        System.out.print("ID Pelanggan: ");
        int idPelanggan = scanner.nextInt();
        System.out.print("ID Menu: ");
        int idMenu = scanner.nextInt();
        System.out.print("Tanggal Pesanan (YYYY-MM-DD): ");
        String tanggalPesanan = scanner.next();
        System.out.print("Jumlah: ");
        int jumlah = scanner.nextInt();

        float harga = restoranService.getHargaMenu(idMenu);
        float totalHarga = jumlah * harga;
        Pesanan pesanan = new Pesanan(id, idPelanggan, idMenu, Date.valueOf(tanggalPesanan), jumlah, totalHarga);

        restoranService.buatPesanan(pesanan);
        System.out.println("Pesanan berhasil dibuat!");
    }

    private static void tampilkanSemuaPelanggan(RestoranService restoranService) throws SQLException {
        List<Pelanggan> pelangganList = restoranService.getAllPelanggan();
        for (Pelanggan pelanggan : pelangganList) {
            System.out.println("ID: " + pelanggan.getId() + ", Nama: " + pelanggan.getNama() + ", Alamat: " + pelanggan.getAlamat() + ", No Telepon: " + pelanggan.getNoTelepon());
        }
    }

    private static void tampilkanSemuaMenu(RestoranService restoranService) throws SQLException {
        List<Menu> menuList = restoranService.getAllMenu();
        for (Menu menu : menuList) {
            System.out.println("ID: " + menu.getId() + ", Nama Menu: " + menu.getNama() + ", Harga: " + menu.getHarga());
        }
    }

    private static void tampilkanSemuaPesanan(RestoranService restoranService) throws SQLException {
        List<Pesanan> pesananList = restoranService.getAllPesanan();
        for (Pesanan pesanan : pesananList) {
            System.out.println("ID: " + pesanan.getId() + ", ID Pelanggan: " + pesanan.getIdPelanggan() + ", ID Menu: " + pesanan.getIdMenu() + ", Tanggal Pesanan: " + pesanan.getTanggalPesanan() + ", Jumlah: " + pesanan.getJumlah() + ", Total Harga: " + pesanan.getTotalHarga());
        }
    }

    private static void updatePelanggan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pelanggan yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nama baru: ");
        String nama = scanner.nextLine();
        System.out.print("Alamat baru: ");
        String alamat = scanner.nextLine();
        System.out.print("No Telepon baru: ");
        String noTelepon = scanner.nextLine();

        Pelanggan pelanggan = new Pelanggan(id, nama, alamat, noTelepon);
        restoranService.updatePelanggan(pelanggan);
        System.out.println("Pelanggan berhasil diupdate!");
    }

    private static void updateMenu(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Menu yang akan diupdate: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Nama Menu baru: ");
        String nama = scanner.nextLine();
        System.out.print("Harga baru: ");
        float harga = scanner.nextFloat();

        Menu menu = new Menu(id, nama, harga);
        restoranService.updateMenu(menu);
        System.out.println("Menu berhasil diupdate!");
    }

    private static void updatePesanan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pesanan yang akan diupdate: ");
        int id = scanner.nextInt();
        System.out.print("ID Pelanggan baru: ");
        int idPelanggan = scanner.nextInt();
        System.out.print("ID Menu baru: ");
        int idMenu = scanner.nextInt();
        System.out.print("Tanggal Pesanan baru (YYYY-MM-DD): ");
        String tanggalPesanan = scanner.next();
        System.out.print("Jumlah baru: ");
        int jumlah = scanner.nextInt();

        float harga = restoranService.getHargaMenu(idMenu);
        float totalHarga = jumlah * harga;
        Pesanan pesanan = new Pesanan(id, idPelanggan, idMenu, Date.valueOf(tanggalPesanan), jumlah, totalHarga);

        restoranService.updatePesanan(pesanan);
        System.out.println("Pesanan berhasil diupdate!");
    }

    private static void hapusPelanggan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pelanggan yang akan dihapus: ");
        int id = scanner.nextInt();
        restoranService.deletePelanggan(id);
        System.out.println("Pelanggan berhasil dihapus!");
    }

    private static void hapusMenu(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Menu yang akan dihapus: ");
        int id = scanner.nextInt();
        restoranService.deleteMenu(id);
        System.out.println("Menu berhasil dihapus!");
    }

    private static void hapusPesanan(RestoranService restoranService, Scanner scanner) throws SQLException {
        System.out.print("ID Pesanan yang akan dihapus: ");
        int id = scanner.nextInt();
        restoranService.deletePesanan(id);
        System.out.println("Pesanan berhasil dihapus!");
    }
}
