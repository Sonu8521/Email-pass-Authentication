package com.example.first_firebase_project

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var pass: EditText
    private lateinit var signbtn: Button
    private lateinit var loginbtn: Button

    private lateinit var sEmail: String
    private lateinit var sPass: String
    private lateinit var auth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

        email = findViewById(R.id.logemail)
        pass = findViewById(R.id.logpass)
        signbtn = findViewById(R.id.signbtn)
        loginbtn = findViewById(R.id.loginbtn) // Assuming you have a login button in your layout

        signbtn.setOnClickListener {
            val intent = Intent(this, Register::class.java)
            startActivity(intent)
        }

        loginbtn.setOnClickListener {
            var intent = Intent(this,dashboard::class.java)
            startActivity(intent)

            sEmail = email.text.toString().trim()
            sPass = pass.text.toString().trim()

            auth.createUserWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Log.d("Tag", "createUserWithEmail:success")
                        val user = auth.currentUser
                        if (user != null) {
                            updateUI(user)
                        }
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(this@MainActivity, "Authentication Success", Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
   }

    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

        }
    }
}