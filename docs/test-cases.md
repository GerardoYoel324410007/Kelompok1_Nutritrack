# 🧪 Tabel Pengujian Manual (test-cases.md) - NutriTrack

Dokumen ini berisi 8 skenario pengujian manual wajib untuk aplikasi **NutriTrack** (Edukasi Gizi Seimbang & Keragaman Pangan).

---

### 📋 Tabel Skenario Pengujian Manual

| ID Skenario | Nama Skenario Uji | Langkah Pengujian | Input Data | Hasil yang Diharapkan (*Expected Output*) | Status |
| :---: | :--- | :--- | :--- | :--- | :---: |
| **TC-01** | Aplikasi Pertama Dibuka | Buka aplikasi NutriTrack pertama kali di emulator / perangkat. | - | TopAppBar & Disclaimer Banner muncul. Ringkasan Gizi menunjukkan `0 Makanan`, `0 / 5 Jenis`, `0.0 Porsi`, status `Belum Bervariasi`, dan Card *Empty State* ("Belum Ada Catatan Makanan") tampil. | **PASS** |
| **TC-02** | Input Valid (Tambah Catatan) | Pilih waktu makan, isi nama makanan valid, pilih kelompok pangan, atur porsi valid, dan tekan **TAMBAH**. | Waktu: `Sarapan`<br>Nama: `Nasi Merah`<br>Kelompok: `Karbohidrat`<br>Porsi: `1.0`<br>Catatan: `Organik` | Data berhasil ditambahkan ke daftar riwayat (*LazyColumn*) di urutan teratas. Ringkasan terupdate (`1 Makanan`, `1 / 5 Jenis`, `1.0 Porsi`), form input otomatis di-reset. | **PASS** |
| **TC-03** | Input Wajib Kosong | Kosongkan field Nama Makanan, lalu tekan tombol **TAMBAH**. | Nama: ` ` (Kosong)<br>Porsi: `1.0` | Pesan error merah muncul: *"Nama makanan tidak boleh kosong."*. Data tidak bertambah ke daftar riwayat. | **PASS** |
| **TC-04** | Porsi Diisi Huruf / Karakter Non-Angka | Ketik karakter huruf pada kotak input porsi, lalu tekan **TAMBAH**. | Nama: `Ayam Bakar`<br>Porsi: `abc` | Pesan error merah muncul: *"Jumlah porsi harus berupa angka yang valid (contoh: 1.0 atau 1.5)."*. Data tidak tersimpan. | **PASS** |
| **TC-05** | Porsi Bernilai Negatif atau Nol | Ketik angka `0` atau `-2` pada input porsi, lalu tekan **TAMBAH**. | Nama: `Susu UHT`<br>Porsi: `-1.0` | Pesan error merah muncul: *"Jumlah porsi harus lebih besar dari 0."*. Data tidak tersimpan. | **PASS** |
| **TC-06** | Tambah Beberapa Data (Keragaman Pangan) | Tambahkan 3 makanan dengan kelompok pangan berbeda (misal: Karbohidrat, Protein Hewani, Sayuran). | 1. Nasi (Karbo)<br>2. Telur (Protein Hewani)<br>3. Bayam (Sayuran) | Jumlah kelompok pangan menjadi `3 / 5 Jenis`. Status badge berubah dari `Belum Bervariasi` (kuning) menjadi `Cukup Bervariasi` (hijau), dan pesan edukatif terupdate pujian. | **PASS** |
| **TC-07** | Cek Perhitungan Ringkasan & Tips Edukatif | Amati perubahan metrik ringkasan gizi harian setelah menambah & menghapus catatan. | Tambah porsi `1.5` & `2.0`<br>Hapus 1 item | Total porsi terakumulasi secara presisi (contoh: `3.5 Porsi`). Saat item dihapus, jumlah catatan, kelompok unik, dan total porsi berkurang secara *real-time*. | **PASS** |
| **TC-08** | Uji Tombol Reset Form | Isi beberapa field form (nama, catatan, ubah porsi), lalu tekan tombol **RESET**. | Nama: `Ikan Goreng`<br>Porsi: `2.5`<br>Catatan: `Pedes` | Seluruh input form kembali ke keadaan default (Sarapan, Nama Kosong, Karbohidrat, Porsi `1.0`, Catatan Kosong) dan pesan error hilang. | **PASS** |

---

### 📝 Catatan Tambahan Pengujian:
- Seluruh pengujian di atas dilakukan secara manual pada perangkat android kami.
- Penanganan angka desimal dengan koma (misal `1,5`) otomatis dikonversi menjadi `1.5` tanpa memicu crash.
