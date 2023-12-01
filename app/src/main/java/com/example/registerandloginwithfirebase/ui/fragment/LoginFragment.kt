package com.example.registerandloginwithfirebase.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.registerandloginwithfirebase.R
import com.example.registerandloginwithfirebase.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater,container,false)

        firebaseAuth= FirebaseAuth.getInstance()

        binding.buttonSignIn.setOnClickListener {

            val email = binding.lEmail.text.toString()
            val password = binding.lPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener {
                    if(it.isSuccessful){
                        val intent = Intent(requireContext(),HomeFragment::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(requireContext(),it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(requireContext(),"Fields can not be empty ",Toast.LENGTH_SHORT).show()
            }
        }

        binding.textSignUp.setOnClickListener {
            val signUpIntent = Intent(requireContext(),RegisterFragment::class.java)
            startActivity(signUpIntent)
        }

        binding.textForgot.setOnClickListener {
            val forgotIntent = Intent(requireContext(),ForgotPasswordFragment::class.java)
            startActivity(forgotIntent)
        }

        return binding.root
    }


}