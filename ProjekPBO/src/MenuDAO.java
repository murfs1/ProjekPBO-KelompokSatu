import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {
    private Connection conn;

    public MenuDAO(Connection conn) {
        this.conn = conn;
    }

    public void tambahMenu(Menu menu) throws SQLException {
        String sql = "INSERT INTO Menu (ID_Menu, Nama_Menu, Harga) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, menu.getId());
        pstmt.setString(2, menu.getNama());
        pstmt.setFloat(3, menu.getHarga());
        pstmt.executeUpdate();
    }

    public Menu getMenu(int id) throws SQLException {
        String sql = "SELECT * FROM Menu WHERE ID_Menu = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return new Menu(rs.getInt("ID_Menu"), rs.getString("Nama_Menu"), rs.getFloat("Harga"));
        }
        return null;
    }

    public List<Menu> getAllMenu() throws SQLException {
        List<Menu> menuList = new ArrayList<>();
        String sql = "SELECT * FROM Menu";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Menu menu = new Menu(rs.getInt("ID_Menu"), rs.getString("Nama_Menu"), rs.getFloat("Harga"));
            menuList.add(menu);
        }
        return menuList;
    }

    public void updateMenu(Menu menu) throws SQLException {
        String sql = "UPDATE Menu SET Nama_Menu = ?, Harga = ? WHERE ID_Menu = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, menu.getNama());
        pstmt.setFloat(2, menu.getHarga());
        pstmt.setInt(3, menu.getId());
        pstmt.executeUpdate();
    }

    public void deleteMenu(int id) throws SQLException {
        String sql = "DELETE FROM Menu WHERE ID_Menu = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }

    public float getHargaMenu(int idMenu) throws SQLException {
        String sql = "SELECT Harga FROM Menu WHERE ID_Menu = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idMenu);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getFloat("Harga");
        }
        return 0;
    }
}
