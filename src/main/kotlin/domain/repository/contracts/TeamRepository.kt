package domain.repository.contracts

import domain.model.Team

interface TeamRepository {
    fun getAllTeams(): List<Team>
}