package com.example.demo01.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenStarted
import com.example.demo01.R
import com.example.demo01.activity.Main3Activity
import com.example.demo01.activity.NavigationActivity
import com.example.demo01.viewmodel.FragmentShareDataViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ExampleFragment: Fragment(R.layout.example_fragment) {

    private val viewModel: FragmentShareDataViewModel by activityViewModels()

    companion object {
        const val TAG = "ExampleFragment"
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG,"onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"onViewCreated")
        view.findViewById<Button>(R.id.btn_open).setOnClickListener {
            //startActivity(Intent(this.requireContext(), NavigationActivity::class.java))
            lifecycleScope.launch {
                delay(4000)
                viewModel.testData.value = "hello world"
            }
        }

        viewModel.testData.observe(viewLifecycleOwner) {
            Log.d(TAG, "testData:$it")
        }
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
}