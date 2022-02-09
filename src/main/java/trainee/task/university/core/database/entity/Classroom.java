package trainee.task.university.core.database.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "classrooms")
@NoArgsConstructor
public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "classroom_number")
    private Integer classroomNumber;

    @Column(name = "building_name")
    private String buildingName;

    @OneToOne(mappedBy = "classroom")
    private Lesson lesson;

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomNumber=" + classroomNumber +
                ", buildingName='" + buildingName + '\'' +
                '}';
    }
}
