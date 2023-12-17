package com.example.demo01.data_structures_algorithms

import java.util.Collections


class AlgorithmsHelper {
    companion object {
        /**
         * 选择排序:时间复杂度：O(n²)，空间复杂度：O(1)，稳定性：非稳定排序
         *
         */
        fun selectionSort(nums: IntArray) {
            val n = nums.size
            // 外循环：未排序区间为 [i, n-1]
            for (i in 0 until n - 1) {
                // 内循环：找到未排序区间内的最小元素
                var min = i
                for (j in i + 1 until n) {
                    if (nums[j] < nums[min]) min = j // 记录最小元素的索引
                }
                // 将该最小元素与未排序区间的首个元素交换
                val temp = nums[i]
                nums[i] = nums[min]
                nums[min] = temp
            }
        }

        /*
        * 冒泡排序：时间复杂度：O(n²)，空间复杂度：O(1)，稳定性：稳定排序
        *  */
        fun bubbleSort(nums: IntArray) {
            // 外循环：未排序区间为 [0, i]
            for (i in nums.size - 1 downTo 1) {
                // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
                for (j in 0 until i) {
                    if (nums[j] > nums[j + 1]) {
                        // 交换 nums[j] 与 nums[j + 1]
                        val tmp = nums[j]
                        nums[j] = nums[j + 1]
                        nums[j + 1] = tmp
                    }
                }
            }
        }

        /* 冒泡排序（标志优化） */
        fun bubbleSortWithFlag(nums: IntArray) {
            // 外循环：未排序区间为 [0, i]
            for (i in nums.size - 1 downTo 1) {
                var flag = false // 初始化标志位
                // 内循环：将未排序区间 [0, i] 中的最大元素交换至该区间的最右端
                for (j in 0 until i) {
                    if (nums[j] > nums[j + 1]) {
                        // 交换 nums[j] 与 nums[j + 1]
                        val tmp = nums[j]
                        nums[j] = nums[j + 1]
                        nums[j + 1] = tmp
                        flag = true // 记录交换元素
                    }
                }
                if (!flag) break // 此轮冒泡未交换任何元素，直接跳出
            }
        }

        /*
        * 插入排序：时间复杂度：O(n²)，空间复杂度：O(1)，稳定性：稳定排序
        * */
        fun insertionSort(nums: IntArray) {
            // 外循环：已排序元素数量为 1, 2, ..., n
            for (i in 1 until nums.size) {
                val base = nums[i]
                var j = i - 1
                // 内循环：将 base 插入到已排序部分的正确位置
                while (j >= 0 && nums[j] > base) {
                    nums[j + 1] = nums[j] // 将 nums[j] 向右移动一位
                    j--
                }
                nums[j + 1] = base // 将 base 赋值到正确位置
            }
        }


        /*
        快速排序（分治法思想）：时间复杂度：O(nlogn)，空间复杂度：O(n)，稳定性：非稳定排序
        *  */
        fun quickSort(nums: IntArray, left: Int, right: Int) {
            // 子数组长度为 1 时终止递归
            if (left >= right) return
            // 哨兵划分
            val pivot: Int = partition(nums, left, right)
            // 递归左子数组、右子数组
            quickSort(nums, left, pivot - 1)
            quickSort(nums, pivot + 1, right)
        }

        /* 元素交换 */
        fun swap(nums: IntArray, i: Int, j: Int) {
            val tmp = nums[i]
            nums[i] = nums[j]
            nums[j] = tmp
        }

        /* 哨兵划分 */
        fun partition(nums: IntArray, left: Int, right: Int): Int {
            // 以 nums[left] 为基准数
            var i = left
            var j = right
            while (i < j) {
                while (i < j && nums[j] >= nums[left]) j-- // 从右向左找首个小于基准数的元素
                while (i < j && nums[i] <= nums[left]) i++ // 从左向右找首个大于基准数的元素
                swap(nums, i, j) // 交换这两个元素
            }
            swap(nums, i, left) // 将基准数交换至两子数组的分界线
            return i // 返回基准数的索引
        }


        /* 合并左子数组和右子数组 */
       private fun merge(nums: IntArray, left: Int, mid: Int, right: Int) {
            // 左子数组区间 [left, mid], 右子数组区间 [mid+1, right]
            // 创建一个临时数组 tmp ，用于存放合并后的结果
            val tmp = IntArray(right - left + 1)
            // 初始化左子数组和右子数组的起始索引
            var i = left
            var j = mid + 1
            var k = 0
            // 当左右子数组都还有元素时，比较并将较小的元素复制到临时数组中
            while (i <= mid && j <= right) {
                if (nums[i] <= nums[j]) tmp[k++] = nums[i++] else tmp[k++] = nums[j++]
            }
            // 将左子数组和右子数组的剩余元素复制到临时数组中
            while (i <= mid) {
                tmp[k++] = nums[i++]
            }
            while (j <= right) {
                tmp[k++] = nums[j++]
            }
            // 将临时数组 tmp 中的元素复制回原数组 nums 的对应区间
            k = 0
            while (k < tmp.size) {
                nums[left + k] = tmp[k]
                k++
            }
        }

        /*
        归并排序（分治法：先拆分再合并）时间复杂度：O(nlogn)，空间复杂度：O(n)，稳定性：稳定排序
        *  */
        fun mergeSort(nums: IntArray, left: Int, right: Int) {
            // 终止条件
            if (left >= right) return  // 当子数组长度为 1 时终止递归
            // 划分阶段
            val mid = (left + right) / 2 // 计算中点
            mergeSort(nums, left, mid) // 递归左子数组
            mergeSort(nums, mid + 1, right) // 递归右子数组
            // 合并阶段
            merge(nums, left, mid, right)
        }


        /* 堆的长度为 n ，从节点 i 开始，从顶至底堆化 */
       private fun siftDown(nums: IntArray, n: Int, i: Int) {
            var i = i
            while (true) {
                // 判断节点 i, l, r 中值最大的节点，记为 ma
                val l = 2 * i + 1
                val r = 2 * i + 2
                var ma = i
                if (l < n && nums[l] > nums[ma]) ma = l
                if (r < n && nums[r] > nums[ma]) ma = r
                // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
                if (ma == i) break
                // 交换两节点
                val temp = nums[i]
                nums[i] = nums[ma]
                nums[ma] = temp
                // 循环向下堆化
                i = ma
            }
        }

        /*
         * 堆排序：时间复杂度：O(nlogn)，空间复杂度：O(1)，稳定性：非稳定排序
        *  */
        fun heapSort(nums: IntArray) {
            // 建堆操作：堆化除叶节点以外的其他所有节点
            for (i in nums.size / 2 - 1 downTo 0) {
                siftDown(nums, nums.size, i)
            }
            // 从堆中提取最大元素，循环 n-1 轮
            for (i in nums.size - 1 downTo 1) {
                // 交换根节点与最右叶节点（交换首元素与尾元素）
                val tmp = nums[0]
                nums[0] = nums[i]
                nums[i] = tmp
                // 以根节点为起点，从顶至底进行堆化
                siftDown(nums, i, 0)
            }
        }

        /*
        桶排序（分治法思想）：时间复杂度：O(n+k)，空间复杂度：O(n+k)，稳定性：取决于桶内选择的排序算法
        *  */
        fun bucketSort(nums: FloatArray) {
            // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
            val k = nums.size / 2
            val buckets: MutableList<MutableList<Float>> = ArrayList()
            for (i in 0 until k) {
                buckets.add(ArrayList())
            }
            // 1. 将数组元素分配到各个桶中
            for (num in nums) {
                // 输入数据范围为 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
                val i = (num * k).toInt()
                // 将 num 添加进桶 i
                buckets[i].add(num)
            }
            // 2. 对各个桶执行排序
            for (bucket in buckets) {
                // 使用内置排序函数，也可以替换成其他排序算法
                bucket.sort()
            }
            // 3. 遍历桶合并结果
            var i = 0
            for (bucket in buckets) {
                for (num in bucket) {
                    nums[i++] = num
                }
            }
        }


    }
}