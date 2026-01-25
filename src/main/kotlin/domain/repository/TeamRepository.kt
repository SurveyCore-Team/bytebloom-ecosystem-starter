package domain.repository

import domain.model.Team

interface TeamRepository {
    fun getAllTeams(): List<Team>
}