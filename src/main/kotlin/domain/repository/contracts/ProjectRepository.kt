package domain.repository.contracts

import domain.model.Projects

interface ProjectRepository {
    fun getAllProjects(): List<Projects>
}