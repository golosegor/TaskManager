package com.home.taskmanager

import com.home.taskmanager.process.Process
import mu.KLogging

class GenericProcessManager(
    private val limit: Int,
    private val kickOutStrategy: KickOutStrategy
) : ProcessManager {
    companion object : KLogging()

    private var processes: MutableMap<String, Process> = mutableMapOf()

    override fun addProcess(process: Process) {
        if (processes.size < limit) {
            processes[process.pid] = process
        } else {
            val processToKick = kickOutStrategy.findProcessToKick(processes.values.toList(), process)
            if (processToKick != null) {
                processToKick.kill()
                processes.remove(processToKick.pid)
                processes[process.pid] = process
            } else {
                logger.info { "Limit exceeded, but no process to kick found -> ignoring process" }
            }
        }
    }

    override fun listRunningProcesses(): List<Process> {
        return processes.values.toList()
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