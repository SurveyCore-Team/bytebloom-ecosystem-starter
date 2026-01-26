package domain.repository

import domain.model.Mentee

interface MenteeRepository {
    fun getAllMentees(): List<Mentee>
}