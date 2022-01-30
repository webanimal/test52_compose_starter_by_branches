package ru.webanimal.test52_compose01.feature_marketplace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class MarketplaceViewModel : ViewModel() {

    private val _marketplaceState = MutableStateFlow(MarketplaceState(listItemCount = LIST_ITEM_COUNT))
    internal val marketplaceState: StateFlow<MarketplaceState> = _marketplaceState

    fun onScrollTop() {
        _marketplaceState.update { MarketplaceState(scrollToPosition = LIST_FIRST_POSITION) }
    }

    fun onScrollBottom() {
        _marketplaceState.update { MarketplaceState(scrollToPosition = LIST_LAST_POSITION) }
    }

    companion object {
        private const val LIST_ITEM_COUNT = 100
        private const val LIST_FIRST_POSITION = 0
        private const val LIST_LAST_POSITION = LIST_ITEM_COUNT - 1
    }
}