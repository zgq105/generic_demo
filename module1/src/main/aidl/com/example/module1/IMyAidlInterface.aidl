// IMyAidlInterface.aidl
package com.example.module1;

import com.example.module1.Person;
import com.example.module1.IResponse;

interface IMyAidlInterface {
    void setData(int a);
    int getData();
    void registerCallBack(IResponse response);
    void addPerson(inout Person p);
}