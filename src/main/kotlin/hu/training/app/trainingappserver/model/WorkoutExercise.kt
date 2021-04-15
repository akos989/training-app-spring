package hu.training.app.trainingappserver.model

import javax.persistence.*
import javax.validation.constraints.Min

@Entity
class WorkoutExercise (
        @EmbeddedId
        var id: WorkoutExerciseKey = WorkoutExerciseKey(),

        @ManyToOne
        @MapsId("workoutId")
        @JoinColumn(name = "workout_id")
        var workout: Workout,

        @ManyToOne
        @MapsId("exerciseId")
        @JoinColumn(name = "exercise_id")
        var exercise: Exercise,

        @Column(nullable = false)
        @Min(1)
        var amount: Int = 1,

        @Column(nullable = false)
        var type: String = "",
)
