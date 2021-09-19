package com.home.taskmanager

import com.home.taskmanager.process.Process

class FixedSizeProcessManager(override val limit: Int) : AbstractProcessManager(limit) {

    override fun addProcess(existingProcesses: List<Process>, process: Process): List<Process> {
        return if (existingProcesses.size < limit) {
            existingProcesses.toMutableList().apply { add(process) }
        } else {
            throw IllegalStateException("Process limit reached")
        }
    }
}
