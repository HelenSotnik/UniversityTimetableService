package trainee.task.university.core.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "lessons")
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Enumerated(EnumType.STRING)
    private LessonType lessonType;

    @OneToOne
    @JoinColumn(name = "classroom_id", unique = false, nullable = false)
    private Classroom classroom;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "timetable_id", nullable = false)
    private Timetable timetable;

    @Override
    public String toString() {
        return "Lesson{" +
                "subjectName='" + subjectName + '\'' +
                ", lessonType=" + lessonType +
                ", classroom=" + classroom +
                '}';
    }
}
