package com.example.demo01.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch

class FragmentShareDataViewModel : ViewModel() {

    val testData by lazy { MutableLiveData("") }

    fun test() {
        val job = viewModelScope.launch {
            with(Dispatchers.IO) {
                //耗时任务
            }
        }

        //job.cancel()
    }

    //并发任务
    fun test2() {
        viewModelScope.launch {
            val task1 = async { work1() }
            val task2 = async { work2() }
            task1.await()
            task2.await()

            val taskList = listOf(async { work1() }, async { work2() })
            taskList.awaitAll()


        }
    }

    private fun work1() {
        Person("ZQG").apply {
            this.name = ""
        }.name

        Person("ZQG").run {
            this.name = "hhhh"
            "success"
        }.toString()

        Person("ZQG").also {
            it.name = "hehe"
        }.name

        Person("ZQG").let {
            it.name = "hhh"
            2.0
        }.toInt()



    }

    private fun work2() {

    }

    data class Person(var name: String)
}