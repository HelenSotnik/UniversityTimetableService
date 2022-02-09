package trainee.task.university.core.database.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "students")
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column
    private Integer age;

    @Column
    private Long phone;

    @Column(unique = true, nullable = false)
    private String email;

    @Column
    private String password;

    @Column( name = "group_name")
    @Enumerated(EnumType.STRING)
    private GroupName groupName;

    @Column
    @Enumerated(EnumType.STRING)
    private Faculty faculty;

    @Column
    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private Set<Timetable> timetables = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> authorities = new HashSet<>();
}
