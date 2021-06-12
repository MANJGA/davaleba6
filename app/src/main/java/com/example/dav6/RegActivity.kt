package com.example.dav6

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class RegActivity : AppCompatActivity() {

    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var submitButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reg)
        mAuth = FirebaseAuth.getInstance()
        init()
        registration()

    }

    private fun init(){

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        repeatPasswordEditText = findViewById(R.id.reeditTextTextPassword)
        submitButton = findViewById(R.id.SubmitButton)

    }
    private fun registration(){

        submitButton.setOnClickListener{

            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val repPassword = repeatPasswordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(this, "ცარიელია!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (isValidEmail(email) == false){

                Toast.makeText(this, "შემოიტანეთ ნამდვილი E-mail-ი!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if(password.length < 9){

                Toast.makeText(this, "პაროლი უნდა შეიცავდეს მინიმუმ 9 სიმბოლოს!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if (passwordContsDigit(password) == false){

                Toast.makeText(this, "პაროლი უნდა შეიცავდეს როგორც სიმბოლოებს აგრეთვე ციფრებს!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            if(password != repPassword){

                Toast.makeText(this, "პაროლები ერთმანეთს არ ემთხვევა!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener

            }
            else {
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { result ->
                        if (result.isSuccessful) {

                            startActivity(Intent(this, MainActivity::class.java))
                            finish()

                        } else {

                            Toast.makeText(this, "ავტორიზაცია ვერ მოხერხდა :/", Toast.LENGTH_SHORT)
                                .show()

                        }
                    }
            }


        }
    }

    private fun passwordContsDigit(pass:String): Boolean {
        var dd = 0
        var ll = 0
        for (i in pass){
            if(i.isDigit()){
                dd += 1
            }
            if(i.isLetter()){
                ll += 1
            }
        }
        return dd >= 1 && ll >=1
    }
    private fun goToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    private fun isValidEmail(target: CharSequence): Boolean {
        return if (TextUtils.isEmpty(target)) {
            false
        } else {
            Patterns.EMAIL_ADDRESS.matcher(target).matches()
        }
    }

}