package com.gigconnect.app.ui.onboarding

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gigconnect.app.data.model.UserRole
import com.gigconnect.app.ui.components.GigTopBar
import com.gigconnect.app.ui.theme.*
import com.gigconnect.app.viewmodel.AppViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthScreen(
    appViewModel: AppViewModel,
    initialIsRegister: Boolean = false,
    onAuthSuccess: (UserRole) -> Unit,
    onBack: () -> Unit
) {
    var isRegister by remember { mutableStateOf(initialIsRegister) }
    var phone by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var selectedRole by remember { mutableStateOf(UserRole.SEEKER) }
    var otpSent by remember { mutableStateOf(false) }
    var otpCode by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            GigTopBar(
                title = if (isRegister) "Create Account" else "Sign In",
                subtitle = "GigConnect Cooperative Network",
                onBack = onBack
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GigBackground)
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            // Mode Switcher Tab
            Surface(
                color = GigSurface,
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Row(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                    TabButton(
                        text = "Sign In",
                        selected = !isRegister,
                        onClick = {
                            isRegister = false
                            errorMessage = null
                            otpSent = false
                        },
                        modifier = Modifier.weight(1f)
                    )
                    TabButton(
                        text = "Register",
                        selected = isRegister,
                        onClick = {
                            isRegister = true
                            errorMessage = null
                            otpSent = false
                        },
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Role Selector Chip Row
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Select Your Role",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.Bold,
                    color = GigOnBackground
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RoleChip("Customer / Seeker", "👤", selectedRole == UserRole.SEEKER, Modifier.weight(1f)) {
                        selectedRole = UserRole.SEEKER
                    }
                    RoleChip("Skilled Worker", "👷", selectedRole == UserRole.WORKER, Modifier.weight(1f)) {
                        selectedRole = UserRole.WORKER
                    }
                    RoleChip("Admin", "🏛️", selectedRole == UserRole.ADMIN, Modifier.weight(0.8f)) {
                        selectedRole = UserRole.ADMIN
                    }
                }
            }

            // Registration: Name Field
            if (isRegister) {
                OutlinedTextField(
                    value = name,
                    onValueChange = {
                        name = it
                        errorMessage = null
                    },
                    textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                    label = { Text("Full Name") },
                    leadingIcon = { Icon(Icons.Filled.Person, null, tint = GigPrimaryBlue) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = GigOnBackground,
                        unfocusedTextColor = GigOnBackground,
                        focusedBorderColor = GigPrimaryBlue,
                        unfocusedBorderColor = GigOutlineVariant,
                        focusedContainerColor = GigSurface,
                        unfocusedContainerColor = GigSurface,
                        cursorColor = GigPrimaryBlue
                    )
                )
            }

            // Phone Number Input
            OutlinedTextField(
                value = phone,
                onValueChange = {
                    if (it.length <= 10 && it.all { char -> char.isDigit() }) {
                        phone = it
                        errorMessage = null
                    }
                },
                textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                label = { Text("Mobile Number") },
                prefix = { Text("+91 ", fontWeight = FontWeight.Bold, color = GigOnSurface) },
                leadingIcon = { Icon(Icons.Filled.Phone, null, tint = GigPrimaryBlue) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = GigOnBackground,
                    unfocusedTextColor = GigOnBackground,
                    focusedBorderColor = GigPrimaryBlue,
                    unfocusedBorderColor = GigOutlineVariant,
                    focusedContainerColor = GigSurface,
                    unfocusedContainerColor = GigSurface,
                    cursorColor = GigPrimaryBlue
                )
            )

            // OTP Section
            AnimatedVisibility(visible = otpSent) {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    OutlinedTextField(
                        value = otpCode,
                        onValueChange = {
                            if (it.length <= 6 && it.all { char -> char.isDigit() }) {
                                otpCode = it
                                errorMessage = null
                            }
                        },
                        textStyle = MaterialTheme.typography.bodyLarge.copy(color = GigOnBackground),
                        label = { Text("Enter 4-digit OTP") },
                        leadingIcon = { Icon(Icons.Filled.Lock, null, tint = GigPrimaryBlue) },
                        trailingIcon = {
                            TextButton(onClick = { otpCode = "1234" }) {
                                Text("Auto-fill", style = MaterialTheme.typography.labelSmall, color = GigPrimaryBlue)
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = GigOnBackground,
                            unfocusedTextColor = GigOnBackground,
                            focusedBorderColor = GigPrimaryBlue,
                            unfocusedBorderColor = GigOutlineVariant,
                            focusedContainerColor = GigSurface,
                            unfocusedContainerColor = GigSurface,
                            cursorColor = GigPrimaryBlue
                        )
                    )
                    Text(
                        "OTP sent to +91 $phone (Demo code: 1234)",
                        style = MaterialTheme.typography.bodySmall,
                        color = GigSubtleText
                    )
                }
            }

            // Error Message
            if (errorMessage != null) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = GigErrorContainer),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Filled.ErrorOutline, null, tint = GigError, modifier = Modifier.size(20.dp))
                        Text(errorMessage!!, style = MaterialTheme.typography.bodySmall, color = GigError, fontWeight = FontWeight.Medium)
                    }
                }
            }

            // Primary Action Button
            Button(
                onClick = {
                    if (phone.length < 10) {
                        errorMessage = "Please enter a valid 10-digit mobile number."
                        return@Button
                    }
                    if (isRegister && name.isBlank()) {
                        errorMessage = "Please enter your name."
                        return@Button
                    }
                    if (!otpSent) {
                        otpSent = true
                        otpCode = "1234"
                    } else {
                        if (otpCode.length < 4) {
                            errorMessage = "Please enter the 4-digit OTP."
                            return@Button
                        }
                        // Successful login/registration
                        isLoading = true
                        appViewModel.login(
                            phone = "+91 $phone",
                            name = if (isRegister) name else (if (selectedRole == UserRole.WORKER) "Ramesh Kumar" else "Demo User"),
                            role = selectedRole
                        )
                        onAuthSuccess(selectedRole)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(54.dp),
                colors = ButtonDefaults.buttonColors(containerColor = GigPrimaryBlue),
                shape = RoundedCornerShape(14.dp)
            ) {
                if (isLoading) {
                    CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp), strokeWidth = 2.5.dp)
                } else {
                    Text(
                        text = if (!otpSent) "Send OTP" else (if (isRegister) "Verify & Register" else "Verify & Sign In"),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            // Quick Demo Login Shortcuts
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = GigSurface),
                border = BorderStroke(1.dp, GigOutlineVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        "⚡ Quick Demo Login",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = GigOnSurface
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                appViewModel.login("+91 98765 43210", "Demo Seeker", UserRole.SEEKER)
                                onAuthSuccess(UserRole.SEEKER)
                            },
                            modifier = Modifier.weight(1f).height(44.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Customer", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                        OutlinedButton(
                            onClick = {
                                appViewModel.login("+91 87654 32109", "Ramesh Kumar", UserRole.WORKER)
                                onAuthSuccess(UserRole.WORKER)
                            },
                            modifier = Modifier.weight(1f).height(44.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Worker", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                        OutlinedButton(
                            onClick = {
                                appViewModel.login("+91 76543 21098", "Pune Admin", UserRole.ADMIN)
                                onAuthSuccess(UserRole.ADMIN)
                            },
                            modifier = Modifier.weight(1f).height(44.dp),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text("Admin", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun TabButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = if (selected) GigPrimaryBlue else Color.Transparent,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = if (selected) Color.White else GigSubtleText,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 10.dp)
        )
    }
}

@Composable
private fun RoleChip(
    label: String,
    icon: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        color = if (selected) GigTealContainer else GigSurface,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(if (selected) 1.5.dp else 1.dp, if (selected) GigPrimaryBlue else GigOutlineVariant),
        modifier = modifier.clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(icon, fontSize = 20.sp)
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                color = if (selected) GigPrimaryBlue else GigOnSurface,
                textAlign = TextAlign.Center
            )
        }
    }
}
