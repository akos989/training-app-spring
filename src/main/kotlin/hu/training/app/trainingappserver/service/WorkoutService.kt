package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.WorkoutDto
import hu.training.app.trainingappserver.dto.request.CreateWorkoutExercise

interface WorkoutService {

    fun getWorkouts(): List<WorkoutDto>

    fun saveWorkout(workoutDto: WorkoutDto): WorkoutDto

    fun deleteWorkout(id: Long)

    fun update(id: Long, updatedWorkoutDto: WorkoutDto): WorkoutDto

    fun addExercises(id: Long, createWorkoutExercises: List<CreateWorkoutExercise>): WorkoutDto
}
