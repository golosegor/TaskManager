package com.home.taskmanager

import com.home.taskmanager.kickout.strategy.FifoStrategy
import com.home.taskmanager.kickout.strategy.FixedSizeStrategy
import com.home.taskmanager.kickout.strategy.PriorityStrategy
import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import mu.KLogging

interface ProcessManager {

    fun addProcess(process: Process)
    fun listRunningProcesses(): List<Process>
    fun killProcess(pid: String)
    fun killGroup(pids: List<String>)
    fun killAll()

    companion object : KLogging() {
        fun createFixedSize(limit: Int) = GenericProcessManager(limit, FixedSizeStrategy())
        fun createFifo(limit: Int) = GenericProcessManager(limit, FifoStrategy())
        fun createPriority(limit: Int) = GenericProcessManager(limit, PriorityStrategy())
    }
}

fun ProcessManager.killAllProcessesWithPriority(priority: Priority) {
    val pidsToKill = listRunningProcesses().filter { it.priority == priority }.map { it.pid }
    this.killGroup(pidsToKill)
}

fun ProcessManager.killGroup(vararg pids: String) {
    return this.killGroup(pids.toList())
}
