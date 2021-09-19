package com.home.taskmanager

import com.home.taskmanager.process.Process

interface ProcessManager {

    fun addProcess(process: Process)
    fun listRunningProcesses(): List<Process>
    fun killProcess(pid: String)
    fun killGroup(pid: String)
    fun killAll(pid: String)
}