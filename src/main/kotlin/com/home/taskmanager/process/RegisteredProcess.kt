package com.home.taskmanager.process

data class RegisteredProcess(val process: Process, val timestamp: Long) {
    companion object {
        fun createWithTimestamp(process: Process): RegisteredProcess {
            return RegisteredProcess(process, System.currentTimeMillis())
        }
    }
}