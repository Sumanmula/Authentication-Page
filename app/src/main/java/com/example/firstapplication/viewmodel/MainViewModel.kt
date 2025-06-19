package com.example.firstapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firstapplication.model.Person
import com.example.firstapplication.network.RetrofitInstance
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class MainViewModel : ViewModel() {

    private val _playerList = mutableStateOf<List<Person>?>(null)
    val playerList: State<List<Person>?> = _playerList

    val searchQuery = mutableStateOf("")
    val zoomedPerson = mutableStateOf<Person?>(null)

    init {
        fetchPlayersFromApi()
    }

    private fun fetchPlayersFromApi() {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.apiInterface.getPlayers()
                _playerList.value = response
            } catch (e: Exception) {
                e.printStackTrace()
                _playerList.value = emptyList()
            }
        }
    }

    fun searchList(): List<Person> {
        val list = _playerList.value ?: return emptyList()
        val query = searchQuery.value.lowercase()
        return if (query.isBlank()) list
        else list.filter {
            it.Name.lowercase().contains(query) || it.Bio.lowercase().contains(query)
        }
    }

    fun onImageClick(person: Person) {
        zoomedPerson.value = person
    }

    fun clearOverlay() {
        zoomedPerson.value = null
    }
}