package com.example.demo01.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import java.util.UUID

class FirstFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var rootView: View

    companion object {
        const val TAG = "FirstFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG,"onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_first, container, false)

        navController = findNavController()

        rootView.findViewById<Button>(R.id.button).setOnClickListener {
            navController.navigate(R.id.action_first_to_second)
            //loadImage()
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d(TAG,"onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG,"onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG,"onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG,"onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG,"onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG,"onDetach")
    }

    private fun loadImage() {
        val imageView = rootView.findViewById<ImageView>(R.id.iv)
        GlideHelper.loadImage(
            this.requireContext(), imageView,
            "https://img95.699pic.com/photo/50136/1351.jpg_wh300.jpg?ra=" + UUID.randomUUID()
                .toString()
        )
    }
}
