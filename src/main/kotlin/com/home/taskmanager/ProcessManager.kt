package com.home.taskmanager

import com.home.taskmanager.kickout.strategy.FifoStrategy
import com.home.taskmanager.kickout.strategy.FixedSizeStrategy
import com.home.taskmanager.kickout.strategy.PriorityStrategy
import com.home.taskmanager.process.MonitoredProcess
import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process

interface ProcessManager {

    fun addProcess(process: Process)
    fun listProcessesWithMeta(): List<MonitoredProcess>
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
    val pidsToKill = listProcessesWithMeta().filter { it.process.priority == priority }.map { it.process.pid }
    this.killGroup(pidsToKill)
}

fun ProcessManager.listProcesses(): List<Process> {
    return listProcessesWithMeta().map { it.process }
}

fun <R : Comparable<R>> ProcessManager.listProcessSortedBy(selector: (MonitoredProcess) -> R): List<MonitoredProcess> {
    return listProcessesWithMeta().sortedBy(selector)
}
