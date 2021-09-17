package com.home.taskmanager

import mu.KLogging
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TaskManagerApplicationTests {
  companion object : KLogging()

  @Test
  fun `demo test`() {
    logger.info { "yo" }
    assertThat("").isEmpty()
  }
}
