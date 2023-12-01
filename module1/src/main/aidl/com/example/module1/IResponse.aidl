// IResponse.aidl
package com.example.module1;

// Declare any non-default types here with import statements

interface IResponse {
    void notifySuccess(String data);

    void notifyFail(int status);
}