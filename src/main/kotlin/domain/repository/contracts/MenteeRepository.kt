package domain.repository.contracts

import domain.model.Mentee

interface MenteeRepository {
    fun getAllMentees(): List<Mentee>
    fun getMenteeById(id: String): Mentee?
}