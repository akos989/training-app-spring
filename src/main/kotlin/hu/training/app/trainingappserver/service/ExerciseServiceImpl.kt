package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.dto.ExerciseDto
import hu.training.app.trainingappserver.dto.toDTO
import hu.training.app.trainingappserver.model.Exercise
import hu.training.app.trainingappserver.repository.ExerciseRepository
import org.modelmapper.ModelMapper
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.lang.Exception
import javax.transaction.Transactional

@Service
class ExerciseServiceImpl(
        private val exerciseRepository: ExerciseRepository,
        private val mapper: ModelMapper
) : ExerciseService {

    override fun getExercises(): List<ExerciseDto> {
        val exercises = exerciseRepository.findAll()
        return exercises.toDTO(mapper)
    }

    @Transactional
    override fun saveExercise(exerciseDto: ExerciseDto): ExerciseDto {
        return exerciseDto.run {
            val exercise = exerciseRepository.save(
                    Exercise(
                            name = name,
                            image = image,
                            video = video,
                            description = description
                    )
            )
            exercise.toDTO(mapper)
        }
    }

    @Transactional
    override fun deleteExercise(id: Long) {
        val exercise = getExerciseById(id)
        exerciseRepository.delete(exercise)
    }

    @Transactional
    override fun update(id: Long, updatedExerciseDto: ExerciseDto): ExerciseDto {
        return updatedExerciseDto.run {
            val exercise = getExerciseById(id)
            exercise.let {
                it.name = name
                it.description = description
                it.image = image
                it.video = video
            }
            exerciseRepository.save(exercise)
            exercise.toDTO(mapper)
        }
    }

    private fun getExerciseById(id: Long): Exercise {
        return exerciseRepository.findByIdOrNull(id)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "Exercise with $id not found!")
    }
}
