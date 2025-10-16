public class Product {
    private String id;
    private String nama;
    private double harga;
    private String kategori;
    private int rating; // atribut baru

    public Product(String id, String nama, double harga, String kategori, int rating) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
        this.rating = rating;
    }

    public String getId() { return id; }
    public String getNama() { return nama; }
    public double getHarga() { return harga; }
    public String getKategori() { return kategori; }
    public int getRating() { return rating; }

    public void setId(String id) { this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setHarga(double harga) { this.harga = harga; }
    public void setKategori(String kategori) { this.kategori = kategori; }
    public void setRating(int rating) { this.rating = rating; }
}
