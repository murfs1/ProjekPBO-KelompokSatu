import java.sql.Date;

public class Pesanan {
    private int id;
    private int idPelanggan;
    private int idMenu;
    private Date tanggalPesanan;
    private int jumlah;
    private float totalHarga;

    public Pesanan(int id, int idPelanggan, int idMenu, Date tanggalPesanan, int jumlah, float totalHarga) {
        this.id = id;
        this.idPelanggan = idPelanggan;
        this.idMenu = idMenu;
        this.tanggalPesanan = tanggalPesanan;
        this.jumlah = jumlah;
        this.totalHarga = totalHarga;
    }

    public int getId() {
        return id;
    }

    public int getIdPelanggan() {
        return idPelanggan;
    }

    public int getIdMenu() {
        return idMenu;
    }

    public Date getTanggalPesanan() {
        return tanggalPesanan;
    }

    public int getJumlah() {
        return jumlah;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdPelanggan(int idPelanggan) {
        this.idPelanggan = idPelanggan;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public void setTanggalPesanan(Date tanggalPesanan) {
        this.tanggalPesanan = tanggalPesanan;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public void setTotalHarga(float totalHarga) {
        this.totalHarga = totalHarga;
    }
}
