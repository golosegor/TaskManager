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
        val limit = 1
        val pm = FixedSizeProcessManager(limit)
        pm.addProcess(Process("id1", Priority.LOW))
        assertThrows(IllegalStateException::class.java) {
            pm.addProcess(Process("id2", Priority.LOW))
        }
    }
}
