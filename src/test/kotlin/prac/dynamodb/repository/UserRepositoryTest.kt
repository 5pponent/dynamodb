package prac.dynamodb.repository

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import prac.dynamodb.entity.User
import java.sql.Timestamp

@SpringBootTest
internal class UserRepositoryTest {

    @Autowired lateinit var userRepository: UserRepository

    @Test @DisplayName("유저 저장")
    fun save() {
        val user = User("u_001", "user1", "")
        userRepository.save(user)
        userRepository.findAll().forEach { println(it.toString()) }
    }

    @Test @DisplayName("유저 수정")
    fun update() {
        userRepository.findAll()
    }

    @Test
    fun delete() {
    }

    @Test
    fun deleteByPk() {
    }
}