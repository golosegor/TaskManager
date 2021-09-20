package com.home.taskmanager.impl.kickout.strategy

import com.home.taskmanager.process.RegisteredProcess
import com.home.taskmanager.process.Process

interface KickOutStrategy {
    fun findProcessToKick(existingProcesses: List<RegisteredProcess>, processToAdd: Process): Process?
}