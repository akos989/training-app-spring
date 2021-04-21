package hu.training.app.trainingappserver.model

import javax.persistence.*

@Entity
class Exercise(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1L,

        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var image: String = "",

        @Column(nullable = false)
        var video: String = "",

        @Column(nullable = false)
        var description: String = "",

        @OneToMany(mappedBy = "exercise", cascade = [CascadeType.REMOVE])
        var workouts: MutableSet<WorkoutExercise> = mutableSetOf(),

        @ManyToMany
        @JoinTable(
                name = "exercise_equipment"
        )
        var equipments: MutableSet<Equipment> = mutableSetOf(),

        @ManyToMany
        @JoinTable(
                name = "exercise_muscle_group"
        )
        var muscleGroups: MutableSet<MuscleGroup> = mutableSetOf()
)
