package com.example.demo01.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.demo01.R
import com.example.demo01.glide.GlideHelper

class FirstFragment : Fragment() {

    private lateinit var navController: NavController

    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false)

        navController = findNavController()

        rootView.findViewById<Button>(R.id.button).setOnClickListener {
            //navController.navigate(R.id.action_first_to_second)
            loadImage()
        }

        return rootView
    }

    private fun loadImage() {
        val imageView = rootView.findViewById<ImageView>(R.id.iv)
        GlideHelper.loadImage(this.requireContext(), imageView,
            "https://img95.699pic.com/photo/50136/1351.jpg_wh300.jpg?pa=123")
    }
}
