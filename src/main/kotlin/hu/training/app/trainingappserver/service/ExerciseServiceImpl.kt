package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.model.Exercise
import hu.training.app.trainingappserver.repository.ExerciseRepository
import org.springframework.stereotype.Service

@Service
class ExerciseServiceImpl(
        private val exerciseRepository: ExerciseRepository
) : ExerciseService {

    override fun getExercises(): List<Exercise> {
        return exerciseRepository.findAll()
    }
}
