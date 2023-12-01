package com.example.demo01.content_provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import android.util.Log

class MyContentProvider : ContentProvider() {

    companion object {
        val CONTENT_URI: Uri = Uri.parse("content://com.example.myapp.provider/data")
    }

    override fun onCreate(): Boolean {
        // Initialization code if needed
        Log.d("MyContentProvider","MyContentProvider-onCreate")
        return true
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        // Handle insert operation
        return null
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {
        // Handle query operation
        val cursor = MatrixCursor(arrayOf("column_name"))
        cursor.addRow(arrayOf("Sample Data"))
        return cursor
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        // Handle update operation
        return 0
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        // Handle delete operation
        return 0
    }

    override fun getType(uri: Uri): String? {
        // Return the MIME type of the data
        return null
    }
}
