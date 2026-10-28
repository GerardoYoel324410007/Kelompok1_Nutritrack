# 🎤 Panduan Materi Presentasi UTS (PRESENTATION.md) - NutriTrack

Dokumen ini berisi poin-poin ringkas materi presentasi sebagai panduan saat mempresentasikan aplikasi **NutriTrack** di depan dosen penilai.

---

## 📌 1. Latar Belakang & Gagasan Utama (1 Menit)
- **Masalah**: Masyarakat sering kali hanya mementingkan rasa kenyang tanpa memperhatikan keragaman gizi seimbang.
- **Solusi NutriTrack**: Aplikasi edukasi gizi seimbang interaktif yang menghitung jumlah kelompok pangan unik harian secara *real-time* untuk mendorong kebiasaan makan bervariasi.

---

## 🏗️ 2. Arsitektur & Struktur Kode Aplikasi (1-2 Menit)
- **Jetpack Compose**: Antarmuka deklaratif tanpa XML View tradisional.
- **Modul Kode**:
  1. `Foodrecord.kt`: Data Model `FoodRecord` & Enum (`MealTime`, `FoodGroup`).
  2. `Nutritracklogic.kt`: Logika validasi input & perhitungan ringkasan keragaman gizi.
  3. `Stitchcolors.kt`: Palet warna desain Stitch Tailwind.
  4. `Components.kt`: Reusable UI Card Components (Summary Card, History Card, Empty State).
  5. `Nutritrackscreen.kt`: Screen Scaffold utama penampung Form & LazyColumn.

---

## 🧪 3. Skenario Demo & Validasi (2 Menit)
Saat demonstrasi aplikasi di emulator:
1. **Demo Initial State**:
   - Tunjukkan tampilan *Empty State* ("Belum Ada Catatan Makanan") dan metrik ringkasan bernilai 0.
2. **Demo Validasi Error**:
   - Biarkan nama makanan kosong -> Klik **TAMBAH** -> Tunjukkan pesan error merah.
   - Masukkan porsi bernilai negatif (`-1`) atau huruf (`abc`) -> Tunjukkan pesan error merah.
3. **Demo Penambahan Data & Keragaman Pangan**:
   - Tambah 1 Makanan Karbohidrat (Nasi Merah) -> Status: `Belum Bervariasi`.
   - Tambah 2 Makanan Tambahan (Lauk Protein & Sayuran) -> Status berubah otomatis menjadi `Cukup Bervariasi` (badge hijau) dengan tips edukatif pujian.
4. **Demo Interaksi Riwayat & Reset**:
   - Hapus salah satu makanan dari daftar riwayat -> Metrik ringkasan berkurang secara *real-time*.
   - Tekan tombol **RESET** -> Form input kembali ke kondisi awal.

---

## ❓ 4. Potensi Pertanyaan Dosen & Jawaban Kunci
- **Q**: *Bagaimana cara aplikasi menghitung keragaman pangan?*  
  **A**: Melalui logika `records.map { it.foodGroup }.distinct().size` di `NutriTrackLogic.kt`. Logika ini menyaring kelompok pangan yang unik dari daftar catatan.
- **Q**: *Apakah data tersimpan permanen?*  
  **A**: Pada versi UTS, data disimpan dalam *In-Memory State* Compose `mutableStateListOf`. Pada rencana versi UAS, akan diintegrasikan dengan database **Room**.
