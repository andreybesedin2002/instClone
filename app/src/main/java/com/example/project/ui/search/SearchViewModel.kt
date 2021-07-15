package com.example.project.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {
    fun searchDataChanged(peoples_name: String) {
        _search_field.value = peoples_name
    }

    private val _search_field = MutableLiveData<String>()
    val search_field: LiveData<String> = _search_field
}