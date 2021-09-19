package com.home.taskmanager.process

import mu.KLogging
import java.util.concurrent.atomic.AtomicBoolean

class Process(val pid: String, val priority: Priority) {
    private val isAlive = AtomicBoolean(true)

    companion object : KLogging()

    fun isAlive() = isAlive.get()
    fun kill() {
        if (isAlive.compareAndSet(true, false)) {
            logger.info { "Object $pid with $priority priority killed" }
        } else {
            throw IllegalStateException("Process already has been killed")
        }
    }
}