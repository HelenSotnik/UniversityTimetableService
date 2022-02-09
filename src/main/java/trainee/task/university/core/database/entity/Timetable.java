package trainee.task.university.core.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "timetables")
@NoArgsConstructor
public class Timetable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private DayOfTheWeek dayOfTheWeek;

    @OneToMany(mappedBy = "timetable", fetch = FetchType.EAGER)
    private List<Lesson> lessons;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE,CascadeType.PERSIST})
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Override
    public String toString() {
        return "Timetable{" +
                "dayOfTheWeek=" + dayOfTheWeek +
                ", lessons=" + lessons +
                '}';
    }
}
