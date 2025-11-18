package com.example.healthcare.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.* // Mengandung semua ikon yang Anda gunakan
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.healthcare.R

@Composable
fun HomeScreen(
    onSistemPakarClick: () -> Unit = {},
    onArtikelClick: () -> Unit = {},
    onHasilAnalisaClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        /* ---------------------- HEADER ---------------------- */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
        ) {
            // ... (Kode untuk Background Image dan Gradient Overlay tidak berubah)

            // Background Image
            Image(
                painter = painterResource(id = R.drawable.background),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Gradient overlay
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.White),
                            startY = 0f,
                            endY = 900f
                        )
                    )
            )

            // Text + Button
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                Text(
                    text = "SELAMAT DATANG",
                    fontSize = 20.sp,
                    color = Color.White
                )
                Text(
                    text = "DI HEALTHCARE",
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3)),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(text = "Tentang Kita", color = Color.White)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        /* ---------------------- MENU CARD ---------------------- */
        Column(modifier = Modifier.padding(16.dp)) {

            HomeServiceCard(
                title = "Sistem Pakar",
                desc = "Diagnosa Penyakit",
                icon = Icons.Default.Psychology,
                onClick = onSistemPakarClick
            )

            // Item Rumah Sakit Dihapus

            HomeServiceCard(
                title = "Blog Kesehatan",
                desc = "Artikel Peduli Kesehatan",
                icon = Icons.Default.Article,
                onClick = onArtikelClick
            )

            HomeServiceCard(
                title = "Hasil Analisa",
                desc = "Jurnal Sistem Pakar",
                icon = Icons.Default.Assessment,
                onClick = onHasilAnalisaClick
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        /* ---------------------- SECTION SISTEM PAKAR ---------------------- */
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Sistem Pakar",
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Sistem pakar adalah sistem komputer yang meniru kemampuan seorang pakar untuk memberikan keputusan atau diagnosis.",
                fontSize = 14.sp,
                color = Color.Gray,
                lineHeight = 20.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Menggunakan modifikasi ContentScale.Fit yang sudah benar
            Image(
                painter = painterResource(id = R.drawable.home1),
                contentDescription = "Sistem Pakar",
                modifier = Modifier
                    .height(300.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }
    }
}


/* -----------------------------------------------------------
   CARD MENU — Versi Seperti Screenshot Kamu
------------------------------------------------------------ */
@Composable
fun HomeServiceCard(
// ... (Kode HomeServiceCard tidak berubah)
    title: String,
    desc: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Icon box
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color(0xFF2196F3)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Color.White)
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(text = title, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = desc, fontSize = 13.sp, color = Color.Gray)
            }

            Icon(
                Icons.Default.ArrowForward,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}