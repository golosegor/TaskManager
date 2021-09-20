package com.home.taskmanager.impl.kickout.strategy.impl

import com.home.taskmanager.impl.kickout.strategy.KickOutStrategy
import com.home.taskmanager.process.RegisteredProcess
import com.home.taskmanager.process.Process
import mu.KLogging

class PriorityStrategy : KickOutStrategy {
    companion object : KLogging()

    override fun findProcessToKick(existingProcesses: List<RegisteredProcess>, processToAdd: Process): Process? {
        return existingProcesses.firstOrNull { it.process.priority.value < processToAdd.priority.value }?.process
    }
}