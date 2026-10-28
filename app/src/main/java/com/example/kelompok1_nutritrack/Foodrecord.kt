package com.example.kelompok1_nutritrack

import android.icu.util.Calendar
import android.text.format.DateFormat

enum class MealTime(val label: String) {
    SARAPAN("Sarapan"),
    MAKAN_SIANG("Makan Siang"),
    MAKAN_MALAM("Makan Malam"),
    CAMILAN("Camilan")
}

enum class FoodGroup(val label: String) {
    KARBOHIDRAT("Karbohidrat"),
    PROTEIN_HEWANI("Protein Hewani"),
    PROTEIN_NABATI("Protein Nabati"),
    SAYURAN("Sayur-sayuran"),
    BUAH("Buah-buahan")
}

// ID otomatis bertambah 1, 2, 3, ... (pengganti System.currentTimeMillis)
private var idCounter = 0L
private fun nextId(): Long = ++idCounter

// Jam saat ini, contoh: "08:30 WIB" (tanpa API Java)
private fun currentTimeLabel(): String {
    val millis = Calendar.getInstance().timeInMillis
    return "${DateFormat.format("HH:mm", millis)} WIB"
}

data class FoodRecord(
    val id: Long = nextId(),
    val mealTime: MealTime,      // 1. Waktu makan
    val foodName: String,        // 2. Nama makanan
    val foodGroup: FoodGroup,    // 3. Kelompok makanan
    val portion: Double,         // 4. Jumlah porsi sederhana (Numerik)
    val notes: String,           // 5. Catatan
    val timestamp: String = currentTimeLabel()
)