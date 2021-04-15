package hu.training.app.trainingappserver.model

import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Entity
class Workout (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1L,

        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var image: String = "",

        @Column(nullable = false)
        @Min(1)
        var duration: Int = 1,

        @Column(nullable = false)
        var type: String = "",

        @Column(nullable = false)
        @Min(1)
        @Max(3)
        var intensity: Int = 1,

        @OneToMany(mappedBy = "workout", cascade = [CascadeType.REMOVE])
        var exercises: MutableSet<WorkoutExercise> = mutableSetOf()
)
