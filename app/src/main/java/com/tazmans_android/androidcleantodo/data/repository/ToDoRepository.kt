package com.tazmans_android.androidcleantodo.data.repository

import androidx.lifecycle.LiveData
import com.tazmans_android.androidcleantodo.data.ToDoDao
import com.tazmans_android.androidcleantodo.data.models.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {

    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData){
        toDoDao.insertData(toDoData)
    }

}