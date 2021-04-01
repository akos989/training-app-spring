package hu.training.app.trainingappserver.service

import hu.training.app.trainingappserver.model.Exercise

interface ExerciseService {

    fun getExercises(): List<Exercise>
}
