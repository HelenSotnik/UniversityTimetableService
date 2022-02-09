package trainee.task.university.core.domain.service;

import javassist.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.database.entity.Student;
import trainee.task.university.core.database.repository.StudentRepository;
import trainee.task.university.core.domain.model.StudentModel;
import trainee.task.university.core.mapper.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements UserDetailsService {

    private final StudentRepository studentRepository;
    private final StudentMapper mapper;

    public StudentService(StudentRepository studentRepository, StudentMapper mapper) {
        this.studentRepository = studentRepository;
        this.mapper = mapper;
    }

    public List<StudentModel> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentModel> studentList = students.stream()
                .map(mapper::entityToModel).collect(Collectors.toList());
        return studentList;
    }

    public StudentModel findStudentById(Long id) throws NotFoundException {
        return studentRepository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with id: " + id)));
    }

    public StudentModel save(StudentModel studentModel) {
        Student student = mapper.modelToEntity(studentModel);
        return mapper.entityToModel(studentRepository.save(student));
    }

    public StudentModel update(Long id, StudentModel studentModel) throws NotFoundException {
        studentRepository.findById(id).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with id: " + id)));
        Student student = mapper.modelToEntity(studentModel);
        student.setId(id);
        return mapper.entityToModel(studentRepository.save(student));
    }

    public void delete(Long id) throws NotFoundException {
        studentRepository.findById(id).map(mapper::entityToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with id: " + id)));
        studentRepository.deleteById(id);
    }

    public boolean existsByEmail(String email) {
        return studentRepository.existsByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Student student = studentRepository.findByEmail(username)
              .orElseThrow(() -> new UsernameNotFoundException(String.format("Student not found with username: " + username)));
       return mapper.entityToModel(student);

    }

    public void singUpNewStudent(AddStudentRequestDto studentDto) {
        StudentModel studentModel = mapper.dtoToModel(studentDto);
        mapper.modelToDto(save(studentModel));
    }

    public StudentModel findStudentByEmail(String email) throws NotFoundException {
        return studentRepository.findByEmail(email).map(mapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Student not found with email: " + email)));

    }
}
