package hu.training.app.trainingappserver.model

import javax.persistence.*

@Entity
class Equipment (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = -1L,

        @Column(nullable = false)
        var name: String = "",

        @Column(nullable = false)
        var image: String = ""
)
