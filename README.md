# 🌿 NutriTrack - Edukasi Gizi Seimbang & Keragaman Pangan

**NutriTrack** adalah aplikasi Android berbasis **Jetpack Compose** yang dirancang untuk mengedukasi masyarakat mengenai pentingnya pola makan gizi seimbang dan keragaman kelompok pangan harian (sesuai panduan *Isi Piringku* Kementerian Kesehatan RI).

---

## 📌 1. Latar Belakang & Masalah
Banyak masyarakat Indonesia masih berfokus pada kuantitas makanan (rasa kenyang) dibandingkan kualitas dan keragaman jenis pangan. Konsumsi harian sering kali didominasi oleh karbohidrat tanpa variasi lauk pauk, sayur, dan buah yang memadai. 

NutriTrack hadir sebagai sarana **refleksi diri dan edukasi gizi harian sederhana** untuk membantu pengguna memantau berapa jenis kelompok pangan yang telah dikonsumsi setiap hari.

---

## 🎯 2. Pengguna Sasaran
- **Masyarakat umum & pelajar/mahasiswa** yang ingin mulai memperhatikan variasi makanan harian.
- **Orang tua / pengelola konsumsi keluarga** yang ingin memastikan bekal makanan keluarga mencakup gizi seimbang.

---

## ✨ 3. Fitur Utama (UTS)
1. **Form Input Makanan (5 Elemen Input)**:
   - Pilih Waktu Makan (*Chip Picker*: Sarapan, Makan Siang, Makan Malam, Camilan).
   - Input Nama Makanan (*OutlinedTextField*).
   - Pilih Kelompok Pangan (*FilterChip*: Karbohidrat, Protein Hewani, Protein Nabati, Sayur-sayuran, Buah-buahan).
   - Input Jumlah Porsi Numerik (*Stepper -/+ & OutlinedTextField*).
   - Catatan Opsional (*OutlinedTextField*).
2. **Ringkasan Gizi Harian (*Daily Summary Card*)**:
   - Total Catatan Makanan.
   - Perhitungan Kelompok Pangan Unik (Target minimal 3 dari 5 jenis pangan).
   - Total Porsi Terakumulasi.
   - Status Indicator Badge (`Belum Bervariasi` vs `Cukup Bervariasi`).
   - Tips Edukatif Otomatis.
3. **Riwayat Makan Hari Ini (*LazyColumn History List*)**:
   - Menampilkan daftar makanan secara terurut dari yang terbaru (*reverse order*).
   - Badge kelompok pangan dan porsi.
   - Tombol hapus catatan harian.
4. **Validasi Input & Penanganan Error**:
   - Peringatan warna merah saat nama makanan kosong, porsi bernilai huruf, atau porsi <= 0.
5. **Aksi Reset Form**:
   - Mengembalikan seluruh input form ke kondisi default secara cepat.

---

## 🧱 4. Model Data & Aturan Validasi

### Model Data (`FoodRecord.kt`)
```kotlin
data class FoodRecord(
    val id: Long = nextId(),
    val mealTime: MealTime,      // Enum: SARAPAN, MAKAN_SIANG, MAKAN_MALAM, CAMILAN
    val foodName: String,        // String nama makanan
    val foodGroup: FoodGroup,    // Enum: KARBOHIDRAT, PROTEIN_HEWANI, PROTEIN_NABATI, SAYURAN, BUAH
    val portion: Double,         // Numerik porsi (contoh: 1.0, 1.5)
    val notes: String,           // Catatan opsional
    val timestamp: String        // Waktu pencatatan
)
```

### Aturan Validasi Input (`NutriTrackLogic.kt`)
- **Nama Makanan**: Tidak boleh kosong atau hanya berisi spasi.
- **Jumlah Porsi**:
  - Wajib diisi.
  - Harus berupa angka desimal/numerik yang valid (misal `1.0` atau `1,5`).
  - Harus lebih besar dari `0.0`.
  - Batas porsi maksimal wajar: `10.0` porsi.

---

## 📸 5. Tampilan Aplikasi (*Screenshots*)

| Empty State | Validasi Error Red | Riwayat & Status Bervariasi |
| :---: | :---: | :---: |
| ![Empty State](docs/screenshots/screenshot_1_empty_state.png) | ![Error Validation](docs/screenshots/screenshot_2_error_validation.png) | ![History Filled](docs/screenshots/screenshot_3_history_filled.png) |

> *Catatan: Gambar screenshot tersimpan di folder `docs/screenshots/`.*

---

## 🧪 6. Ringkasan Hasil Pengujian Manual
Telah dilakukan 8 skenario pengujian manual utama (dokumen lengkap dapat dilihat di [`docs/test-cases.md`](docs/test-cases.md)):
- ✅ **TC-01**: Tampilan awal (Empty State & metrik nol) - **PASS**
- ✅ **TC-02**: Penambahan data valid - **PASS**
- ✅ **TC-03**: Validasi nama makanan kosong - **PASS**
- ✅ **TC-04**: Validasi porsi diisi karakter huruf - **PASS**
- ✅ **TC-05**: Validasi porsi bernilai negatif/nol - **PASS**
- ✅ **TC-06**: Peningkatan status keragaman saat mencapai 3 kelompok pangan - **PASS**
- ✅ **TC-07**: Perhitungan akumulasi porsi & penghapusan riwayat - **PASS**
- ✅ **TC-08**: Fungsi tombol reset form - **PASS**

---

## 🛠️ 7. Cara Menjalankan Aplikasi di Android Studio
1. Clone repositori ini ke komputer lokal Anda:
   ```bash
   git clone https://github.com/GerardoYoel324410007/Kelompok1_Nutritrack.git
   ```
2. Buka Android Studio (versi Ladybug 2024.2+ atau lebih baru).
3. Pilih **Open** dan arahkan ke folder proyek `Kelompok1_Nutritrack`.
4. Tunggu proses **Gradle Sync** selesai secara otomatis.
5. Jalankan aplikasi pada Emulator Android (API 24+) atau Perangkat Fisik dengan menekan tombol **Run (Shift + F10)**.

---

## 🚧 8. Batasan Aplikasi (UTS) & Rencana UAS

### Batasan Versi UTS:
- Penyimpanan data bersifat sementara (*In-Memory State*) menggunakan Compose `mutableStateListOf`. Data akan ter-reset saat aplikasi ditutup/direstart.

### Rencana Pengembangan Versi UAS:
- **Persistensi Data Lokal**: Integrasi database **Room** atau DataStore agar catatan makanan tersimpan secara permanen.
- **Grafik Tren Mingguan**: Menampilkan chart perkembangan konsumsi gizi harian/mingguan.
- **Rekomendasi Menu Interaktif**: Rekomendasi resep sayur/buah berdasarkan kelompok pangan yang belum terpenuhi.
