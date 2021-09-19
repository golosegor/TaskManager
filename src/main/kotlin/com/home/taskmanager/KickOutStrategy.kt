package com.home.taskmanager

import com.home.taskmanager.process.Process

interface KickOutStrategy {
    fun findProcessToKick(existingProcesses: List<Process>, processToAdd: Process): Process?
}