package com.home.taskmanager

import com.home.taskmanager.process.Process

abstract class AbstractProcessManager(open val limit: Int) : ProcessManager {
    private var processes: List<Process> = emptyList()

    abstract fun addProcess(existingProcesses: List<Process>, process: Process): List<Process>

    final override fun addProcess(process: Process) {
        this.processes = addProcess(processes, process)

    }

    final override fun listRunningProcesses(): List<Process> {
        return processes
    }

    final override fun killProcess(pid: String) {
        TODO("Not yet implemented")
    }

    final override fun killGroup(pid: String) {
        TODO("Not yet implemented")
    }

    final override fun killAll(pid: String) {
        TODO("Not yet implemented")
    }
}