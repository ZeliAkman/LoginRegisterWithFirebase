package com.example.registerandloginwithfirebase.ui.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.registerandloginwithfirebase.R
import com.example.registerandloginwithfirebase.databinding.FragmentRegisterBinding
import com.google.firebase.auth.FirebaseAuth


class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.buttonSignUp.setOnClickListener {
            val username = binding.editUsername.text.toString()
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val rePassword = binding.editRepassword.text.toString()


            if(username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && rePassword.isNotEmpty() ){
                if (password == rePassword ){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(requireContext(),LoginFragment::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(requireContext(),it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(requireContext(),"Password does not matched",Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(requireContext(),"Fields can not be empty ",Toast.LENGTH_SHORT).show()

            }
        }

        binding.textSignUp.setOnClickListener {
            val signUpIntent = Intent(requireContext(),LoginFragment::class.java)
            startActivity(signUpIntent)
        }


        return binding.root
    }


}