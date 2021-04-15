package hu.training.app.trainingappserver.model

import java.io.Serializable
import javax.persistence.Column
import javax.persistence.Embeddable

@Embeddable
data class WorkoutExerciseKey(
        @Column(name = "workout_id")
        var workoutId: Long = -1L,

        @Column(name = "exercise_id")
        var exerciseId: Long = -1L
) : Serializable
