package com.home.taskmanager

import com.home.taskmanager.kickout.strategy.FifoStrategy
import com.home.taskmanager.kickout.strategy.FixedSizeStrategy
import com.home.taskmanager.kickout.strategy.PriorityStrategy
import com.home.taskmanager.process.Process
import mu.KLogging

interface ProcessManager {

    fun addProcess(process: Process)
    fun listRunningProcesses(): List<Process>
    fun killProcess(pid: String)
    fun killGroup(pid: String)
    fun killAll(pid: String)

    companion object : KLogging(){
        fun createFixedSize(limit: Int) =  GenericProcessManager(limit, FixedSizeStrategy())
        fun createFifo(limit: Int) =  GenericProcessManager(limit, FifoStrategy())
        fun createPriority(limit: Int) =  GenericProcessManager(limit, PriorityStrategy())
    }
}