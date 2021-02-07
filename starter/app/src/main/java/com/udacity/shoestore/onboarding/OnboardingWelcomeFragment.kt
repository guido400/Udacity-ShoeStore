package com.udacity.shoestore.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding
import com.udacity.shoestore.databinding.FragmentOnboardingWelcomeBinding


class OnboardingWelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOnboardingWelcomeBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_onboarding_welcome,
                container,
                false
            )

        binding.button.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_onboardingWelcomeFragment_to_onboardingInstructionsFragment)
        )

        return binding.root
    }
}

