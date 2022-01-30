package ru.webanimal.test52_compose01.feature_marketplace

data class MarketplaceState(
    val avatarUrl: String = "https://developer.android.com/images/brand/Android_Robot.png",
    val listItemCount: Int = 100,
    val scrollToPosition: Int = 0
)