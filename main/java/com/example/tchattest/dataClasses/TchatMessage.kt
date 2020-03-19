package com.example.tchattest.dataClasses

data class TchatMessage(val message: String, val sender : User, val timeBeforeNextMessage: Long = 1000)