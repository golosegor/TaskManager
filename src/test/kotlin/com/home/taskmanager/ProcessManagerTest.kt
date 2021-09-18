package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import mu.KLogging
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.omg.CORBA.portable.ApplicationException


class ProcessManagerTest {
    companion object : KLogging()

    @Test
    fun `process could be added`() {
        val pm = ProcessManager()
        pm.addProcess(Process("12351", Priority.LOW))
    }

    @Test
    fun `it is not possible to more processes than limit`() {
        val pm = ProcessManager()
        assertThrows(IllegalStateException::class.java) {
            (0..ProcessManager.Companion.MAX_CAPACITY + 1).forEach { id ->
                pm.addProcess(Process("$id", Priority.LOW))
            }
        }
    }
}
