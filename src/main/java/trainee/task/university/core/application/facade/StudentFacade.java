package trainee.task.university.core.application.facade;

import javassist.NotFoundException;
import org.springframework.stereotype.Component;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.application.dto.StudentDto;
import trainee.task.university.core.domain.model.StudentModel;
import trainee.task.university.core.domain.service.StudentService;
import trainee.task.university.core.mapper.StudentMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentFacade {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    public StudentFacade(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    public StudentDto findStudentById(Long id) throws NotFoundException {
        StudentModel studentModel = studentService.findStudentById(id);
        return studentMapper.modelToDto(studentModel);
    }

    public List<StudentDto> findAllStudents() {
        List<StudentModel> studentModelList = studentService.findAllStudents();
        return studentModelList.stream().map(studentMapper::modelToDto).collect(Collectors.toList());
    }

    public void delete(Long id) throws NotFoundException {
        studentService.delete(id);
    }

    public StudentDto save(AddStudentRequestDto studentRequestDto) {
        StudentModel studentModel = studentMapper.dtoToModel(studentRequestDto);
        return studentMapper.modelToDto(studentService.save(studentModel));
    }

    public StudentDto update(Long id, AddStudentRequestDto studentRequestDto) throws NotFoundException {
        StudentModel studentModel = studentMapper.dtoToModel(studentRequestDto);
        return studentMapper.modelToDto(studentService.update(id, studentModel));
    }

    public StudentDto findStudentByEmail(String email) throws NotFoundException {
        StudentModel studentModel = studentService.findStudentByEmail(email);
        return studentMapper.modelToDto(studentModel);
    }
}
