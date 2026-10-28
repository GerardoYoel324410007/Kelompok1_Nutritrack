package com.example.kelompok1_nutritrack

import kotlin.math.roundToInt

data class NutriSummary(
    val totalRecords: Int,
    val distinctGroupsCount: Int,
    val totalPortions: Double,
    val diversityStatus: String,
    val educationalMessage: String,
    val isDiverse: Boolean
)

object NutriTrackLogic {

    // Pengganti String.format(Locale.US, "%.1f", value): satu angka desimal, contoh 1.5
    fun formatPorsi(value: Double): String =
        ((value * 10).roundToInt() / 10.0).toString()

    fun validateInput(foodName: String, portionText: String): String? {
        if (foodName.trim().isEmpty()) {
            return "Nama makanan tidak boleh kosong."
        }
        if (portionText.trim().isEmpty()) {
            return "Jumlah porsi wajib diisi."
        }

        val portion = portionText.trim().replace(",", ".").toDoubleOrNull()
            ?: return "Jumlah porsi harus berupa angka yang valid (contoh: 1.0 atau 1.5)."

        if (portion <= 0.0) {
            return "Jumlah porsi harus lebih besar dari 0."
        }
        if (portion > 10.0) {
            return "Jumlah porsi tidak wajar (maksimal 10 porsi)."
        }

        return null
    }

    fun calculateSummary(records: List<FoodRecord>): NutriSummary {
        val totalRecords = records.size
        val distinctGroupsCount = records.map { it.foodGroup }.distinct().size
        val totalPortions = records.sumOf { it.portion }

        val isDiverse = distinctGroupsCount >= 3
        val diversityStatus = if (isDiverse) "Cukup Bervariasi" else "Belum Bervariasi"

        val educationalMessage = when {
            totalRecords == 0 -> "Coba tambahkan variasi sayuran hijau, lauk protein, atau buah segar untuk melengkapi piring gizi seimbang hari ini!"
            !isDiverse -> "Baru mencakup $distinctGroupsCount dari 5 jenis pangan. Coba tambahkan variasi sayuran hijau atau buah segar untuk melengkapi piring gizi seimbang hari ini!"
            else -> "Hebat! Konsumsi harianmu sudah mencakup $distinctGroupsCount jenis kelompok pangan yang berbeda."
        }

        return NutriSummary(
            totalRecords = totalRecords,
            distinctGroupsCount = distinctGroupsCount,
            totalPortions = totalPortions,
            diversityStatus = diversityStatus,
            educationalMessage = educationalMessage,
            isDiverse = isDiverse
        )
    }
}