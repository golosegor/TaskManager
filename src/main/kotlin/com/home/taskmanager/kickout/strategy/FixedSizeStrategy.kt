package com.home.taskmanager.kickout.strategy

import com.home.taskmanager.KickOutStrategy
import com.home.taskmanager.process.Process

class FixedSizeStrategy : KickOutStrategy {

    override fun findProcessToKick(existingProcesses: List<Process>, processToAdd: Process): Process {
        throw IllegalStateException("Process limit reached")
    }
}
