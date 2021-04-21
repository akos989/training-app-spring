package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.MuscleGroupDto
import hu.training.app.trainingappserver.dto.toDTO
import hu.training.app.trainingappserver.model.MuscleGroup
import hu.training.app.trainingappserver.repository.MuscleGroupRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class MuscleGroupServiceImpl(
        private val muscleGroupRepository: MuscleGroupRepository,
        private val mapper: ModelMapper
) : MuscleGroupService {
    override fun getMuscleGroups(): List<MuscleGroupDto> {
        return muscleGroupRepository.findAll().toDTO(mapper)
    }

    @Transactional
    override fun saveMuscleGroup(muscleGroupDto: MuscleGroupDto): MuscleGroupDto {
        val exists = muscleGroupRepository.findByName(muscleGroupDto.name)
        if (exists != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "Muscle group with name ${muscleGroupDto.name} already exists.")
        }
        val newMuscleGroup = muscleGroupRepository.save(MuscleGroup(name = muscleGroupDto.name))
        return newMuscleGroup.toDTO(mapper)
    }

    @Transactional
    override fun deleteMuscleGroup(id: Long) {
        val muscleGroup = findMuscleGroupById(id)
        muscleGroupRepository.delete(muscleGroup)
    }

    @Transactional
    override fun update(id: Long, updatedMuscleGroup: MuscleGroupDto): MuscleGroupDto {
        return updatedMuscleGroup.run {
            val muscleGroup = findMuscleGroupById(id)
            muscleGroup.let {
                it.name = name
            }
            muscleGroupRepository.save(muscleGroup)
            muscleGroup.toDTO(mapper)
        }
    }

    private fun findMuscleGroupById(id: Long): MuscleGroup {
        return muscleGroupRepository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Muscle group not found")
    }
}
