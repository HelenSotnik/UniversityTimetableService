package trainee.task.university.core.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import trainee.task.university.core.database.entity.Lesson;
import trainee.task.university.core.database.entity.LessonType;
import trainee.task.university.core.database.repository.LessonRepository;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.domain.model.LessonModel;
import trainee.task.university.core.mapper.LessonMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LessonServiceTest {

    @InjectMocks
    private LessonService lessonService;

    @Mock
    private LessonRepository lessonRepository;

    @Mock
    private LessonMapper mapper;

    private Lesson lesson = new Lesson();
    private LessonModel lessonModel = new LessonModel();

    @BeforeEach
    private void lessonModelInitialization() {
        ClassroomModel classroomModel = new ClassroomModel();
        classroomModel.setId(400L);
        classroomModel.setBuildingName("Corpus 3");
        classroomModel.setClassroomNumber(400);

        lessonModel.setId(1L);
        lessonModel.setClassroom(classroomModel);
        lessonModel.setLessonType(LessonType.LECTURE);
        lessonModel.setSubjectName("Higher Mathematics");
    }

    @Test
    void findAllLessons_correctValuesTest() {
        when(lessonRepository.findAll()).thenReturn(Collections.singletonList(lesson));
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        List<LessonModel> actualLessonList = lessonRepository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertTrue(actualLessonList.get(0).getId() == 1L);
        assertEquals(lessonModel.getLessonType(), actualLessonList.get(0).getLessonType());
        assertEquals(lessonModel.getClassroom().getClassroomNumber(), actualLessonList.get(0).getClassroom().getClassroomNumber());
        assertTrue(actualLessonList.size() == 1);
        assertTrue(actualLessonList.get(0).getSubjectName().equals(lessonModel.getSubjectName()));
    }

    @Test
    void findAllLessons_incorrectValuesTest() {
        when(lessonRepository.findAll()).thenReturn(Collections.singletonList(lesson));
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        List<LessonModel> actualLessonList = lessonRepository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertFalse(actualLessonList.get(0).getId() != 1L);
        assertNotEquals(LessonType.LABORATORY_WORK, actualLessonList.get(0).getLessonType());
        assertNotEquals(7000000, actualLessonList.get(0).getClassroom().getClassroomNumber());
        assertFalse(actualLessonList.size() != 1);
        assertFalse(!actualLessonList.get(0).getSubjectName().equals(lessonModel.getSubjectName()));
    }

    @Test
    void findLessonById_correctValuesTest() {
        when(lessonRepository.getById(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actualLessonModel = mapper.entityToModel(lessonRepository.getById(any()));

        assertTrue(actualLessonModel.getId() == 1L);
        assertEquals(lessonModel.getLessonType(), actualLessonModel.getLessonType());
        assertEquals(lessonModel.getSubjectName(), actualLessonModel.getSubjectName());
        assertTrue(actualLessonModel.getClassroom().getBuildingName().equals(lessonModel.getClassroom().getBuildingName()));
        assertEquals(lessonModel.getClassroom().getClassroomNumber(), actualLessonModel.getClassroom().getClassroomNumber());
    }

    @Test
    void findLessonById_incorrectValuesTest() {
        when(lessonRepository.getById(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actualLessonModel = mapper.entityToModel(lessonRepository.getById(any()));

        assertFalse(actualLessonModel.getId() != 1L);
        assertNotEquals(LessonType.PRACTICAL_LESSON, actualLessonModel.getLessonType());
        assertNotEquals("WRONG PARAMETER", actualLessonModel.getSubjectName());
        assertTrue(!actualLessonModel.getClassroom().getBuildingName().equals("WRONG PARAMETER"));
        assertNotEquals(900000, actualLessonModel.getClassroom().getClassroomNumber());
    }

    @Test
    void saveLesson_correctValuesTest() {
        when(lessonRepository.save(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actualLessonModel = mapper.entityToModel(lessonRepository.save(any()));

        assertTrue(actualLessonModel.getId() == 1L);
        assertEquals(lessonModel.getLessonType(), actualLessonModel.getLessonType());
        assertEquals(lessonModel.getSubjectName(), actualLessonModel.getSubjectName());
        assertTrue(actualLessonModel.getClassroom().getBuildingName().equals(lessonModel.getClassroom().getBuildingName()));
        assertEquals(lessonModel.getClassroom().getClassroomNumber(), actualLessonModel.getClassroom().getClassroomNumber());
    }

    @Test
    void saveLesson_incorrectValuesTest() {
        when(lessonRepository.save(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actualLessonModel = mapper.entityToModel(lessonRepository.save(any()));

        assertFalse(actualLessonModel.getId() != 1L);
        assertNotEquals(LessonType.PRACTICAL_LESSON, actualLessonModel.getLessonType());
        assertNotEquals("WRONG PARAMETER", actualLessonModel.getSubjectName());
        assertTrue(!actualLessonModel.getClassroom().getBuildingName().equals("WRONG PARAMETER"));
        assertNotEquals(900000, actualLessonModel.getClassroom().getClassroomNumber());
    }

    @Test
    void updateLesson_correctValuesTest() {
        when(lessonRepository.getById(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actual = mapper.entityToModel(lessonRepository.getById(any()));

        ClassroomModel newClassroomModel = new ClassroomModel();
        newClassroomModel.setId(200L);
        newClassroomModel.setBuildingName("New Classroom");
        newClassroomModel.setClassroomNumber(200);

        actual.setId(3L);
        actual.setClassroom(newClassroomModel);
        actual.setLessonType(LessonType.PRACTICAL_LESSON);
        actual.setSubjectName("Chemistry");

        when(mapper.entityToModel(any())).thenReturn(actual);

        LessonModel updatedLessonModel = mapper.entityToModel(lessonRepository.save(any()));
        assertEquals(actual.getId(), updatedLessonModel.getId());
        assertEquals(actual.getClassroom().getClassroomNumber(), updatedLessonModel.getClassroom().getClassroomNumber());
        assertTrue(updatedLessonModel.getClassroom().getBuildingName().equals(actual.getClassroom().getBuildingName()));
        assertEquals(actual.getSubjectName(), updatedLessonModel.getSubjectName());
        assertEquals(actual.getLessonType(), updatedLessonModel.getLessonType());
    }

    @Test
    void updateLesson_incorrectValuesTest() {
        when(lessonRepository.getById(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actual = mapper.entityToModel(lessonRepository.getById(any()));

        ClassroomModel newClassroomModel = new ClassroomModel();
        newClassroomModel.setId(200L);
        newClassroomModel.setBuildingName("New Classroom");
        newClassroomModel.setClassroomNumber(200);

        actual.setId(3L);
        actual.setClassroom(newClassroomModel);
        actual.setLessonType(LessonType.PRACTICAL_LESSON);
        actual.setSubjectName("Chemistry");

        when(mapper.entityToModel(any())).thenReturn(actual);

        LessonModel updatedLessonModel = mapper.entityToModel(lessonRepository.save(any()));

        assertFalse(!updatedLessonModel.getId().equals(actual.getId()));
        assertNotEquals(900000, updatedLessonModel.getClassroom().getClassroomNumber());
        assertFalse(!updatedLessonModel.getClassroom().getBuildingName().equals(actual.getClassroom().getBuildingName()));
        assertNotEquals("WRONG PARAMETER", updatedLessonModel.getSubjectName());
        assertNotEquals(LessonType.LECTURE, updatedLessonModel.getLessonType());
    }

    @Test
    void deleteLesson_correctValuesTest() {
        when(lessonRepository.getById(any())).thenReturn(lesson);
        when(mapper.entityToModel(any())).thenReturn(lessonModel);

        LessonModel actual = mapper.entityToModel(lessonRepository.getById(any()));
        lessonService.save(actual);
        verify(lessonRepository,times(1)).save(mapper.modelToEntity(actual));

        lessonRepository.deleteById(actual.getId());
        verify(lessonRepository, times(1)).deleteById(actual.getId());
    }
}
