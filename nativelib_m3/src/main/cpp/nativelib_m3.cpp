#include <jni.h>
#include <string>
#include <android/log.h>
#include <iostream>
#include <vector>
#include <list>
#include <map>

using namespace std;

#define LENGTH 10; //定义常量
#define MIN(a, b) (a<b ? a : b) //参数宏
const int WIDTH = 5;

//条件编译
#ifdef NULL
#define NULL 0
#endif

// 函数声明
void testPointer();

void foo(int *ptr);

void testMemoryManagement();

void test();

void dynamicMemoryUsage();

char *const TAG = "JNI";

struct Point {
    int x;
    int y;
};

enum Color {
    red, green, blue
};

//抽象类：存在至少一个纯虚函数的类
class ABSClass {
public:
    // 纯虚函数
    virtual double getVolume() = 0;
};

class MyClass {
public:
    static int callCount;//静态变量
    int publicVar; //可以在类的外部访问
    // 成员函数（方法）
    void f1() {
        cout << "Value of i : " << this->protectedVar << this->p2 << endl;;
    }

private:
    int privateVar;//只能在类的内部访问
    double p2;
protected:
    int protectedVar;//在类的内部和派生类中可访问

};

class MyClassChild : MyClass {
public:
    void f1() {
        this->protectedVar; //访问父类的变量
    }
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

    list<int> list = {1, 2};
    list.push_back(1);
    list.pop_back();
    std::list<int>::iterator it = list.begin();
    while (it != list.end()) {
        //遍历迭代器
    }

    //说明vector是动态数组，采用数组的数据结构实现；list是采用链表数据结构实现

    map<string, int> m = {};
    m["key"] = 10;
    m.find("key");
}

void test() {
    Color myColor = red;
    sin(3.24);
    cos(2.0);
    int *ptr = nullptr;
    int *ptr2 = NULL;
    // 声明简单的变量
    int i;
    // 声明引用变量
    int &r = i; //i和r指向同一个内存地址
    i = 5;
    cout << "Value of i : " << i << endl;
    cout << "Value of i reference : " << r << endl;

    // 基于当前系统的当前日期/时间
    time_t now = time(0);
    // 把 now 转换为字符串形式
    char *dt = ctime(&now);
    cout << "本地日期和时间：" << dt << endl;


    try {
        MyClass myClass;
        myClass.f1();
    } catch (exception e) {

    }
}

/**
 * c++动态内存使用
 */
void dynamicMemoryUsage() {

    //1.基础类型动态内存使用：
    double *pValue  = nullptr; // 初始化为 null 的指针
    pValue  = new double;   // 为变量请求内存

    *pValue = 29494.99;     // 在分配的地址存储值
    cout << "Value of pValue : " << *pValue << endl;

    delete pValue;         // 释放内存

    //2.数组类型
    // 动态分配,数组长度为 20
    int *array = new int[20];
    //释放内存
    delete[] array;

    //3.对象的动态内存分配
    MyClass *myBoxArray = new MyClass[4];

    delete[] myBoxArray; // 删除数组


}




