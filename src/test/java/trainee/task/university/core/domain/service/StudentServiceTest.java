package trainee.task.university.core.domain.service;

import javassist.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import trainee.task.university.core.database.entity.*;
import trainee.task.university.core.database.repository.StudentRepository;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.domain.model.LessonModel;
import trainee.task.university.core.domain.model.StudentModel;
import trainee.task.university.core.domain.model.TimetableModel;
import trainee.task.university.core.mapper.StudentMapper;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository repository;

    @Mock
    private StudentMapper mapper;

    private Student student = new Student();
    private StudentModel studentModel = new StudentModel();

    @BeforeEach
    private void studentModelInitialization() {
        ClassroomModel classroomModel = new ClassroomModel();
        classroomModel.setId(400L);
        classroomModel.setBuildingName("Corpus 3");
        classroomModel.setClassroomNumber(400);

        LessonModel lessonModel = new LessonModel();
        lessonModel.setId(1L);
        lessonModel.setClassroom(classroomModel);
        lessonModel.setLessonType(LessonType.LECTURE);
        lessonModel.setSubjectName("Higher Mathematics");

        TimetableModel timetableModel = new TimetableModel();
        timetableModel.setId(400L);
        timetableModel.setDayOfTheWeek(DayOfTheWeek.MONDAY);
        timetableModel.setLessons(Collections.singletonList(lessonModel));

        studentModel.setId(1L);
        studentModel.setFirstName("Rita");
        studentModel.setLastName("Milkovich");
        studentModel.setAge(18);
        studentModel.setEmail("student@gmail.com");
        studentModel.setPassword("ppdd123");
        studentModel.setFaculty(Faculty.MECHANICAL_FACULTY);
        studentModel.setGroupName(GroupName.MEF_103);
        studentModel.setSpeciality(Speciality.AERODYNAMICS_AND_FLIGHT_SAFETY);
        studentModel.setPhone(380995436622L);
        studentModel.setTimetables(Collections.singleton(timetableModel));
        studentModel.setAuthoritySet(new HashSet<>());
    }

    @Test
    void findAllStudents_correctValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(student));
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        List<StudentModel> actualStudentList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertTrue(actualStudentList.size() == 1);
        assertEquals(studentModel.getAge(), actualStudentList.get(0).getAge());
        assertEquals(studentModel.getEmail(), actualStudentList.get(0).getEmail());
        assertSame(actualStudentList.get(0).getGroupName(), studentModel.getGroupName());
        assertTrue(actualStudentList.get(0).getFaculty().equals(studentModel.getFaculty()));
        assertTrue(actualStudentList.get(0).getSpeciality().equals(studentModel.getSpeciality()));
        assertEquals(studentModel.getLastName(), actualStudentList.get(0).getLastName());
    }

    @Test
    void findAllStudents_incorrectValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(student));
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        List<StudentModel> actualStudentList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertFalse(actualStudentList.size() != 1);
        assertNotEquals(100, actualStudentList.get(0).getAge());
        assertNotEquals("WRONG EMAIL", actualStudentList.get(0).getEmail());
        assertNotSame(actualStudentList.get(0).getGroupName(), GroupName.EMF_107);
        assertFalse(actualStudentList.get(0).getFaculty().equals(Faculty.FACULTY_OF_ECONOMICS_AND_BUSINESS_ADMINISTRATION));
        assertFalse(!actualStudentList.get(0).getSpeciality().equals(studentModel.getSpeciality()));
        assertNotEquals("WRONG PARAMETER", actualStudentList.get(0).getLastName());
    }

    @Test
    void findStudentById_correctValuesTest() {
        when(repository.getById(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actualStudent = mapper.entityToModel(repository.getById(any()));

        assertEquals(studentModel.getAge(), actualStudent.getAge());
        assertEquals(studentModel.getEmail(), actualStudent.getEmail());
        assertSame(actualStudent.getGroupName(), studentModel.getGroupName());
        assertTrue(actualStudent.getFaculty().equals(studentModel.getFaculty()));
        assertTrue(actualStudent.getSpeciality().equals(studentModel.getSpeciality()));
        assertEquals(studentModel.getLastName(), actualStudent.getLastName());
    }

    @Test
    void findStudentById_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actualStudent = mapper.entityToModel(repository.getById(any()));

        assertNotEquals(100, actualStudent.getAge());
        assertNotEquals("WRONG EMAIL", actualStudent.getEmail());
        assertNotSame(actualStudent.getGroupName(), GroupName.EMF_107);
        assertFalse(actualStudent.getFaculty().equals(Faculty.FACULTY_OF_ECONOMICS_AND_BUSINESS_ADMINISTRATION));
        assertFalse(!actualStudent.getSpeciality().equals(studentModel.getSpeciality()));
        assertNotEquals("WRONG PARAMETER", actualStudent.getLastName());
    }

    @Test
    void saveStudent_correctValuesTest() {
        when(repository.save(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actual = mapper.entityToModel(repository.save(any()));
        verify(repository, times(1)).save(mapper.modelToEntity(actual));
    }

    @Test
    void deleteStudent_correctValuesTest() {
        when(repository.getById(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actual = mapper.entityToModel(repository.getById(any()));
        studentService.save(actual);
        verify(repository, times(1)).save(mapper.modelToEntity(actual));

        repository.deleteById(actual.getId());
        verify(repository, times(1)).deleteById(actual.getId());
    }

    @Test
    void updateStudent_correctValuesTest() {
        when(repository.getById(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setSpeciality(Speciality.AIRCRAFT_ENGINES);
        actual.setPhone(380774433221L);
        actual.setLastName("UPDATED");

        when(mapper.entityToModel(any())).thenReturn(actual);

        StudentModel updated = mapper.entityToModel(any());

        assertTrue(updated.getLastName().equals(actual.getLastName()));
        assertEquals(actual.getPhone(), updated.getPhone());
        assertEquals(actual.getSpeciality(), updated.getSpeciality());
    }

    @Test
    void updateStudent_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setSpeciality(Speciality.AIRCRAFT_ENGINES);
        actual.setPhone(380774433221L);
        actual.setLastName("UPDATED");

        when(mapper.entityToModel(any())).thenReturn(actual);

        StudentModel updated = mapper.entityToModel(any());

        assertFalse(!updated.getLastName().equals(actual.getLastName()));
        assertNotEquals(87654321341L, updated.getPhone());
        assertNotEquals(Speciality.CIVIL_LAW_AND_PROCESSING, updated.getSpeciality());
    }

    @Test
    void loadUserByUserNameThrowsException_test() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(UsernameNotFoundException.class, () -> studentService.loadUserByUsername("null@gmail.com"));
    }

    @Test
    void existByEmail_correctValuesTest() {
        when(repository.existsByEmail(anyString())).thenReturn(true);

        boolean exists = repository.existsByEmail("test@gmail.com");
        assertEquals(true, exists);
    }

    @Test
    void signUpNewStudent_test() {
        when(repository.save(any())).thenReturn(student);
        when(mapper.entityToModel(any())).thenReturn(studentModel);

        StudentModel actual = mapper.entityToModel(repository.save(any()));
        verify(repository, times(1)).save(mapper.modelToEntity(actual));
    }

    @Test
    void findStudentByEmailThrowsException_test() {
        when(repository.findByEmail(anyString())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> studentService.findStudentByEmail("null@gmail.com"));
    }
}
