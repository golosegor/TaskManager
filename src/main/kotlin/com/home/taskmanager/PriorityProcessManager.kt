package com.home.taskmanager

import com.home.taskmanager.process.Process
import mu.KLogging

class PriorityProcessManager(override val limit: Int) : AbstractProcessManager(limit) {

    companion object : KLogging()

    override fun addProcess(existingProcesses: List<Process>, process: Process): List<Process> {
        return if (existingProcesses.size < limit) {
            existingProcesses.toMutableList().apply { add(process) }
        } else {
            val processes = existingProcesses.toMutableList()
            val processToReplace = processes.firstOrNull { it.priority < process.priority }
            if (processToReplace != null) {
                processToReplace.kill()
                processes.remove(processToReplace)
                processes.add(process)
                processes
            } else {
                logger.info { "Skipping process" }
                existingProcesses
            }
        }
    }


}