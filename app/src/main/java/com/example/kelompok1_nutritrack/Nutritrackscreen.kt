package com.example.kelompok1_nutritrack

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun NutriTrackScreen() {
    // State Daftar Sementara (Syarat 6.3)
    val foodRecords = remember { mutableStateListOf<FoodRecord>() }

    // State Form Input
    var selectedMealTime by remember { mutableStateOf(MealTime.SARAPAN) }
    var foodNameInput by remember { mutableStateOf("") }
    var selectedFoodGroup by remember { mutableStateOf(FoodGroup.KARBOHIDRAT) }
    var portionInput by remember { mutableStateOf("1.0") }
    var notesInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    val summary = NutriTrackLogic.calculateSummary(foodRecords)

    fun resetForm() {
        selectedMealTime = MealTime.SARAPAN
        foodNameInput = ""
        selectedFoodGroup = FoodGroup.KARBOHIDRAT
        portionInput = "1.0"
        notesInput = ""
        errorMessage = null
    }

    Scaffold(
        containerColor = StitchColors.SurfaceContainerLow,
        topBar = {
            // 1. Top App Bar sesuai Stitch
            Surface(
                color = StitchColors.Surface,
                shadowElevation = 2.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .padding(horizontal = 16.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "🌿", fontSize = 22.sp)
                        Column {
                            Text(
                                text = "NutriTrack",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = StitchColors.OnSurface,
                            )
                            Text(
                                text = "Edukasi Gizi Seimbang & Keragaman Pangan",
                                fontSize = 12.sp,
                                color = StitchColors.OnSurfaceVariant,
                            )
                        }
                    }
                    IconButton(onClick = { /* Menu opsional */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Menu Lainnya",
                            tint = StitchColors.OnSurface,
                        )
                    }
                }
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 12.dp, bottom = 32.dp),
        ) {
            // 2. Safety & Ethics Disclaimer Banner
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = StitchColors.SurfaceVariant.copy(alpha = 0.5f),
                    ),
                    border = BorderStroke(1.dp, StitchColors.OutlineVariant.copy(alpha = 0.6f)),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.Top,
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = "Info",
                            tint = StitchColors.Primary,
                            modifier = Modifier
                                .size(20.dp)
                                .padding(top = 2.dp),
                        )
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    SpanStyle(
                                        fontWeight = FontWeight.SemiBold,
                                        color = StitchColors.OnSurface,
                                    ),
                                ) {
                                    append("Catatan: ")
                                }
                                append("Indikator hanya bersifat edukatif dan refleksi diri, bukan penilaian klinis atau diagnosis status gizi.")
                            },
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = StitchColors.OnSurfaceVariant,
                        )
                    }
                }
            }

            // 3. Daily Nutrition Summary Card
            item {
                StitchSummaryCard(summary = summary)
            }

            // 4. Food Input Form Card
            item {
                Card(
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = StitchColors.SurfaceContainerLowest),
                    border = BorderStroke(1.dp, StitchColors.OutlineVariant.copy(alpha = 0.5f)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(14.dp),
                    ) {
                        // Header Form
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                            ) {
                                Text("📝", fontSize = 16.sp)
                                Text(
                                    text = "Tambah Catatan Makanan",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium,
                                    color = StitchColors.OnSurface,
                                )
                            }
                            HorizontalDivider(color = StitchColors.OutlineVariant.copy(alpha = 0.3f))
                        }

                        // Input 1: Waktu Makan (Chips)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "1. Waktu Makan:",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = StitchColors.OnSurface,
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                MealTime.entries.forEach { meal ->
                                    val isSelected = selectedMealTime == meal
                                    Surface(
                                        shape = CircleShape,
                                        color = if (isSelected) StitchColors.Primary else Color.Transparent,
                                        border = if (isSelected) null else BorderStroke(1.dp, StitchColors.OutlineVariant),
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .clickable { selectedMealTime = meal },
                                    ) {
                                        Text(
                                            text = meal.label,
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = if (isSelected) StitchColors.OnPrimary else StitchColors.Secondary,
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                                        )
                                    }
                                }
                            }
                        }

                        // Input 2: Nama Makanan (OutlinedTextField)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            RequiredFieldLabel("2. Nama Makanan")
                            OutlinedTextField(
                                value = foodNameInput,
                                onValueChange = {
                                    foodNameInput = it
                                    errorMessage = null
                                },
                                placeholder = {
                                    Text(
                                        "Contoh: Nasi Merah, Dada Ayam, Bayam",
                                        fontSize = 13.sp,
                                        color = StitchColors.Outline,
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = StitchColors.Primary,
                                    unfocusedBorderColor = StitchColors.OutlineVariant,
                                    focusedContainerColor = StitchColors.SurfaceContainerLowest,
                                    unfocusedContainerColor = StitchColors.SurfaceContainerLowest,
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        // Input 3: Kelompok Makanan (Wrap Chips)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            RequiredFieldLabel("3. Kelompok Makanan")
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                            ) {
                                FoodGroup.entries.forEach { group ->
                                    val isSelected = selectedFoodGroup == group
                                    Surface(
                                        shape = CircleShape,
                                        color = if (isSelected) StitchColors.SecondaryContainer else Color.Transparent,
                                        border = if (isSelected) null else BorderStroke(1.dp, StitchColors.OutlineVariant),
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .clickable { selectedFoodGroup = group },
                                    ) {
                                        Text(
                                            text = group.label,
                                            fontSize = 12.sp,
                                            fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                                            color = if (isSelected) StitchColors.OnSecondaryContainer else StitchColors.Secondary,
                                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                        )
                                    }
                                }
                            }
                        }

                        // Input 4: Jumlah Porsi Sederhana (Stepper + TextField untuk Uji Validasi)
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            RequiredFieldLabel("4. Jumlah Porsi Sederhana")
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = StitchColors.SurfaceContainerLow,
                                border = BorderStroke(1.dp, StitchColors.OutlineVariant.copy(alpha = 0.6f)),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 12.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(
                                            text = "Takaran Porsi",
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = StitchColors.OnSurface,
                                        )
                                        Text(
                                            text = "Ketik angka atau tekan - / +",
                                            fontSize = 11.sp,
                                            color = StitchColors.OnSurfaceVariant,
                                        )
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                    ) {
                                        // Tombol Kurang (-)
                                        FilledIconButton(
                                            onClick = {
                                                val current = portionInput.replace(",", ".").toDoubleOrNull() ?: 1.0
                                                val next = (current - 0.5).coerceAtLeast(0.5)
                                                portionInput = NutriTrackLogic.formatPorsi(next)
                                                errorMessage = null
                                            },
                                            modifier = Modifier.size(34.dp),
                                            colors = IconButtonDefaults.filledIconButtonColors(
                                                containerColor = StitchColors.SurfaceContainerLowest,
                                                contentColor = StitchColors.OnSurface,
                                            ),
                                        ) {
                                            Text("−", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                        }

                                        // Kotak Input Porsi
                                        OutlinedTextField(
                                            value = portionInput,
                                            onValueChange = {
                                                portionInput = it
                                                errorMessage = null
                                            },
                                            singleLine = true,
                                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                                            textStyle = LocalTextStyle.current.copy(
                                                textAlign = TextAlign.Center,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp,
                                            ),
                                            shape = RoundedCornerShape(10.dp),
                                            colors = OutlinedTextFieldDefaults.colors(
                                                focusedContainerColor = StitchColors.SurfaceContainerLowest,
                                                unfocusedContainerColor = StitchColors.SurfaceContainerLowest,
                                                focusedBorderColor = StitchColors.Primary,
                                                unfocusedBorderColor = StitchColors.OutlineVariant,
                                            ),
                                            modifier = Modifier.width(76.dp),
                                        )

                                        // Tombol Tambah (+)
                                        FilledIconButton(
                                            onClick = {
                                                val current = portionInput.replace(",", ".").toDoubleOrNull() ?: 0.5
                                                val next = (current + 0.5).coerceAtMost(10.0)
                                                portionInput = NutriTrackLogic.formatPorsi(next)
                                                errorMessage = null
                                            },
                                            modifier = Modifier.size(34.dp),
                                            colors = IconButtonDefaults.filledIconButtonColors(
                                                containerColor = StitchColors.SurfaceContainerLowest,
                                                contentColor = StitchColors.OnSurface,
                                            ),
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Tambah Porsi",
                                                modifier = Modifier.size(18.dp),
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // Input 5: Catatan Opsional
                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Text(
                                text = "5. Catatan (Opsional)",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium,
                                color = StitchColors.OnSurface,
                            )
                            OutlinedTextField(
                                value = notesInput,
                                onValueChange = { notesInput = it },
                                placeholder = {
                                    Text(
                                        "Contoh: Tanpa santan / dikukus / bumbu ringan",
                                        fontSize = 13.sp,
                                        color = StitchColors.Outline,
                                    )
                                },
                                singleLine = true,
                                shape = RoundedCornerShape(12.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = StitchColors.Primary,
                                    unfocusedBorderColor = StitchColors.OutlineVariant,
                                    focusedContainerColor = StitchColors.SurfaceContainerLowest,
                                    unfocusedContainerColor = StitchColors.SurfaceContainerLowest,
                                ),
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }

                        // Kotak Pesan Validasi Error
                        val currentError = errorMessage
                        if (currentError != null) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = StitchColors.ErrorContainer.copy(alpha = 0.7f),
                                border = BorderStroke(1.dp, StitchColors.Error.copy(alpha = 0.3f)),
                                modifier = Modifier.fillMaxWidth(),
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = "Error",
                                        tint = StitchColors.Error,
                                        modifier = Modifier.size(18.dp),
                                    )
                                    Text(
                                        text = currentError,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = StitchColors.OnErrorContainer,
                                    )
                                }
                            }
                        }

                        // Baris Tombol Aksi: RESET & TAMBAH
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            OutlinedButton(
                                onClick = { resetForm() },
                                shape = CircleShape,
                                border = BorderStroke(1.dp, StitchColors.Outline),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    contentColor = StitchColors.Secondary,
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Reset",
                                    modifier = Modifier.size(18.dp),
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("RESET", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            }

                            Button(
                                onClick = {
                                    val validationError = NutriTrackLogic.validateInput(
                                        foodName = foodNameInput,
                                        portionText = portionInput,
                                    )
                                    if (validationError != null) {
                                        errorMessage = validationError
                                    } else {
                                        val validPortion = portionInput.trim().replace(",", ".").toDouble()
                                        val newRecord = FoodRecord(
                                            mealTime = selectedMealTime,
                                            foodName = foodNameInput.trim(),
                                            foodGroup = selectedFoodGroup,
                                            portion = validPortion,
                                            notes = notesInput.trim(),
                                        )
                                        foodRecords.add(0, newRecord)
                                        resetForm()
                                    }
                                },
                                shape = CircleShape,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = StitchColors.Primary,
                                    contentColor = StitchColors.OnPrimary,
                                ),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(48.dp),
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Tambah",
                                    modifier = Modifier.size(18.dp),
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("TAMBAH", fontWeight = FontWeight.SemiBold, fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            // 5. Header Daftar Riwayat Makan Hari Ini
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = "Riwayat Makan Hari Ini",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = StitchColors.OnSurface,
                        )
                        Surface(
                            shape = CircleShape,
                            color = if (foodRecords.isEmpty()) StitchColors.SurfaceVariant else StitchColors.PrimaryFixed,
                        ) {
                            Text(
                                text = foodRecords.size.toString(),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (foodRecords.isEmpty()) StitchColors.OnSurfaceVariant else StitchColors.OnPrimaryFixedVariant,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
                            )
                        }
                    }
                }
            }

            // 6. Empty State atau Populated List
            if (foodRecords.isEmpty()) {
                item {
                    StitchEmptyStateCard()
                }
            } else {
                items(foodRecords, key = { it.id }) { record ->
                    StitchHistoryItemCard(record = record) {
                        foodRecords.remove(record)
                    }
                }
            }
        }
    }
}
