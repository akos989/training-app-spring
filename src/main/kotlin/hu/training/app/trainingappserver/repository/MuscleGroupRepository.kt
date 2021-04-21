package hu.training.app.trainingappserver.repository

import hu.training.app.trainingappserver.model.MuscleGroup
import org.springframework.data.jpa.repository.JpaRepository

interface MuscleGroupRepository : JpaRepository<MuscleGroup, Long> {

    fun findByName(name: String): MuscleGroup?
}
