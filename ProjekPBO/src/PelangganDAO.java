import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PelangganDAO {
    private Connection conn;

    public PelangganDAO(Connection conn) {
        this.conn = conn;
    }

    public void tambahPelanggan(Pelanggan pelanggan) throws SQLException {
        String sql = "INSERT INTO Pelanggan (ID_Pelanggan, Nama, Alamat, No_Telepon) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, pelanggan.getId());
        pstmt.setString(2, pelanggan.getNama());
        pstmt.setString(3, pelanggan.getAlamat());
        pstmt.setString(4, pelanggan.getNoTelepon());
        pstmt.executeUpdate();
    }

    public Pelanggan getPelanggan(int id) throws SQLException {
        String sql = "SELECT * FROM Pelanggan WHERE ID_Pelanggan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Pelanggan(rs.getInt("ID_Pelanggan"), rs.getString("Nama"), rs.getString("Alamat"), rs.getString("No_Telepon"));
        }
        return null;
    }

    public List<Pelanggan> getAllPelanggan() throws SQLException {
        List<Pelanggan> pelangganList = new ArrayList<>();
        String sql = "SELECT * FROM Pelanggan";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Pelanggan pelanggan = new Pelanggan(rs.getInt("ID_Pelanggan"), rs.getString("Nama"), rs.getString("Alamat"), rs.getString("No_Telepon"));
            pelangganList.add(pelanggan);
        }
        return pelangganList;
    }

    public void updatePelanggan(Pelanggan pelanggan) throws SQLException {
        String sql = "UPDATE Pelanggan SET Nama = ?, Alamat = ?, No_Telepon = ? WHERE ID_Pelanggan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, pelanggan.getNama());
        pstmt.setString(2, pelanggan.getAlamat());
        pstmt.setString(3, pelanggan.getNoTelepon());
        pstmt.setInt(4, pelanggan.getId());
        pstmt.executeUpdate();
    }

    public void deletePelanggan(int id) throws SQLException {
        String sql = "DELETE FROM Pelanggan WHERE ID_Pelanggan = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
}
