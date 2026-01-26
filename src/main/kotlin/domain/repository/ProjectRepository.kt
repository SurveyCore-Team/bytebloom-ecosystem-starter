package domain.repository

import domain.model.Projects

interface ProjectRepository {
    fun getAllProjects(): List<Projects>
}