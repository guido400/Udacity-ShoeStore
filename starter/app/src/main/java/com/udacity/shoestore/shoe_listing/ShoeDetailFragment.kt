package com.udacity.shoestore.shoe_listing

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import kotlinx.android.synthetic.main.fragment_shoe_detail.view.*

/**
 * A simple [Fragment] subclass.
 * Use the [ShoeDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShoeDetailFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var shoeType: String

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_detail,
                container,
                false
        )

        val viewModel = ViewModelProvider(requireActivity()).get(ShoeListingViewModel::class.java)
        binding.shoeViewModel =viewModel

        //cancel and return to listing
        binding.cancelButton.setOnClickListener {
            navigateToListing ()
        }

        //add shoe and return to listing
        binding.addShoeButton.setOnClickListener {
            if (TextUtils.isEmpty(binding.editTextShoeName.text)  ||
                TextUtils.isEmpty(binding.editTextSize.text)||
                TextUtils.isEmpty(binding.editTextCompany.text) ||
                TextUtils.isEmpty(binding.editTextDescription.text) )
            {
                Toast.makeText(requireActivity(), "All fields must be filled before adding shoe", Toast.LENGTH_LONG).show()
            } else {
                viewModel.addShoe()
                navigateToListing ()
            }
        }

        return binding.root
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            shoeType = parent.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //empty method
    }

    private fun navigateToListing () {
        val action = R.id.action_shoeDetailFragment_to_shoeListingFragment
        findNavController(this).navigate(action)
    }

}
