## Desain Program
Aplikasi ini adalah sistem manajemen data produk buku yang mengimplementasikan fungsionalitas CRUD (Create, Read, Update, Delete). Program ini dirancang dengan memisahkan logika ke dalam tiga class utama untuk menjaga struktur kode yang baik.

1. **Class Product (Model Data)**
   
    Class ini berfungsi sebagai blueprint atau model untuk merepresentasikan objek sebuah buku.
    
    **Atribut :**
    
    - id : String (ID unik untuk setiap buku).
    
    - nama : String (Judul buku).
    
    - harga : double (Harga buku).
    
    - kategori : String (Genre buku, misal: Novel, Fiksi, dll).
    
    - rating : int (Peringkat buku dari 1 hingga 5).
    
    **Method :**
    
    - Konstruktor: Untuk inisialisasi objek Product baru dengan semua atribut yang diperlukan.
    
    - Getter dan Setter: Kumpulan method untuk mengakses (get) dan memodifikasi (set) setiap atribut privat.

2. **Class Database (Akses Data)**
   
    Class ini bertanggung jawab penuh atas semua interaksi dengan database MySQL, bertindak sebagai lapisan akses data.

    **Koneksi :** Saat diinisialisasi, class ini secara otomatis membuat koneksi ke database menggunakan Java Database Connectivity (JDBC).

   Konfigurasi koneksi adalah sebagai berikut :

      - URL : jdbc:mysql://localhost:3306/db_product 
      - User : root 
      - Password : "" (kosong)
        

    **Method :**
   
   - selectQuery(String sql) : Mengeksekusi perintah SELECT dan mengembalikan hasilnya dalam bentuk objek ResultSet.
   
   - insertUpdateDeleteQuery(String sql): Mengeksekusi perintah INSERT, UPDATE, atau DELETE dan mengembalikan jumlah baris yang terpengaruh oleh query tersebut.
   
   - isIdExists(String id) : Method khusus untuk memeriksa apakah sebuah ID sudah ada di dalam tabel product, berguna untuk validasi data duplikat.

4. **Class ProductMenu (GUI & Controller)**
   
    Class ini adalah inti dari aplikasi yang dilihat dan dioperasikan oleh pengguna. Dibuat menggunakan Java Swing, class ini berfungsi sebagai View (Tampilan) sekaligus Controller (Pengontrol Logika).
    
    **Elemen Utama GUI :**
    
      - JTextField : Untuk input ID, Nama, dan Harga produk.
      
      - JComboBox : Untuk memilih Kategori buku.
      
      - JSlider : Untuk memberikan nilai Rating dari 1 sampai 5.
      
      - JTable : Menampilkan daftar semua produk dari database.
      
      - JButton : Tombol fungsional seperti "Add" / "Update", "Delete", dan "Cancel".

## Alur Program (Flow Code)

  1. **Inisialisasi dan Tampilan Data (Read)**
        - Saat aplikasi dijalankan, constructor dari KarakterMenu akan membuat objek Database untuk membuka koneksi ke MySQL.
        - Method setTable() langsung dipanggil. Method ini mengeksekusi query SELECT * FROM product menggunakan database.selectQuery().
        - Data yang diterima dari ResultSet kemudian diiterasi dan setiap barisnya ditambahkan ke dalam JTable untuk ditampilkan kepada pengguna.
  
  2. **Menambahkan Produk Baru (Create)**
        - Pengguna mengisi seluruh field pada form (ID, Nama, Harga, Kategori, Rating) lalu menekan tombol "Add".
        - Method insertData() dipanggil dan melakukan dua validasi utama:
        - Validasi Kolom Kosong: Memeriksa apakah ada field input yang belum diisi. Jika ada, dialog error akan ditampilkan.
        - Validasi ID Duplikat: Memanggil database.isIdExists() untuk memastikan ID yang dimasukkan belum ada di database. Jika sudah ada, dialog error akan muncul.
        - Jika validasi berhasil, program menyusun string query INSERT dan mengirimkannya ke database melalui database.insertUpdateDeleteQuery().
        - Setelah data berhasil disimpan, tabel dimuat ulang dengan data terbaru dari database (setTable()), dan semua field di form dikosongkan (clearForm()).
  
  3. **Mengubah Produk (Update)**
        - Pengguna memilih salah satu baris pada JTable.  
        - Aksi ini akan memicu mouse listener yang akan mengambil semua data dari baris yang dipilih dan menampilkannya di form . Tombol "Add" otomatis berubah teks menjadi "Update", dan tombol "Delete" menjadi terlihat .
        - Pengguna mengubah data pada form lalu menekan tombol "Update".
        - Method updateData() akan dipanggil. Program akan menyusun query UPDATE dengan klausa WHERE yang menargetkan ID asli dari produk yang dipilih.
        - Setelah query dieksekusi, tabel akan dimuat ulang dan form akan dibersihkan.
  
  4. **Menghapus Produk (Delete)**
        - Pengguna memilih baris data yang ingin dihapus, yang membuat tombol "Delete" muncul.
        - Saat tombol "Delete" ditekan, sebuah dialog konfirmasi akan ditampilkan untuk memastikan tindakan pengguna.
        - Jika pengguna setuju, method deleteData() akan menyusun query DELETE berdasarkan ID produk yang dipilih.
        - Query dieksekusi, data dihapus dari database, lalu tabel dimuat ulang dan form dibersihkan.

  5. **Membatalkan Input (Cancel)**
        - Menekan tombol "Cancel" akan memanggil method clearForm().
        - Method ini akan mengosongkan semua JTextField, mengembalikan JComboBox dan JSlider ke posisi default, menyembunyikan tombol "Delete", dan mengubah teks tombol kembali menjadi "Add".
    
# Dokumentasi

### manjalankan CRUD
![bandicam 2025-10-16 23-19-27-250](https://github.com/user-attachments/assets/6dbee737-56d9-4fa8-afcc-4f582336c1eb)

### Sesudah ditambahkan
<img width="780" height="315" alt="image" src="https://github.com/user-attachments/assets/ab5f1d8b-a276-4a38-8569-94a788ca4b0d" />

### Sesudah diupdate
<img width="780" height="301" alt="image" src="https://github.com/user-attachments/assets/96962653-4f53-4bf5-adf8-d98f49a5f731" />

### Setelah dihapus
<img width="778" height="264" alt="image" src="https://github.com/user-attachments/assets/d9671a57-855d-4a1c-bcdf-0caf6112dcc5" />

## Error

### Input Error Id yang sama
<img width="1303" height="900" alt="image" src="https://github.com/user-attachments/assets/1c2293a3-b36b-43c7-aca5-11e8c6126d43" />

### Input Error kolom kosong
<img width="1262" height="968" alt="Cuplikan layar 2025-10-17 203557" src="https://github.com/user-attachments/assets/a31f27f3-ef70-46c1-b355-5c01378da884" />





