package com.home.taskmanager

import com.home.taskmanager.kickout.strategy.FifoStrategy
import com.home.taskmanager.kickout.strategy.FixedSizeStrategy
import com.home.taskmanager.kickout.strategy.PriorityStrategy
import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process

interface ProcessManager {

    fun addProcess(process: Process)
    fun listProcesses(): List<Process>
    fun killProcess(pid: String)
    fun killGroup(pids: List<String>)
    fun killAll()

    companion object {
        fun createFixedSize(limit: Int) = GenericProcessManager(limit, FixedSizeStrategy())
        fun createFifo(limit: Int) = GenericProcessManager(limit, FifoStrategy())
        fun createPriority(limit: Int) = GenericProcessManager(limit, PriorityStrategy())
    }
}

fun ProcessManager.killAllProcessesWithPriority(priority: Priority) {
    val pidsToKill = listProcesses().filter { it.priority == priority }.map { it.pid }
    this.killGroup(pidsToKill)
}

fun ProcessManager.killGroup(vararg pids: String) {
    return this.killGroup(pids.toList())
}
