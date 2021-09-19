package com.home.taskmanager

import com.home.taskmanager.process.Process
import mu.KLogging

class PriorityProcessManager(private val limit: Int) : ProcessManager {
    private val processes: MutableList<Process> = mutableListOf()

    companion object : KLogging()

    override fun addProcess(process: Process) {
        if (processes.size < limit) {
            processes.add(process)
        } else {
            val processToReplace = processes.firstOrNull { it.priority < process.priority }
            if (processToReplace != null) {
                processToReplace.kill()
                processes.remove(processToReplace)
                processes.add(process)
            } else {
                logger.info { "Skipping process" }
            }
        }
    }

    override fun listRunningProcesses(): List<Process> {
        return this.processes
    }

    override fun killProcess(pid: String) {
        TODO("Not yet implemented")
    }

    override fun killGroup(pid: String) {
        TODO("Not yet implemented")
    }

    override fun killAll(pid: String) {
        TODO("Not yet implemented")
    }
}