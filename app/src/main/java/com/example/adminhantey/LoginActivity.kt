package com.example.adminhantey

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.adminhantey.databinding.ActivityLoginBinding
import com.example.adminhantey.model.UserModel
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class LoginActivity : AppCompatActivity() {


    private lateinit var email:String
    private lateinit var password:String
    private lateinit var auth:FirebaseAuth
    private lateinit var database:DatabaseReference
    private lateinit var GoogleSignInClient: GoogleSignInClient


     private val binding:ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val googleSignInOptions=GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.project_id))

        auth=Firebase.auth

        database=Firebase.database.reference

        //initialize google sign in






        binding.login.setOnClickListener {
            //get text from edit text
            email=binding.email.text.toString().trim()
            password=binding.password.text.toString().trim()

            if(email.isBlank()|| password.isBlank()){
                Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }
            else
            {
                createUserAccount(email,password)
            }



            val intent=Intent(this,SignUpActivity ::class.java)
            startActivity(intent)
        }
        binding.donothaveanaccount.setOnClickListener {
            val intent=Intent(this,SignUpActivity ::class.java)
            startActivity(intent)
        }

    }
    private fun createUserAccount(email: String, password: String) {
auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task->
    if (task.isSuccessful){
        val user=auth.currentUser
        updateUi(user)
    } else{
         auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {  task->
             if (task.isSuccessful)
             {
                 val user=auth.currentUser
                 saveUserData()
                 updateUi(user)
             }
             else{
                 Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
                 Log.d("Account", "createUserAccount:Authentication Failed ",task.exception)
             }
         }
    }
}


    }

    private fun saveUserData() {
        //get text from edit text
        email=binding.email.text.toString().trim()
        password=binding.password.text.toString().trim()

        val user= UserModel(email,password)
        val  userId=FirebaseAuth.getInstance().currentUser?.uid
        userId.let {
            it?.let { it1 -> database.child("user").child(it1).setValue(user) }
        }

    }

    private fun updateUi(user: FirebaseUser?) {
        startActivity(Intent(this,MainActivity::class.java))

    }


}