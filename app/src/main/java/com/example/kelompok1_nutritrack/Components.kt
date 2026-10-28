package com.example.kelompok1_nutritrack

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RequiredFieldLabel(text: String) {
    Text(
        text = buildAnnotatedString {
            append("$text ")
            withStyle(SpanStyle(color = StitchColors.Error, fontWeight = FontWeight.Bold)) {
                append("*")
            }
        },
        fontSize = 12.sp,
        fontWeight = FontWeight.Medium,
        color = StitchColors.OnSurface
    )
}

@Composable
fun StitchSummaryCard(summary: NutriSummary) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = StitchColors.SurfaceContainerLowest),
        border = BorderStroke(1.dp, StitchColors.OutlineVariant.copy(alpha = 0.5f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text("📊", fontSize = 16.sp)
                    Text(
                        text = "Ringkasan Gizi Harian",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = StitchColors.OnSurface
                    )
                }
                // Status Pill Badge ('Belum Bervariasi' vs 'Cukup Bervariasi')
                Surface(
                    shape = CircleShape,
                    color = if (summary.isDiverse) StitchColors.PrimaryFixed else StitchColors.TertiaryFixed
                ) {
                    Text(
                        text = summary.diversityStatus,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (summary.isDiverse) StitchColors.OnPrimaryFixedVariant else StitchColors.OnTertiaryFixed,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                }
            }

            // 3-Column Metrics Grid
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = StitchColors.SurfaceContainerLow.copy(alpha = 0.7f),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(vertical = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Catatan", fontSize = 12.sp, color = StitchColors.OnSurfaceVariant)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${summary.totalRecords} Makanan",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StitchColors.OnSurface
                        )
                    }

                    VerticalDivider(
                        color = StitchColors.OutlineVariant.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxHeight()
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Kelompok", fontSize = 12.sp, color = StitchColors.OnSurfaceVariant)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${summary.distinctGroupsCount} / 5 Jenis",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StitchColors.Primary
                        )
                    }

                    VerticalDivider(
                        color = StitchColors.OutlineVariant.copy(alpha = 0.6f),
                        modifier = Modifier.fillMaxHeight()
                    )

                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("Total Porsi", fontSize = 12.sp, color = StitchColors.OnSurfaceVariant)
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${NutriTrackLogic.formatPorsi(summary.totalPortions)} Porsi",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StitchColors.OnSurface
                        )
                    }
                }
            }

            HorizontalDivider(color = StitchColors.OutlineVariant.copy(alpha = 0.4f))

            // Educational Insight Tip
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.Top
            ) {
                Text("💡", fontSize = 14.sp)
                Text(
                    text = buildAnnotatedString {
                        withStyle(
                            SpanStyle(
                                fontWeight = FontWeight.SemiBold,
                                color = StitchColors.OnSurface
                            )
                        ) {
                            append("Tips: ")
                        }
                        append(summary.educationalMessage)
                    },
                    fontSize = 12.sp,
                    lineHeight = 16.sp,
                    color = StitchColors.OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun StitchHistoryItemCard(record: FoodRecord, onDelete: () -> Unit) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = StitchColors.SurfaceContainerLowest),
        border = BorderStroke(1.dp, StitchColors.OutlineVariant.copy(alpha = 0.6f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
                    Text(
                        text = "🕒 ${record.timestamp}",
                        fontSize = 12.sp,
                        color = StitchColors.OnSurfaceVariant
                    )
                    Text(
                        text = record.foodName,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = StitchColors.OnSurface
                    )
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(
                        shape = CircleShape,
                        color = StitchColors.SecondaryContainer
                    ) {
                        Text(
                            text = "${NutriTrackLogic.formatPorsi(record.portion)} Porsi",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = StitchColors.OnSecondaryContainer,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    IconButton(
                        onClick = onDelete,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Hapus Catatan",
                            tint = StitchColors.OnSurfaceVariant,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // Badges untuk Waktu Makan & Kelompok Makanan
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = StitchColors.SurfaceContainer
                ) {
                    Text(
                        text = record.mealTime.label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = StitchColors.OnSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                val isAnimalOrCarb = record.foodGroup == FoodGroup.KARBOHIDRAT || record.foodGroup == FoodGroup.SAYURAN
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = if (isAnimalOrCarb) StitchColors.PrimaryFixed.copy(alpha = 0.6f) else StitchColors.TertiaryFixed.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = record.foodGroup.label,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (isAnimalOrCarb) StitchColors.OnPrimaryFixedVariant else StitchColors.OnTertiaryFixed,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            if (record.notes.isNotEmpty()) {
                Text(
                    text = "\"${record.notes}\"",
                    fontSize = 12.sp,
                    fontStyle = FontStyle.Italic,
                    color = StitchColors.OnSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun StitchEmptyStateCard() {
    val dashColor = StitchColors.OutlineVariant
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = StitchColors.SurfaceContainerLowest.copy(alpha = 0.5f),
                shape = RoundedCornerShape(16.dp)
            )
            .drawBehind {
                drawRoundRect(
                    color = dashColor,
                    style = Stroke(
                        width = 2.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(15f, 15f), 0f)
                    ),
                    cornerRadius = CornerRadius(16.dp.toPx())
                )
            }
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Surface(
                shape = CircleShape,
                color = StitchColors.SurfaceContainer,
                modifier = Modifier.size(56.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text("🍱", fontSize = 26.sp)
                }
            }
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = "Belum Ada Catatan Makanan",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = StitchColors.OnSurface
            )
            Text(
                text = "Isi formulir di atas untuk mulai memantau keragaman pangan dan kualitas gizi harian Anda.",
                fontSize = 12.sp,
                color = StitchColors.OnSurfaceVariant,
                textAlign = TextAlign.Center,
                lineHeight = 16.sp
            )
        }
    }
}