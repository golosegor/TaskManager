package com.home.taskmanager.kickout.strategy

import com.home.taskmanager.KickOutStrategy
import com.home.taskmanager.process.Process

class FifoStrategy : KickOutStrategy {

    override fun findProcessToKick(existingProcesses: List<Process>, processToAdd: Process): Process {
        return existingProcesses[0]
    }
}