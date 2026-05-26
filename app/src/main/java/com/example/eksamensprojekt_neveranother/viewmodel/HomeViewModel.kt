package com.example.eksamensprojekt_neveranother.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

// ===== HOME VIEW MODEL =====
// Holder styr på logikken for forsiden.
class HomeViewModel : ViewModel() {
    
    // Email state til nyhedsbrev
    var email by mutableStateOf("")

    fun onEmailChange(newEmail: String) {
        email = newEmail
    }

    fun onSendEmail() {
        // Logik til tilmelding af nyhedsbrev
        println("Email sendt: $email")
        email = "" // Ryd feltet efter afsendelse
    }

    // ===== BUSINESS LOGIC FOR HERO SECTION =====
    
    // Bestemmer teksten på hovedknappen baseret på om brugeren er målt op.
    fun getHeroBtnText(isTailored: Boolean): String {
        return if (isTailored) "Se din BH" else "Skræddersy BH"
    }

    // Bestemmer hvor knappen skal navigere hen.
    fun getHeroBtnRoute(isTailored: Boolean): String {
        return if (isTailored) "product" else "tailor_start"
    }
}
