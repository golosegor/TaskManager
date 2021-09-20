package com.home.taskmanager.kickout.strategy

import com.home.taskmanager.KickOutStrategy
import com.home.taskmanager.process.MonitoredProcess
import com.home.taskmanager.process.Process
import mu.KLogging

class PriorityStrategy : KickOutStrategy {
    companion object : KLogging()

    override fun findProcessToKick(existingProcesses: List<MonitoredProcess>, processToAdd: Process): Process? {
        return existingProcesses.firstOrNull { it.process.priority.value < processToAdd.priority.value }?.process
    }
}