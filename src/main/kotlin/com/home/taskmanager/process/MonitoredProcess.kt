package com.home.taskmanager.process

data class MonitoredProcess(val ts: Long, val process: Process) {
    companion object {
        fun create(process: Process): MonitoredProcess {
            return MonitoredProcess(System.currentTimeMillis(), process)
        }
    }
}