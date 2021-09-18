package com.home.taskmanager.process

import mu.KLogging

class Process(val pid: String, val priority: Priority) {

    companion object : KLogging()

    fun kill() {
        logger.info { "Object $pid with priority $priority killed" }
    }
}