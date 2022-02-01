package ru.webanimal.test52_compose01.feature_contacts

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ContactsViewModel : ViewModel() {

    private val _contactsState = MutableStateFlow(ContactsState(listItemCount = LIST_ITEM_COUNT))
    internal val contactsState: StateFlow<ContactsState> = _contactsState

    fun onScrollTop() {
        _contactsState.update { ContactsState(scrollToPosition = LIST_FIRST_POSITION) }
    }

    fun onScrollBottom() {
        _contactsState.update { ContactsState(scrollToPosition = LIST_LAST_POSITION) }
    }

    companion object {
        private const val LIST_ITEM_COUNT = 100
        private const val LIST_FIRST_POSITION = 0
        private const val LIST_LAST_POSITION = LIST_ITEM_COUNT - 1
    }
}