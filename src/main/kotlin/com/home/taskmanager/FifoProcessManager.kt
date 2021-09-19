package com.home.taskmanager

import com.home.taskmanager.process.Process

class FifoProcessManager(private val limit: Int) : ProcessManager {
    private val processes: MutableList<Process> = mutableListOf()

    override fun addProcess(process: Process) {
        if (processes.size < limit) {
            processes.add(process)
        } else {
            val removedElement = processes.removeAt(0)
            removedElement.kill()
        }
    }

    override fun listRunningProcesses(): List<Process> {
        TODO("Not yet implemented")
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