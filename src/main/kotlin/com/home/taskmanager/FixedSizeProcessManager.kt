package com.home.taskmanager

import com.home.taskmanager.process.Process
import java.util.concurrent.ConcurrentHashMap

class FixedSizeProcessManager(private val limit: Int) : ProcessManager {


    private val processes: MutableMap<String, Process> = ConcurrentHashMap<String, Process>()

    override fun addProcess(process: Process) {
        if (processes.size < limit) {
            processes[process.pid] = process
        } else {
            throw IllegalStateException("Process limit reached")
        }
    }

    override fun listRunningProcesses(): List<Process> {

        throw IllegalStateException("")
    }

    override fun killProcess(pid: String) {
        throw IllegalStateException("")
    }


    override fun killGroup(pid: String) {
        throw IllegalStateException("")
    }

    override fun killAll(pid: String) {
        throw IllegalStateException("")
    }
}
