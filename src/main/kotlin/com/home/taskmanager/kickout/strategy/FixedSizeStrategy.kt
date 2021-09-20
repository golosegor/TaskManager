package com.home.taskmanager.kickout.strategy

import com.home.taskmanager.KickOutStrategy
import com.home.taskmanager.process.MonitoredProcess
import com.home.taskmanager.process.Process

class FixedSizeStrategy : KickOutStrategy {

    override fun findProcessToKick(existingProcesses: List<MonitoredProcess>, processToAdd: Process): Process {
        throw IllegalStateException("Process limit reached")
    }
}
