package com.home.taskmanager.impl.kickout.strategy.impl

import com.home.taskmanager.impl.kickout.strategy.KickOutStrategy
import com.home.taskmanager.process.RegisteredProcess
import com.home.taskmanager.process.Process

class FixedSizeStrategy : KickOutStrategy {

    override fun findProcessToKick(existingProcesses: List<RegisteredProcess>, processToAdd: Process): Process {
        throw IllegalStateException("Process limit reached")
    }
}
