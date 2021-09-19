package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import mu.KLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test


class FixedSizeProcessManagerTest {
    companion object : KLogging()

    @Test
    fun `process could be added`() {
        val pm = ProcessManager.createFixedSize(5)
        val p1 = Process("12351", Priority.LOW)
        pm.addProcess(p1)
        assertThat(pm.listProcesses()).containsExactly(p1)
    }

    @Test
    fun `it is not possible to more processes than limit`() {
        val limit = 1
        val pm = ProcessManager.createFixedSize(limit)
        val p1 = Process("id1", Priority.LOW)
        pm.addProcess(p1)
        assertThrows(IllegalStateException::class.java) {
            pm.addProcess(Process("id2", Priority.LOW))
        }
        assertThat(pm.listProcesses()).containsExactly(p1)
    }
}
