package trainee.task.university.core.application.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import trainee.task.university.core.database.entity.Faculty;
import trainee.task.university.core.database.entity.GroupName;
import trainee.task.university.core.database.entity.Speciality;

import java.util.Set;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long phone;
    private String email;
    private GroupName groupName;
    private Faculty faculty;
    private Speciality speciality;
    private Set<TimetableDto> timetables;
    private Set<GrantedAuthority> authorities;
}
