package com.fastcampus.kotlinspring.todo.service

import com.fastcampus.kotlinspring.todo.domain.Todo
import com.fastcampus.kotlinspring.todo.domain.TodoRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
class TodoServiceTest {
    @MockkBean
    lateinit var todoRepository: TodoRepository

    lateinit var todoService: TodoService

    val stub: Todo by lazy {
       Todo(
           id = 1,
           title="테스트",
           description = "테스트 상세",
           done = false,
           createdAt = LocalDateTime.now(),
           updatedAt = LocalDateTime.now(),
       )
    }

    @BeforeEach
    fun setUp() {
        todoService = TodoService(todoRepository)
    }

    @Test
    fun `한개의 TODO를 반환 해야 한다`() {
        // Given
        every { todoRepository.findByIdOrNull(1) } returns stub
        // When
        val actual = todoService.findById(1)
        // Then
        assertThat(actual).isNotNull
        assertThat(actual).isEqualTo(stub)
    }

}
