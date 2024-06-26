import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RestoranService {
    private Connection conn;

    public RestoranService(Connection conn) {
        this.conn = conn;
    }

    // Pelanggan CRUD methods
    public void tambahPelanggan(Pelanggan pelanggan) throws SQLException {
        String sql = "INSERT INTO Pelanggan (ID_Pelanggan, Nama, Alamat, No_Telepon) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pelanggan.getId());
            pstmt.setString(2, pelanggan.getNama());
            pstmt.setString(3, pelanggan.getAlamat());
            pstmt.setString(4, pelanggan.getNoTelepon());
            pstmt.executeUpdate();
        }
    }

    public List<Pelanggan> getAllPelanggan() throws SQLException {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String sql = "SELECT * FROM Pelanggan";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("ID_Pelanggan");
                String nama = rs.getString("Nama");
                String alamat = rs.getString("Alamat");
                String noTelepon = rs.getString("No_Telepon");
                Pelanggan pelanggan = new Pelanggan(id, nama, alamat, noTelepon);
                pelangganList.add(pelanggan);
            }
        }
        return pelangganList;
    }

    public void updatePelanggan(Pelanggan pelanggan) throws SQLException {
        String sql = "UPDATE Pelanggan SET Nama = ?, Alamat = ?, No_Telepon = ? WHERE ID_Pelanggan = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pelanggan.getNama());
            pstmt.setString(2, pelanggan.getAlamat());
            pstmt.setString(3, pelanggan.getNoTelepon());
            pstmt.setInt(4, pelanggan.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletePelanggan(int id) throws SQLException {
        String sql = "DELETE FROM Pelanggan WHERE ID_Pelanggan = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Menu CRUD methods
    public void tambahMenu(Menu menu) throws SQLException {
        String sql = "INSERT INTO Menu (ID_Menu, Nama_Menu, Harga) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, menu.getId());
            pstmt.setString(2, menu.getNama());
            pstmt.setFloat(3, menu.getHarga());
            pstmt.executeUpdate();
        }
    }

    public List<Menu> getAllMenu() throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("ID_Menu");
                String nama = rs.getString("Nama_Menu");
                float harga = rs.getFloat("Harga");
                Menu menu = new Menu(id, nama, harga);
                menuList.add(menu);
            }
        }
        return menuList;
    }

    public void updateMenu(Menu menu) throws SQLException {
        String sql = "UPDATE Menu SET Nama_Menu = ?, Harga = ? WHERE ID_Menu = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, menu.getNama());
            pstmt.setFloat(2, menu.getHarga());
            pstmt.setInt(3, menu.getId());
            pstmt.executeUpdate();
        }
    }

    public void deleteMenu(int id) throws SQLException {
        String sql = "DELETE FROM Menu WHERE ID_Menu = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    // Pesanan CRUD methods
    public void buatPesanan(Pesanan pesanan) throws SQLException {
        String sql = "INSERT INTO Pesanan (ID_Pesanan, ID_Pelanggan, ID_Menu, Tanggal_Pesanan, Jumlah, Total_Harga) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pesanan.getId());
            pstmt.setInt(2, pesanan.getIdPelanggan());
            pstmt.setInt(3, pesanan.getIdMenu());
            pstmt.setDate(4, pesanan.getTanggalPesanan());
            pstmt.setInt(5, pesanan.getJumlah());
            pstmt.setFloat(6, pesanan.getTotalHarga());
            pstmt.executeUpdate();
        }
    }

    public List<Pesanan> getAllPesanan() throws SQLException {
        List<Pesanan> pesananList = new ArrayList<>();
        String sql = "SELECT * FROM Pesanan";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("ID_Pesanan");
                int idPelanggan = rs.getInt("ID_Pelanggan");
                int idMenu = rs.getInt("ID_Menu");
                Date tanggalPesanan = rs.getDate("Tanggal_Pesanan");
                int jumlah = rs.getInt("Jumlah");
                float totalHarga = rs.getFloat("Total_Harga");
                Pesanan pesanan = new Pesanan(id, idPelanggan, idMenu, tanggalPesanan, jumlah, totalHarga);
                pesananList.add(pesanan);
            }
        }
        return pesananList;
    }

    public void updatePesanan(Pesanan pesanan) throws SQLException {
        String sql = "UPDATE Pesanan SET ID_Pelanggan = ?, ID_Menu = ?, Tanggal_Pesanan = ?, Jumlah = ?, Total_Harga = ? WHERE ID_Pesanan = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pesanan.getIdPelanggan());
            pstmt.setInt(2, pesanan.getIdMenu());
            pstmt.setDate(3, pesanan.getTanggalPesanan());
            pstmt.setInt(4, pesanan.getJumlah());
            pstmt.setFloat(5, pesanan.getTotalHarga());
            pstmt.setInt(6, pesanan.getId());
            pstmt.executeUpdate();
        }
    }

    public void deletePesanan(int id) throws SQLException {
        String sql = "DELETE FROM Pesanan WHERE ID_Pesanan = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public float getHargaMenu(int idMenu) throws SQLException {
        String sql = "SELECT Harga FROM Menu WHERE ID_Menu = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idMenu);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("Harga");
                } else {
                    throw new SQLException("ID Menu tidak ditemukan.");
                }
            }
        }
    }
}
