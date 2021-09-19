package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FifoProcessManagerTest {
    @Test
    fun `process could be added`() {
        val pm = FixedSizeProcessManager(5)
        pm.addProcess(Process("12351", Priority.LOW))
    }

    @Test
    fun `it is possible to more processes than limit`() {
        val pm = FifoProcessManager(1)
        pm.addProcess(Process("id1", Priority.LOW))
        pm.addProcess(Process("id2", Priority.LOW))
    }

    @Test
    fun `fifo kills old process in case size is exceeded`() {
        val pm = FifoProcessManager(1)
        //given
        val p1 = Process("p1", Priority.LOW)
        val p2 = Process("p2", Priority.LOW)
        assertThat(p1.isAlive()).isTrue
        assertThat(p2.isAlive()).isTrue
        //when
        pm.addProcess(p1)
        pm.addProcess(p2)
        //then
        assertThat(p1.isAlive()).isFalse
        assertThat(p2.isAlive()).isTrue
    }

}