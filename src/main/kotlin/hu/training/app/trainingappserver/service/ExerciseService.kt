package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.ExerciseDto

interface ExerciseService {

    fun getExercises(): List<ExerciseDto>

    fun saveExercise(exerciseDto: ExerciseDto): ExerciseDto

    fun deleteExercise(id: Long)

    fun update(id: Long, updatedExerciseDto: ExerciseDto): ExerciseDto
}
