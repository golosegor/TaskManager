package com.home.taskmanager

import com.home.taskmanager.process.Process
import mu.KLogging
import java.util.concurrent.ConcurrentHashMap

class ProcessManager {
    companion object : KLogging() {
        const val MAX_CAPACITY = 10
    }

    private val processes: MutableMap<String, Process> = ConcurrentHashMap<String, Process>()

    fun addProcess(process: Process) {
        if (processes.size < MAX_CAPACITY) {
            processes[process.pid] = process
        } else {
            throw IllegalStateException("Process limit reached")
        }
    }

    fun listRunningProcesses(): List<Process> {
        throw IllegalStateException("")
    }

    fun killProcess(pid: String) {
        throw IllegalStateException("")
    }


    fun killGroup(pid: String) {
        throw IllegalStateException("")
    }

    fun killAll(pid: String) {
        throw IllegalStateException("")
    }
}
