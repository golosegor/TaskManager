package com.home.taskmanager

import com.home.taskmanager.process.Process

interface ProcessManager {
    companion object {
        const val MAX_CAPACITY = 10
    }

    fun addProcess(process: Process)
    fun listRunningProcesses(): List<Process>
    fun killProcess(pid: String)
    fun killGroup(pid: String)
    fun killAll(pid: String)
}