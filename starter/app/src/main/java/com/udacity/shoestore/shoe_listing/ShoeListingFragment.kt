package com.udacity.shoestore.shoe_listing


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingBinding
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup


class ShoeListingFragment : Fragment() {

    lateinit var binding: FragmentShoeListingBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_shoe_listing,
                container,
                false
        )

        //navigate to detail fragment after fab click
        binding.floatingActionButton.setOnClickListener(
                Navigation.createNavigateOnClickListener(R.id.action_shoeListingFragment_to_shoeDetailFragment)
        )

        //viewmodel used to get shoe list data via observer
        val viewModel = ViewModelProvider(requireActivity()).get(ShoeListingViewModel::class.java)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { shoeList ->
            redraw(shoeList)
        })

        //create options menu only in this fragment
        setHasOptionsMenu(true)
        return binding.root
    }

    //redraw shoe layout following observed changes in shoe list livedata
    private fun redraw(shoeList: MutableList<Shoe>) {
        //remove old shoe items
        binding.shoeContainer.removeAllViewsInLayout()

        //create shoe item in layout for each shoe in list
        for (shoe in shoeList) {

            // Inflate the layout using LayoutInflater
            val view: View = layoutInflater.inflate(
                    R.layout.shoe_layout, // Custom view/ layout
                    binding.shoeContainer, // Root layout to attach the view
                    false // Attach with root layout or not
            )

            // Find the text view from shoe layout and set text
            val label = view.findViewById<TextView>(R.id.shoeNameText)
            label.text = shoe.name

            // Get the ImageView from shoe layout
            val imageView = view.findViewById<ImageView>(R.id.shoeImageView)

            // Set a shoe image to image view resource
            imageView.setImageResource(shoe.imageResource)

            // Finally, add the shoe item layout to the layout in the scrollview
            binding.shoeContainer.addView(view, 0)
        }

    }

    //handle navigation for overflow menu items (currently to login fragment)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu, menu)
    }

}
