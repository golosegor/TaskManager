package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FifoProcessManagerTest {
    @Test
    fun `process could be added`() {
        val pm = ProcessManager.createFifo(5)
        pm.addProcess(Process("12351", Priority.LOW))
    }

    @Test
    fun `pm does not throw in case process limit exceeded`() {
        val pm = ProcessManager.createFifo(1)
        val p1 = Process("id1", Priority.LOW)
        val p2 = Process("id2", Priority.LOW)
        pm.addProcess(p1)
        pm.addProcess(p2)
        assertThat(pm.listRunningProcesses()).containsExactly(p2)
        //then
        assertThat(p1.isAlive()).isFalse
        assertThat(p2.isAlive()).isTrue
    }

    @Test
    fun `fifo kills old process in case size is exceeded`() {
        val pm = ProcessManager.createFifo(1)
        //given
        val p1 = Process("p1", Priority.LOW)
        val p2 = Process("p2", Priority.LOW)
        assertThat(p1.isAlive()).isTrue
        assertThat(p2.isAlive()).isTrue
        //when
        pm.addProcess(p1)
        pm.addProcess(p2)
    }
}