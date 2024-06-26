import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PesananDAO {
    private Connection conn;

    public PesananDAO(Connection conn) {
        this.conn = conn;
    }

    public void buatPesanan(Pesanan pesanan) throws SQLException {
        String sql = "INSERT INTO Pesanan (ID_Pesanan, ID_Pelanggan, ID_Menu, Tanggal_Pesanan, Jumlah, Total_Harga) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pesanan.getId());
        pstmt.setInt(2, pesanan.getIdPelanggan());
        pstmt.setInt(3, pesanan.getIdMenu());
        pstmt.setDate(4, pesanan.getTanggalPesanan());
        pstmt.setInt(5, pesanan.getJumlah());
        pstmt.setFloat(6, pesanan.getTotalHarga());
        pstmt.executeUpdate();
    }

    public Pesanan getPesanan(int id) throws SQLException {
        String sql = "SELECT * FROM Pesanan WHERE ID_Pesanan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Pesanan(rs.getInt("ID_Pesanan"), rs.getInt("ID_Pelanggan"), rs.getInt("ID_Menu"), rs.getDate("Tanggal_Pesanan"), rs.getInt("Jumlah"), rs.getFloat("Total_Harga"));
        }
        return null;
    }

    public List<Pesanan> getAllPesanan() throws SQLException {
        List<Pesanan> pesananList = new ArrayList<>();
        String sql = "SELECT * FROM Pesanan";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Pesanan pesanan = new Pesanan(rs.getInt("ID_Pesanan"), rs.getInt("ID_Pelanggan"), rs.getInt("ID_Menu"), rs.getDate("Tanggal_Pesanan"), rs.getInt("Jumlah"), rs.getFloat("Total_Harga"));
            pesananList.add(pesanan);
        }
        return pesananList;
    }

    public void updatePesanan(Pesanan pesanan) throws SQLException {
        String sql = "UPDATE Pesanan SET ID_Pelanggan = ?, ID_Menu = ?, Tanggal_Pesanan = ?, Jumlah = ?, Total_Harga = ? WHERE ID_Pesanan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pesanan.getIdPelanggan());
        pstmt.setInt(2, pesanan.getIdMenu());
        pstmt.setDate(3, pesanan.getTanggalPesanan());
        pstmt.setInt(4, pesanan.getJumlah());
        pstmt.setFloat(5, pesanan.getTotalHarga());
        pstmt.setInt(6, pesanan.getId());
        pstmt.executeUpdate();
    }

    public void deletePesanan(int id) throws SQLException {
        String sql = "DELETE FROM Pesanan WHERE ID_Pesanan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
