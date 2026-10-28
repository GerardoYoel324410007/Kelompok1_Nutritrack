# Catatan Penggunaan AI (AI_USAGE.md)

Dokumen ini mencatat penggunaan alat Bantu AI selama pengerjaan proyek UTS **NutriTrack** oleh Kelompok 1 secara jujur dan transparan.

---

### 📋 Tabel Catatan Penggunaan AI

| Tanggal | Alat AI | Prompt (Pertanyaan Mahasiswa) | Bagian digunakan | Modifikasi / Pemahaman Mahasiswa |
| :--- | :--- | :--- | :--- | :--- |
| **27 Okt 2026** | Android Studio AI (Gemini) | "Bagaimana cara menentukan palet warna dan komponen UI di Jetpack Compose agar sesuai dengan skema desain Stitch Tailwind? Maksud saya bukan tampilan yang terlalu kompleks, melainkan rancangan simpel." | Penentuan warna di `Stitchcolors.kt` & rancangan Wireframe | Penerapan skema warna `Color(0xFF...)` di Jetpack Compose sesuai dengan palet warna Stitch Tailwind. |
| **27 Okt 2026** | Android Studio AI (Gemini) | "Bagaimana struktur model data dan logika pencatatan makanan di Android? Maksud saya bukan hanya sekadar menyimpan input, melainkan ada validasi nilai porsi numerik serta perhitungan otomatis dari 5 kelompok pangan." | Struktur file `Foodrecord.kt` & `Nutritracklogic.kt` | Penerapan perhitungan kelompok pangan unik menggunakan `distinct()` serta fungsi validasi input porsi numerik. |
| **28 Okt 2026** | Android Studio AI (Gemini) | "Mengapa terjadi error 'Cannot add extension with name kotlin, as there is an extension already registered' saat proses sync Gradle? Mohon tunjukkan letak kesalahan pada file build.gradle.kts beserta langkah solusinya." | Memahami & menyelesaikan error di `build.gradle.kts` | Pembersihan konfigurasi plugin Kotlin ganda pada `build.gradle.kts` karena sudah terintegrasi bawaan di AGP 9+. |
| **28 Okt 2026** | Android Studio AI (Gemini) | "Tolong kumpulkan seluruh kode dari berkas Kotlin dalam proyek ini ke dalam satu dokumen Markdown. Eh bentar, maksud saya disalin lengkap ya bukan dirangkum atau dipotong-potong." | Pembuatan file dokumentasi kode proyek (`Dokumentasi_Kode_NutriTrack.md`) | Penyusunan dan penelaahan dokumentasi struktur kode dari seluruh berkas proyek NutriTrack. |
| **28 Okt 2026** | Android Studio AI (Gemini) | "Bagaimana urutan perintah Git di terminal untuk melakukan commit berkas ke GitHub? Eh bentar, bukan melakukan push sekaligus untuk seluruh proyek, melainkan commit satu per satu per file agar riwayat commit di repositori rapi." | Panduan eksekusi perintah Git & Gradle (`git status`, `git add`, `git commit`, `git restore`, `git log`, `git push`, dan Gradle Build) | Pelaksanaan alur commit bertahap per berkas dan penanganan status warning/error pada terminal Git. |

---

### 💻 Rincian Perintah Terminal yang Digunakan & Dipelajari:

1. **`git status`**: Mengecek status berkas mana saja yang telah diubah, ter-stage, atau belum dilacak (*untracked*).
2. **`git restore --staged .`**: Membatalkan penambahan berkas di staging area agar bisa melakukan commit satu per satu.
3. **`git add <file>`**: Menambahkan berkas tertentu ke staging area sebelum di-commit.
4. **`git commit -m "<pesan>"`**: Menyimpan rekam perubahan (*snapshot*) dengan pesan commit yang terstruktur.
5. **`git log`**: Melihat daftar riwayat commit yang telah berhasil dibuat.
6. **`git push origin main`**: Mengunggah seluruh commit lokal ke repositori GitHub.
7. **`./gradlew assembleDebug` / Gradle Sync**: Menjalankan proses kompilasi untuk memastikan tidak ada error pada kode sebelum di-commit.
