package trainee.task.university.core.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import trainee.task.university.core.database.entity.Classroom;
import trainee.task.university.core.database.repository.ClassroomRepository;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.mapper.ClassroomMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClassroomServiceTest {

    @InjectMocks
    private ClassroomService classroomService;

    @Mock
    private ClassroomRepository repository;

    @Mock
    private ClassroomMapper mapper;

    private Classroom classroom = new Classroom();
    private ClassroomModel classroomModel = new ClassroomModel();

    @BeforeEach
    private void classroomModelInitialization() {
        classroomModel.setId(400L);
        classroomModel.setBuildingName("Corpus 3");
        classroomModel.setClassroomNumber(400);
    }

    @Test
    void findAllClassrooms_correctValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(classroom));
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        List<ClassroomModel> actualClassroomList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertTrue(actualClassroomList.get(0).getId() == 400L);
        assertEquals("Corpus 3", actualClassroomList.get(0).getBuildingName());
        assertEquals(400, actualClassroomList.get(0).getClassroomNumber());
        assertTrue(actualClassroomList.size() == 1);
    }

    @Test
    void findAllClassrooms_incorrectValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(classroom));
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        List<ClassroomModel> actualClassroomList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertFalse(actualClassroomList.size() != 1);
        assertNotEquals("FALSE PARAMETER", actualClassroomList.get(0).getBuildingName());
        assertFalse(actualClassroomList.get(0).getClassroomNumber() != 400);
        assertFalse(actualClassroomList.get(0).getId() != 400L);
        assertNotEquals(6000000L, actualClassroomList.get(0).getId());
    }

    @Test
    void findClassroomById_correctValuesTest() {
        when(repository.getById(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel classroomModel = mapper.entityToModel(repository.getById(any()));

        assertEquals("Corpus 3", classroomModel.getBuildingName());
        assertEquals(400, classroomModel.getClassroomNumber());
        assertTrue(classroomModel.getId() == 400L);
    }

    @Test
    void findClassroomById_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel classroomModel = mapper.entityToModel(repository.getById(any()));

        assertNotEquals("WRONG PARAMETER", classroomModel.getBuildingName());
        assertNotEquals(600000, classroomModel.getClassroomNumber());
        assertFalse(classroomModel.getId() != 400L);
        assertFalse(classroomModel.getId() == 709000000L);
    }

    @Test
    void saveClassroom_correctValuesTest() {
        when(repository.save(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel classroomModel = mapper.entityToModel(repository.save(any()));

        assertEquals("Corpus 3", classroomModel.getBuildingName());
        assertEquals(400, classroomModel.getClassroomNumber());
        assertTrue(classroomModel.getId() == 400L);
    }

    @Test
    void saveClassroom_incorrectValuesTest() {
        when(repository.save(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel classroomModel = mapper.entityToModel(repository.save(any()));

        assertNotEquals("WRONG PARAMETER", classroomModel.getBuildingName());
        assertNotEquals(600000, classroomModel.getClassroomNumber());
        assertFalse(classroomModel.getId() != 400L);
        assertFalse(classroomModel.getId() == 709000000L);
    }

    @Test
    void updateClassroom_correctValuesTest() {
        when(repository.getById(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setClassroomNumber(100);
        actual.setBuildingName("Main Building");
        actual.setId(100L);

        when(mapper.entityToModel(any())).thenReturn(actual);

        ClassroomModel updatedClassroom = mapper.entityToModel(repository.save(any()));
        assertEquals(actual.getId(), updatedClassroom.getId());
        assertEquals(actual.getClassroomNumber(), updatedClassroom.getClassroomNumber());
        assertTrue(updatedClassroom.getBuildingName().equals(actual.getBuildingName()));
    }

    @Test
    void updateClassroom_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setClassroomNumber(100);
        actual.setBuildingName("Main Building");
        actual.setId(100L);

        when(mapper.entityToModel(any())).thenReturn(actual);

        ClassroomModel updatedClassroom = mapper.entityToModel(repository.save(any()));
        assertNotEquals(500000L, updatedClassroom.getId());
        assertNotEquals(28282, updatedClassroom.getClassroomNumber());
        assertFalse(updatedClassroom.getBuildingName().equals("WRONG PARAMETER"));
        assertFalse(!updatedClassroom.getBuildingName().equals(actual.getBuildingName()));
    }

    @Test
    void deleteClassroom_correctValuesTest() {
        when(repository.getById(any())).thenReturn(classroom);
        when(mapper.entityToModel(any())).thenReturn(classroomModel);

        ClassroomModel actual = mapper.entityToModel(repository.getById(any()));
        classroomService.save(actual);
        verify(repository,times(1)).save(mapper.modelToEntity(actual));

        repository.deleteById(actual.getId());
        verify(repository, times(1)).deleteById(actual.getId());
    }
}