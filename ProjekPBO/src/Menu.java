public class Menu {
    private int id;
    private String nama;
    private float harga;

    public Menu(int id, String nama, float harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public float getHarga() {
        return harga;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setHarga(float harga) {
        this.harga = harga;
    }
}
