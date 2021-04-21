package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.EquipmentDto

interface EquipmentService {
    fun getEquipments(): List<EquipmentDto>

    fun saveEquipment(muscleGroupDto: EquipmentDto): EquipmentDto

    fun deleteEquipment(id: Long)

    fun update(id: Long, updatedEquipment: EquipmentDto): EquipmentDto
}
