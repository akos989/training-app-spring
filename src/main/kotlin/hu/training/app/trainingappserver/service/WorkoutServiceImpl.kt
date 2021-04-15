package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.WorkoutDto
import hu.training.app.trainingappserver.dto.request.CreateWorkoutExercise
import hu.training.app.trainingappserver.dto.toDTO
import hu.training.app.trainingappserver.model.Workout
import hu.training.app.trainingappserver.model.WorkoutExercise
import hu.training.app.trainingappserver.repository.ExerciseRepository
import hu.training.app.trainingappserver.repository.WorkoutExerciseRepository
import hu.training.app.trainingappserver.repository.WorkoutRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
class WorkoutServiceImpl(
        private val workoutRepository: WorkoutRepository,
        private val exerciseRepository: ExerciseRepository,
        private val workoutExerciseRepository: WorkoutExerciseRepository,
        private val mapper: ModelMapper
) : WorkoutService {

    override fun getWorkouts(): List<WorkoutDto> {
        return workoutRepository.findAll().toDTO(mapper)
    }

    @Transactional
    override fun saveWorkout(workoutDto: WorkoutDto): WorkoutDto {
        return workoutDto.run {
            val workout = workoutRepository.save(
                    Workout(
                            name = name,
                            image = image,
                            duration = duration,
                            type = type,
                            intensity = intensity
                    )
            )
            workout.toDTO(mapper)
        }
    }

    @Transactional
    override fun deleteWorkout(id: Long) {
        val workout = getWorkoutById(id)
        workoutRepository.delete(workout)
    }

    @Transactional
    override fun update(id: Long, updatedWorkoutDto: WorkoutDto): WorkoutDto {
        return updatedWorkoutDto.run {
            val workout = getWorkoutById(id)
            workout.let {
                it.name = name
                it.image = image
                it.duration = duration
                it.type = type
                it.intensity = intensity
            }
            workoutRepository.save(workout)
            workout.toDTO(mapper)
        }
    }

    override fun addExercises(id: Long, createWorkoutExercises: List<CreateWorkoutExercise>): WorkoutDto {
        val workout = getWorkoutById(id)
        var workoutExercises = createWorkoutExercises.mapNotNull {
            val exercise = exerciseRepository.findByIdOrNull(it.id)
            if (exercise != null)
                WorkoutExercise(
                        workout = workout,
                        exercise = exercise,
                        amount = it.amount,
                        type = it.type
                )
            else
                null
        }
        workoutExercises = workoutExerciseRepository.saveAll(workoutExercises)
        workout.exercises.addAll(workoutExercises)
        return workout.toDTO(mapper)
    }

    private fun getWorkoutById(id: Long): Workout {
        return workoutRepository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Workout with $id not found!")
    }
}
