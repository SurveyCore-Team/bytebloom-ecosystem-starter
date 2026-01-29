package domain.repository

import domain.model.Project

interface ProjectRepository {
    fun getAllProjects(): List<Project>
}