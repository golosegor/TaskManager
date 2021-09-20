package com.home.taskmanager

import com.home.taskmanager.process.MonitoredProcess
import com.home.taskmanager.process.Process

interface KickOutStrategy {
    fun findProcessToKick(existingProcesses: List<MonitoredProcess>, processToAdd: Process): Process?
}