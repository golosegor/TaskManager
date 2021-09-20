package com.home.taskmanager

import com.home.taskmanager.impl.GenericProcessManager
import com.home.taskmanager.impl.kickout.strategy.impl.FifoStrategy
import com.home.taskmanager.impl.kickout.strategy.impl.FixedSizeStrategy
import com.home.taskmanager.impl.kickout.strategy.impl.PriorityStrategy
import com.home.taskmanager.process.RegisteredProcess
import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process

interface ProcessManager {

    fun addProcess(process: Process)
    fun listRegisteredProcesses(): List<RegisteredProcess>
    fun killProcess(pid: String)
    fun killGroup(pids: List<String>)
    fun killAll()

    companion object {
        const val PM_MAX_CAPACITY = 5
        fun createFixedSize(limit: Int = PM_MAX_CAPACITY) = GenericProcessManager(limit, FixedSizeStrategy())

        fun createFifo(limit: Int = PM_MAX_CAPACITY) = GenericProcessManager(limit, FifoStrategy())
        fun createPriority(limit: Int = PM_MAX_CAPACITY) = GenericProcessManager(limit, PriorityStrategy())
    }
}

fun ProcessManager.killAllProcessesWithPriority(priority: Priority) {
    val pidsToKill = listRegisteredProcesses().filter { it.process.priority == priority }.map { it.process.pid }
    this.killGroup(pidsToKill)
}

fun ProcessManager.listProcesses(): List<Process> {
    return listRegisteredProcesses().map { it.process }
}

fun <R : Comparable<R>> ProcessManager.listProcessSortedBy(selector: (RegisteredProcess) -> R): List<RegisteredProcess> {
    return listRegisteredProcesses().sortedBy(selector)
}
