package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import mu.KLogging
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class FixedSizeProcessManagerTest {
    companion object : KLogging()

    @Test
    fun `process could be added`() {
        val pm = FixedSizeProcessManager(5)
        pm.addProcess(Process("12351", Priority.LOW))
    }

    @Test
    fun `it is not possible to more processes than limit`() {
        val limit = 5
        val pm = FixedSizeProcessManager(limit)
        assertThrows(IllegalStateException::class.java) {
            (1..limit + 1).forEach { id ->
                pm.addProcess(Process("$id", Priority.LOW))
            }
        }
    }
}
