package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.EquipmentDto
import hu.training.app.trainingappserver.dto.toDTO
import hu.training.app.trainingappserver.model.Equipment
import hu.training.app.trainingappserver.repository.EquipmentRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class EquipmentServiceImpl(
        private val equipmentRepository: EquipmentRepository,
        private val mapper: ModelMapper
): EquipmentService{
    override fun getEquipments(): List<EquipmentDto> {
        return equipmentRepository.findAll().toDTO(mapper)
    }

    override fun saveEquipment(muscleGroupDto: EquipmentDto): EquipmentDto {
        return muscleGroupDto.run {
            if (equipmentRepository.findByName(name) != null) {
                throw ResponseStatusException(HttpStatus.CONFLICT, "Equipment with name $name already exists")
            }
            val equipment = Equipment(name = name, image = image)
            equipmentRepository.save(equipment)
            equipment.toDTO(mapper)
        }
    }

    override fun deleteEquipment(id: Long) {
        val equipment = findEquipmentById(id)
        equipmentRepository.delete(equipment)
    }

    override fun update(id: Long, updatedEquipment: EquipmentDto): EquipmentDto {
        return updatedEquipment.run {
            val equipment = findEquipmentById(id)
            equipment.let {
                it.name = name
                it.image = image
            }
            equipmentRepository.save(equipment)
            equipment.toDTO(mapper)
        }
    }

    private fun findEquipmentById(id: Long): Equipment {
        return equipmentRepository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found")
    }
}
