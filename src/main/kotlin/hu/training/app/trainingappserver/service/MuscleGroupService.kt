package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.MuscleGroupDto

interface MuscleGroupService {
    fun getMuscleGroups(): List<MuscleGroupDto>

    fun saveMuscleGroup(muscleGroupDto: MuscleGroupDto): MuscleGroupDto

    fun deleteMuscleGroup(id: Long)

    fun update(id: Long, updatedMuscleGroup: MuscleGroupDto): MuscleGroupDto
}
