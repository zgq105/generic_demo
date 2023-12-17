package com.example.demo01.hashtable

import android.util.ArrayMap
import android.util.Log
import android.util.SparseArray

object HashTableHelper {

    fun test() {
        //存储key必须是int类型,适用于小数据量的场景
        val sparseArray = SparseArray<String>()
        sparseArray.put(1, "2")
        sparseArray.get(1)

        //get的效率高，基本是O(1)
        val hashMap = HashMap<String, String>()
        hashMap.put("qq", "111")
        hashMap.get("qq")

        //key和value挨着存储在一个数据mArray中，适用于小数据量的场景
        /**
         *  mHashes[index] = hash; //存储key的hashCode()
         *  mArray[index<<1] = key;//存储key
         *  mArray[(index<<1)+1] = value; //存储value
         */
        val arrayMap = ArrayMap<Person, String>()
        arrayMap.put(Person("张三"), "11")
        arrayMap.put(Person("李四"), "22")
        arrayMap.put(Person("zgq"), "33")
        arrayMap.get(Person("zgq")) //查找的效率并不高O(n)，内部二分查找
        val iterator = arrayMap.iterator()
        while (iterator.hasNext()) {
            val item = iterator.next()
            Log.d("zgq", "key:${item.key},value:${item.value}")
        }
    }


    class Person(var name: String) {

        override fun hashCode(): Int {
            if (name == "张三" || name == "李四") {
                return 1103
            }
            return super.hashCode()
        }
    }
}