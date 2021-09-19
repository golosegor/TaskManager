package com.home.taskmanager

import com.home.taskmanager.process.Process

class FifoProcessManager(override val limit: Int) : AbstractProcessManager(limit) {
    override fun addProcess(existingProcesses: List<Process>, process: Process): List<Process> {
        return if (existingProcesses.size < limit) {
            existingProcesses.plus(process)
        } else {
            return existingProcesses.toMutableList().apply {
                val removeAt = removeAt(0)
                removeAt.kill()
                add(process)
            }
        }
    }
}