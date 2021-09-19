package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PriorityProcessManagerTest {
    @Test
    fun `it is possible to more processes than limit`() {
        val pm = ProcessManager.createPriority(1)
        pm.addProcess(Process("id1", Priority.LOW))
        pm.addProcess(Process("id2", Priority.LOW))
    }

    @Test
    fun `if priority is greater that existing - it is replaces the less priority tasks`() {
        val pm = ProcessManager.createPriority(1)
        //given
        val p1 = Process("p1", Priority.LOW)
        val p2 = Process("p2", Priority.HIGH)
        assertThat(p1.isAlive()).isTrue
        assertThat(p2.isAlive()).isTrue
        //when
        pm.addProcess(p1)
        pm.addProcess(p2)
        //then
        assertThat(pm.listRunningProcesses()).containsOnly(p2)
        assertThat(p1.isAlive()).isFalse
        assertThat(p2.isAlive()).isTrue
    }

    @Test
    fun `if priority is less that existing - newly added process is ignored`() {
        val pm = ProcessManager.createPriority(1)
        //given
        val p1 = Process("p1", Priority.HIGH)
        val p2 = Process("p2", Priority.LOW)
        assertThat(p1.isAlive()).isTrue
        assertThat(p2.isAlive()).isTrue
        //when
        pm.addProcess(p1)
        pm.addProcess(p2)
        //then
        assertThat(pm.listRunningProcesses()).containsOnly(p1)
    }
}