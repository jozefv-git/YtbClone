package com.jozefv.ytbclone.presentation.subscriptions

sealed interface SubscriptionAction {
    data class Order(val ascending: Boolean) : SubscriptionAction
    data class SearchQuery(val query: String): SubscriptionAction
    data class OnItemClicked(val index: Int): SubscriptionAction
}