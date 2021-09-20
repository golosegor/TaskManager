package com.home.taskmanager.impl

import com.home.taskmanager.ProcessManager
import com.home.taskmanager.impl.kickout.strategy.KickOutStrategy
import com.home.taskmanager.process.RegisteredProcess
import com.home.taskmanager.process.Process
import mu.KLogging
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

internal class GenericProcessManager(
    private val limit: Int,
    private val kickOutStrategy: KickOutStrategy
) : ProcessManager {
    companion object : KLogging()

    private var processes: ConcurrentMap<String, RegisteredProcess> = ConcurrentHashMap()

    override fun addProcess(process: Process) {
        if (processes.size < limit) {
            processes[process.pid] = RegisteredProcess.createWithTimestamp(process)
        } else {
            val processToKick = kickOutStrategy.findProcessToKick(processes.values.toList(), process)
            if (processToKick != null) {
                processToKick.kill()
                processes.remove(processToKick.pid)
                processes[process.pid] = RegisteredProcess.createWithTimestamp(process)
            } else {
                logger.info { "Limit exceeded, but no process to kick found -> ignoring process" }
            }
        }
    }

    override fun listRegisteredProcesses(): List<RegisteredProcess> {
        return processes.values.toList()
    }

    override fun killProcess(pid: String) {
        processes.remove(pid)?.process?.kill()
    }

    override fun killGroup(pids: List<String>) {
        pids.forEach { killProcess(it) }
    }

    override fun killAll() {
        val pidsToKill = processes.values.map { it.process.pid }
        killGroup(pidsToKill)
    }
}