package com.gigconnect.app.ui.onboarding

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import com.gigconnect.app.data.demo.DemoDataProvider
import com.gigconnect.app.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationSelectionScreen(onNext: (String, String) -> Unit) {
    val cities = DemoDataProvider.demoCities
    val states = cities.map { it.state }.distinct()

    var selectedState by remember { mutableStateOf(states.first()) }
    var selectedCity by remember { mutableStateOf(cities.first()) }
    var stateExpanded by remember { mutableStateOf(false) }
    var cityExpanded by remember { mutableStateOf(false) }

    val filteredCities = cities.filter { it.state == selectedState }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GigBackground)
    ) {
        // Header gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .background(Brush.verticalGradient(listOf(GigTeal, GigTealLight)))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawCircle(Color.White.copy(alpha = 0.07f), 200.dp.toPx(), Offset(size.width * 0.85f, 0f))
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.Bottom
            ) {
                Text("📍", fontSize = 36.sp)
                Spacer(Modifier.height(8.dp))
                Text(
                    "Where do you need\na service?",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "We'll find verified workers near you.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // GPS Button
            Button(
                onClick = { onNext(selectedCity.name, selectedState) },
                modifier = Modifier.fillMaxWidth().height(52.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GigSaffron),
                shape = RoundedCornerShape(14.dp)
            ) {
                Icon(Icons.Filled.MyLocation, null, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(10.dp))
                Text("Use Current Location", fontWeight = FontWeight.SemiBold)
            }

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                HorizontalDivider(modifier = Modifier.weight(1f), color = GigOutlineVariant)
                Text(" or select manually ", style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                HorizontalDivider(modifier = Modifier.weight(1f), color = GigOutlineVariant)
            }

            // State Dropdown
            Text("State", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
            ExposedDropdownMenuBox(expanded = stateExpanded, onExpandedChange = { stateExpanded = it }) {
                OutlinedTextField(
                    value = selectedState,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select State") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = stateExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = GigTeal)
                )
                ExposedDropdownMenu(expanded = stateExpanded, onDismissRequest = { stateExpanded = false }) {
                    states.forEach { state ->
                        DropdownMenuItem(
                            text = { Text(state) },
                            onClick = {
                                selectedState = state
                                selectedCity = filteredCities.firstOrNull() ?: cities.first()
                                stateExpanded = false
                            }
                        )
                    }
                }
            }

            // City Dropdown
            Text("City / District", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.SemiBold, color = GigOnSurface)
            ExposedDropdownMenuBox(expanded = cityExpanded, onExpandedChange = { cityExpanded = it }) {
                OutlinedTextField(
                    value = selectedCity.name,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select City") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = cityExpanded) },
                    modifier = Modifier.menuAnchor().fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = GigTeal)
                )
                ExposedDropdownMenu(expanded = cityExpanded, onDismissRequest = { cityExpanded = false }) {
                    filteredCities.forEach { city ->
                        DropdownMenuItem(
                            text = { Text("${city.name} — ${city.locality}") },
                            onClick = { selectedCity = city; cityExpanded = false }
                        )
                    }
                }
            }

            // Selected location card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = GigTealContainer)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(Icons.Filled.LocationOn, null, tint = GigTeal, modifier = Modifier.size(24.dp))
                    Column {
                        Text(selectedCity.name, fontWeight = FontWeight.Bold, color = GigTealDark)
                        Text("${selectedCity.locality}, ${selectedCity.state} — ${selectedCity.pincode}",
                            style = MaterialTheme.typography.bodySmall, color = GigSubtleText)
                        Text(selectedCity.primaryLanguages.joinToString(" • "),
                            style = MaterialTheme.typography.bodySmall, color = GigTeal)
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = { onNext(selectedCity.name, selectedState) },
                modifier = Modifier.fillMaxWidth().height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GigTeal),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Continue", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.width(8.dp))
                Icon(Icons.Filled.ArrowForward, null, modifier = Modifier.size(20.dp))
            }
        }
    }
}
