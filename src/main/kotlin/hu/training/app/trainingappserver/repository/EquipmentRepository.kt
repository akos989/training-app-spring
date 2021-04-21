package hu.training.app.trainingappserver.repository

import hu.training.app.trainingappserver.model.Equipment
import org.springframework.data.jpa.repository.JpaRepository

interface EquipmentRepository : JpaRepository<Equipment, Long> {
    fun findByName(name: String): Equipment?
}
