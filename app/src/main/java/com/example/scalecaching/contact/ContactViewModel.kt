package com.example.scalecaching.contact

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.scalecaching.common.BaseDao
import com.example.scalecaching.common.Util.Companion.randomWord
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class ContactViewModel(private val todoDao: BaseDao<ContactItem>) : ViewModel() {
    private var contactList = mutableStateListOf<ContactItem>()
    private val _contactListFlow = MutableStateFlow(contactList)

    val contactListFlow: StateFlow<List<ContactItem>> get() = _contactListFlow
    private var postExecute: (() -> Unit)? = null

    init {
        loadContactList()
    }

    private fun loadContactList() {
        viewModelScope.launch {
            todoDao.getAll().collect {
                contactList = it.toMutableStateList()
                _contactListFlow.value = contactList
                postExecute?.invoke()
            }
        }
    }

    fun setFriend(index: Int, value: Boolean) {
        val editedTodo = contactList[index].copy(friend = value)
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.update(editedTodo)
            postExecute = null
        }
    }

    fun generateRandomContact(postGenerate: (() -> Unit)? = null) {
        val numberOfTodo = (10..20).random()
        val mutableTodoList = mutableStateListOf<ContactItem>()
        (0..numberOfTodo).forEach {
            val todoItem = ContactItem(it, "Person $it: ${randomWord()}", Random.nextBoolean())
            mutableTodoList.add(todoItem)
        }

        viewModelScope.launch(Dispatchers.IO) {
            todoDao.nukeTable()
            todoDao.insertAll(*mutableTodoList.toList().toTypedArray())
            postExecute = postGenerate
        }
    }

    fun addRecord(nameText: String, friend: Boolean, postInsert: (() -> Unit)? = null) {
        val id = contactList.lastOrNull()?.id ?: -1
        val contactItem = ContactItem(id + 1, nameText, friend)
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.insertAll(contactItem)
            postExecute = postInsert
        }
    }

    fun removeRecord(contactItem: ContactItem, postRemove: (() -> Unit)? = null) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.delete(contactItem)
            postExecute = postRemove
        }
    }
}
