package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class FifoProcessManagerTest {
    @Test
    fun `process could be added`() {
        val pm = FixedSizeProcessManager(5)
        pm.addProcess(Process("12351", Priority.LOW))
    }

    @Test
    fun `it is possible to more processes than limit`() {
        val pm = FifoProcessManager(5)
        (1..5 + 1).forEach { id ->
            pm.addProcess(Process("$id", Priority.LOW))
        }
    }
}