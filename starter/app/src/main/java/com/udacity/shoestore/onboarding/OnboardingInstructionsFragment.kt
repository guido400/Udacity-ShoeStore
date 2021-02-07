package com.udacity.shoestore.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentOnboardingInstructionsBinding
import com.udacity.shoestore.databinding.FragmentOnboardingWelcomeBinding

class OnboardingInstructionsFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentOnboardingInstructionsBinding =
                DataBindingUtil.inflate(
                        inflater,
                        R.layout.fragment_onboarding_instructions,
                        container,
                        false
                )

        binding.button.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_onboardingInstructionsFragment_to_shoeListingFragment)
        )

        return binding.root
    }
}

