package com.home.taskmanager.process
//package kotlin

import com.home.taskmanager.process.Priority

class Process(val pid: String, val priority: Priority) {
    fun kill(): Unit {
        throw IllegalStateException("not implemented yet")
    }
}