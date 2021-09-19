package com.home.taskmanager

import com.home.taskmanager.process.Process
import mu.KLogging
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class GenericProcessManager(
    private val limit: Int,
    private val kickOutStrategy: KickOutStrategy
) : ProcessManager {
    companion object : KLogging()

    private var processes: ConcurrentMap<String, Process> = ConcurrentHashMap()

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

    override fun listProcesses(): List<Process> {
        return processes.values.toList()
    }

    override fun killProcess(pid: String) {
        processes.remove(pid)?.kill()
    }

    override fun killGroup(pids: List<String>) {
        pids.forEach { killProcess(it) }
    }

    override fun killAll() {
        processes.values.forEach { d -> killProcess(d.pid) }
    }
}