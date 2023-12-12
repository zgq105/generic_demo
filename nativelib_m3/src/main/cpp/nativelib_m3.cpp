#include <jni.h>
#include <string>
#include <android/log.h>
#include <iostream>
#include <vector>
#include <list>
using namespace std;

void testPointer();
void foo(int *ptr);
void testMemoryManagement();

char *const TAG = "JNI";

struct Point {
    int x;
    int y;
};

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_1m3_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_1m3_NativeLib_getParamsFromJava(JNIEnv *env, jobject instance,
                                                           jint intValue) {
    // Convert jint to string
    char buffer[20];  // Assuming a maximum of 20 characters for the string representation
    snprintf(buffer, sizeof(buffer), "%d", intValue);
    // Create a new Java string from the C string
    return env->NewStringUTF(buffer);
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_nativelib_1m3_NativeLib_getParamsFromClassObj(JNIEnv *env, jobject instance,
                                                               jobject paramsObj) {
    jclass cls = env->GetObjectClass(paramsObj);
    jmethodID methodID = env->GetMethodID(cls, "test", "(Ljava/lang/String;)Ljava/lang/String;");
    const char *input = "Hello from JNI!";
    jstring res = static_cast<jstring>(env->CallObjectMethod(paramsObj, methodID,
                                                             env->NewStringUTF(input)));
    testPointer();
    return res;
}

//指针
void testPointer() {
    int x = 10;
    int *i;        // 声明一个整型指针
    i = &x;        // 将ptr指向变量x的地址
    const string iRes = "testPointer-i:" + std::to_string(*i);
    __android_log_write(ANDROID_LOG_INFO, TAG, iRes.c_str());

    int y = *i;    // 通过解引用指针获取存储在指针所指地址的值
    const string yRes = "testPointer-y:" + std::to_string(y);
    __android_log_write(ANDROID_LOG_INFO, TAG, yRes.c_str());

    int *nullTest = nullptr;  // C++11起推荐使用nullptr代替NULL
    __android_log_write(ANDROID_LOG_INFO, TAG, "testPointer-nullptr:");

    int arr[5] = {1, 2, 3, 4, 5};
    int *p = arr;       // 指向数组的第一个元素的指针
//    p++;                // 移动到数组的下一个元素
    const string pRes = "testPointer-p:" + std::to_string(*p);
    __android_log_write(ANDROID_LOG_INFO, TAG, pRes.c_str());

    int arr2[5] = {1, 2, 3, 4, 5};
    int *p2 = arr2;
    //输出数组的第三个元素，等同于 *(p2 + 2)
    const string p2Res = "testPointer-p2:" + std::to_string(p2[2]);
    __android_log_write(ANDROID_LOG_INFO, TAG, p2Res.c_str());
    const string p22Res = "testPointer-p22:" + std::to_string(*(p2 + 2));
    __android_log_write(ANDROID_LOG_INFO, TAG, p22Res.c_str());

    int params = 10;
    foo(&params);   //&params表示获取变量params的内存地址

    int *ptr = new int;    // 动态分配整型变量的内存
    *ptr = 10;
    __android_log_write(ANDROID_LOG_INFO, TAG, to_string(*ptr).c_str());
    delete ptr;            // 释放动态分配的内存

    const char *str = "Hello";  // 指向字符串的指针
    __android_log_write(ANDROID_LOG_INFO, TAG, str);

    Point point;
    Point *pPoint = &point;    // 指向结构体的指针
    pPoint->x = 10;
    pPoint->y = 12;

    string pointStr = "point:" + to_string(pPoint->x) + to_string(pPoint->y);
    __android_log_write(ANDROID_LOG_INFO, TAG, pointStr.c_str());

}

void foo(int *ptr) {
    // 函数参数为指针
    const string res = "testPointer-foo:" + std::to_string(*ptr);
    __android_log_write(ANDROID_LOG_INFO, TAG, res.c_str());
}


void testMemoryManagement() {
    // 使用 std::unique_ptr 动态分配内存,不需要手动释放内存，当 unique_ptr 超出范围时会自动释放
    std::unique_ptr<int> dynamicInt = std::make_unique<int>(42);
    dynamicInt.get();

    // 使用 std::shared_ptr 动态分配内存,不需要手动释放内存，当最后一个 shared_ptr 超出范围时会自动释放
    std::shared_ptr<int> dynamicInt2 = std::make_shared<int>(42);
    dynamicInt2.get();


    //使用标准库容器：在C++11及以后的版本中，标准库容器（如std::vector、std::list等）会自动管理其元素的内存，无需手动释放。
    vector<int> numbers = {1, 2, 3, 4, 5};
    numbers.insert(numbers.begin() + 2, 10);
    numbers.push_back(9);

    list<int> list = {1,2};
    list.push_back(1);
    list.pop_back();
    std::list<int>::iterator it = list.begin();
    while (it != list.end()){
        //遍历迭代器
    }
}


