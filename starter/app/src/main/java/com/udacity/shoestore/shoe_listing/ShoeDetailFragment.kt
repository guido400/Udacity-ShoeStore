package com.udacity.shoestore.shoe_listing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
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

        //setup of spinner
        val adapter = context?.let {
            ArrayAdapter.createFromResource(
                    it,
                    R.array.shoeTypes,
                    android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        }

        binding.shoeSpinner.onItemSelectedListener = this
        binding.shoeSpinner.adapter = adapter

        //setvalue for default spinner item
        shoeType= getString(R.string.allstar)




        //listener for add show and return to listing
        binding.addShoeButton.setOnClickListener {
            val shoeName: String = binding.editTextShoeModel.text.toString()

            val shoeTypeId = when (this.shoeType) {
                getString(R.string.allstar) -> R.drawable.allstar
                getString(R.string.boot) -> R.drawable.boot
                getString(R.string.flipflop) -> R.drawable.flipflop
                getString(R.string.high_heel) -> R.drawable.highheel
                getString(R.string.leather_shoe) -> R.drawable.leathershoe
                else -> R.drawable.questionmark
            }

            val newShoe = Shoe(shoeName,shoeTypeId)
            viewModel.addShoe(newShoe)

            //navigate to listing fragment
            val action = R.id.action_shoeDetailFragment_to_shoeListingFragment
            findNavController(this).navigate(action)
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

}
