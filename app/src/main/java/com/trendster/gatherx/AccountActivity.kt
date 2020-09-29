package com.trendster.gatherx

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.CallbackManager
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.core.Context
import com.squareup.picasso.Picasso


class AccountActivity : AppCompatActivity() {

    lateinit var btnLogOut: Button
    lateinit var imgProfile: ImageView
    lateinit var txtName: TextView
    lateinit var txtID: TextView
    lateinit var txtEmail: TextView
    private lateinit var auth: FirebaseAuth
    lateinit var callBackManager: CallbackManager
    var user: FirebaseUser? = FirebaseAuth.getInstance().currentUser


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        txtName=findViewById(R.id.txtName)
        txtEmail=findViewById(R.id.txtEmail)
        txtID=findViewById(R.id.txtID)
        imgProfile=findViewById(R.id.imgProfile)
        btnLogOut=findViewById(R.id.btnLogOut)



///////////////////////////////GOOGLE///////////////////////////


        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            val personName = acct.displayName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl

            txtName.text = personName
            txtEmail.text = personEmail
            txtID.text = personId

            Picasso.get().load(personPhoto.toString()).into(imgProfile)

        }

        btnLogOut.setOnClickListener{

            setupUI()
        }


    }
    private fun setupUI() {
        btnLogOut.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        startActivity(GoogleActivity.getLaunchIntent(this))
        FirebaseAuth.getInstance().signOut()
        Toast.makeText(this@AccountActivity , "Sign Out Successful" , Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun getLaunchIntent(from: Context){

        }
    }




}