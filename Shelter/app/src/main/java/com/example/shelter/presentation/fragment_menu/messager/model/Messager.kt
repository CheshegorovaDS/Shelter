package com.example.shelter.presentation.fragment_menu.messager.model

data class Messager (var fullName: String,
                     var messageText: String,
                     var messageTime: String,
                     var unreadMessage: Int = 0)