package com.udacity.shoestore.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding



/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login, container, false)

        binding.buttonExistingLogin.setOnClickListener { navigateToWelcomeScreen() }
        binding.buttonNewLogin.setOnClickListener { navigateToWelcomeScreen() }

        return binding.root



    }

    private fun navigateToWelcomeScreen() {
        val action = LoginFragmentDirections.actionLoginFragmentToOnboardingWelcomeFragment()
        findNavController(this).navigate(action)
    }



}