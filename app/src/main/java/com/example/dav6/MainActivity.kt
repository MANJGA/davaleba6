package com.example.dav6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var emailTextView : TextView
    private lateinit var uidTextView : TextView
    private lateinit var backToMainButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        init()
        backToMain()
    }
    private fun init(){
        emailTextView = findViewById(R.id.emailTextView)
        uidTextView = findViewById(R.id.uidTextView)
        backToMainButton = findViewById(R.id.backToMainButton)

        emailTextView.text = mAuth.currentUser?.email
        uidTextView.text = mAuth.currentUser?.uid

    }
    private fun backToMain(){
        backToMainButton.setOnClickListener {
            startActivity(Intent(this, RegActivity::class.java))
        }
    }



}