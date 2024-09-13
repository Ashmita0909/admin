package com.example.adminhantey

import android.accounts.Account
import android.content.Intent
import android.os.Bundle

import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.adminhantey.databinding.ActivitySignUpBinding
import com.example.adminhantey.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class SignUpActivity : AppCompatActivity() {


    private lateinit var email: String
    private lateinit var password: String
    private lateinit var userName: String
    private lateinit var nameOfRestaurant: String
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    private val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        //initialize Firebase Auth
        auth = Firebase.auth

        // initialize Firebase database
        database = Firebase.database.reference

        binding.createaccount.setOnClickListener {
            //get text from edittext
            userName = binding.user.text.toString().trim()
            nameOfRestaurant = binding.restaurant.text.toString().trim()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()

            if ( userName.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank())
            {
                Toast.makeText(this, "Please fill all details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                createAccount(email,password)
            }



        }
        binding.alreadyhaveaccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }
    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { task->
            if(task.isSuccessful){
                Toast.makeText(this, "Account Created Sucessfully.", Toast.LENGTH_SHORT).show()
                saveUserData()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                Toast.makeText(this, "Account creation failed.", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure",task.exception)
            }
        }

    }
// save data into data base
    private fun saveUserData() {

        userName = binding.user.text.toString().trim()
        nameOfRestaurant = binding.restaurant.text.toString().trim()
        email = binding.email.text.toString().trim()
        password = binding.password.text.toString().trim()
        val user=UserModel(userName,nameOfRestaurant, email,password)
        val userId=FirebaseAuth.getInstance().currentUser!!.uid
    // save user data Firebase database
        database.child("user").child(userId).setValue(user)

    }

}