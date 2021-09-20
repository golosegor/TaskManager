package com.home.taskmanager

import com.home.taskmanager.process.Priority
import com.home.taskmanager.process.Process
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GenericProcessManagerTest {
    @Test
    fun `pm could kill all processes`() {
        val pm = ProcessManager.createFixedSize(10)
        val p1 = Process("id1", Priority.LOW)
        val p2 = Process("id2", Priority.LOW)
        pm.addProcess(p1)
        pm.addProcess(p2)
        assertThat(pm.listProcesses()).containsOnly(p1, p2)
        //when
        pm.killAll()
        //then
        assertThat(pm.listRegisteredProcesses()).isEmpty()
        assertThat(p1.isAlive()).isFalse
        assertThat(p2.isAlive()).isFalse
    }

    @Test
    fun `pm could kill processes with priority`() {
        val pm = ProcessManager.createFixedSize(10)
        val p1 = Process("id1", Priority.LOW)
        val p2 = Process("id2", Priority.HIGH)
        pm.addProcess(p1)
        pm.addProcess(p2)
        assertThat(pm.listProcesses()).containsOnly(p1, p2)
        //when
        pm.killAllProcessesWithPriority(Priority.HIGH)
        //then
        assertThat(pm.listProcesses()).containsOnly(p1)
        assertThat(p1.isAlive()).isTrue
        assertThat(p2.isAlive()).isFalse
    }

    @Test
    internal fun `list with sorting by priority  works fine`() {
        val pm = ProcessManager.createFixedSize(10)
        val p1 = Process("id1", Priority.HIGH)
        val p2 = Process("id2", Priority.LOW)
        pm.addProcess(p1)
        Thread.sleep(1)
        pm.addProcess(p2)
        assertThat(pm.listProcessSortedBy { it.process.priority.value }.map { it.process }).containsExactly(p2, p1)
        assertThat(pm.listProcessSortedBy { it.timestamp }.map { it.process }).containsExactly(p1, p2)
    }
}