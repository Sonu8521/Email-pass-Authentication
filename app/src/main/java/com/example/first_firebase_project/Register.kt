package com.example.first_firebase_project

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

class Register : AppCompatActivity() {

    private lateinit var remail: EditText
    private lateinit var rpass: EditText
    private lateinit var submitbtn: Button
    private lateinit var sEmail: String
    private lateinit var sPass: String
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        remail = findViewById(R.id.regemail)
        rpass = findViewById(R.id.regpass)
        submitbtn = findViewById(R.id.submitbtn)

        submitbtn.setOnClickListener {


            if (remail.text.isBlank() || rpass.text.isBlank()
            ) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            sEmail = remail.text.toString().trim()
            sPass = rpass.text.toString().trim()

            auth.createUserWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        Log.d("Tag", "createUserWithEmail:success")
                        val user = auth.currentUser
                        if (user != null) {
                            updateUI(user)

                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        }
                    } else {
                        Log.w(ContentValues.TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            this@Register,
                            "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {

        }
    }
}