package trainee.task.university.core.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.application.dto.StudentDto;
import trainee.task.university.core.database.entity.Student;
import trainee.task.university.core.domain.model.StudentModel;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class StudentMapper {

    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public StudentMapper(ModelMapper mapper, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    public StudentModel entityToModel(Student student) {
        if (student == null) {
            return null;
        }
        StudentModel studentModel = mapper.map(student, StudentModel.class);
        studentModel.setAuthoritySet(student.getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet()));
        return studentModel;

    }

    public StudentDto modelToDto(StudentModel studentModel) {
        return Objects.isNull(studentModel) ? null : mapper.map(studentModel, StudentDto.class);
    }

    public Student modelToEntity(StudentModel studentModel) {
        Student student = mapper.map(studentModel, Student.class);
        student.setPassword(passwordEncoder.encode(studentModel.getPassword()));
        return student;
    }

    public StudentModel dtoToModel(AddStudentRequestDto studentDto) {
        return Objects.isNull(studentDto) ? null : mapper.map(studentDto, StudentModel.class);
    }

    public StudentDto entityToDto(Student student) {
        return Objects.isNull(student) ? null : mapper.map(student, StudentDto.class);
    }
}
