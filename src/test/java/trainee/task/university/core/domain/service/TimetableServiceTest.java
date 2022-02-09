package trainee.task.university.core.domain.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import trainee.task.university.core.database.entity.DayOfTheWeek;
import trainee.task.university.core.database.entity.LessonType;
import trainee.task.university.core.database.entity.Timetable;
import trainee.task.university.core.database.repository.TimetableRepository;
import trainee.task.university.core.domain.model.ClassroomModel;
import trainee.task.university.core.domain.model.LessonModel;
import trainee.task.university.core.domain.model.TimetableModel;
import trainee.task.university.core.mapper.TimetableMapper;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TimetableServiceTest {

    @InjectMocks
    private TimetableService timetableService;

    @Mock
    private TimetableRepository repository;

    @Mock
    private TimetableMapper mapper;

    private Timetable timetable = new Timetable();
    private TimetableModel timetableModel = new TimetableModel();
    private LessonModel lessonModel = new LessonModel();
    private ClassroomModel classroomModel = new ClassroomModel();

    @BeforeEach
    private void timetableModelInitialization() {
        classroomModel.setId(400L);
        classroomModel.setBuildingName("Corpus 3");
        classroomModel.setClassroomNumber(400);

        lessonModel.setId(1L);
        lessonModel.setClassroom(classroomModel);
        lessonModel.setLessonType(LessonType.LECTURE);
        lessonModel.setSubjectName("Higher Mathematics");

        timetableModel.setId(400L);
        timetableModel.setDayOfTheWeek(DayOfTheWeek.MONDAY);
        timetableModel.setLessons(Collections.singletonList(lessonModel));
    }

    @Test
    void findAllTimetables_correctValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(timetable));
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        List<TimetableModel> actualTimetableList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertTrue(actualTimetableList.get(0).getId() == timetableModel.getId());
        assertEquals(timetableModel.getDayOfTheWeek(), actualTimetableList.get(0).getDayOfTheWeek());
        assertEquals(lessonModel.getLessonType(), actualTimetableList.get(0).getLessons().get(0).getLessonType());
        assertTrue(actualTimetableList.size() == 1);
    }

    @Test
    void findAllTimetables_incorrectValuesTest() {
        when(repository.findAll()).thenReturn(Collections.singletonList(timetable));
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        List<TimetableModel> actualTimetableList = repository.findAll()
                .stream()
                .map(mapper::entityToModel)
                .collect(Collectors.toList());

        assertFalse(actualTimetableList.get(0).getId() != timetableModel.getId());
        assertNotEquals(DayOfTheWeek.SUNDAY, actualTimetableList.get(0).getDayOfTheWeek());
        assertNotEquals(LessonType.PRACTICAL_LESSON, actualTimetableList.get(0).getLessons().get(0).getLessonType());
        assertFalse(actualTimetableList.size() != 1);
    }

    @Test
    void findTimetableById_correctValuesTest() {
        when(repository.getById(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actualTimetableModel = mapper.entityToModel(repository.getById(any()));

        assertTrue(actualTimetableModel.getId() == 400L);
        assertEquals(timetableModel.getDayOfTheWeek(), actualTimetableModel.getDayOfTheWeek());
        assertEquals(lessonModel.getLessonType(), actualTimetableModel.getLessons().get(0).getLessonType());
        assertTrue(timetableModel.getLessons().get(0).getSubjectName().equals(lessonModel.getSubjectName()));
        assertEquals(classroomModel.getClassroomNumber(),
                actualTimetableModel.getLessons().get(0).getClassroom().getClassroomNumber());
    }

    @Test
    void findTimetableById_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actualTimetableModel = mapper.entityToModel(repository.getById(any()));

        assertFalse(actualTimetableModel.getId() == 1L);
        assertNotEquals(DayOfTheWeek.SUNDAY, actualTimetableModel.getDayOfTheWeek());
        assertNotEquals(LessonType.PRACTICAL_LESSON, actualTimetableModel.getLessons().get(0).getLessonType());
        assertFalse(timetableModel.getLessons().get(0).getSubjectName().equals("WRONG PARAMETER"));
        assertNotEquals(90000,
                actualTimetableModel.getLessons().get(0).getClassroom().getClassroomNumber());
    }

    @Test
    void saveTimetable_correctValuesTest() {
        when(repository.save(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actualTimetableModel = mapper.entityToModel(repository.save(any()));

        assertTrue(actualTimetableModel.getId() == 400L);
        assertEquals(timetableModel.getDayOfTheWeek(), actualTimetableModel.getDayOfTheWeek());
        assertEquals(lessonModel.getLessonType(), actualTimetableModel.getLessons().get(0).getLessonType());
        assertTrue(timetableModel.getLessons().get(0).getSubjectName().equals(lessonModel.getSubjectName()));
        assertEquals(classroomModel.getClassroomNumber(),
                actualTimetableModel.getLessons().get(0).getClassroom().getClassroomNumber());
    }

    @Test
    void saveTimetable_incorrectValuesTest() {
        when(repository.save(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actualTimetableModel = mapper.entityToModel(repository.save(any()));

        assertFalse(actualTimetableModel.getId() == 1L);
        assertNotEquals(DayOfTheWeek.SUNDAY, actualTimetableModel.getDayOfTheWeek());
        assertNotEquals(LessonType.PRACTICAL_LESSON, actualTimetableModel.getLessons().get(0).getLessonType());
        assertFalse(timetableModel.getLessons().get(0).getSubjectName().equals("WRONG PARAMETER"));
        assertNotEquals(90000,
                actualTimetableModel.getLessons().get(0).getClassroom().getClassroomNumber());
    }

    @Test
    void updateTimetable_correctValuesTest() {
        when(repository.getById(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setId(1000L);
        actual.setDayOfTheWeek(DayOfTheWeek.FRIDAY);

        when(mapper.entityToModel(any())).thenReturn(actual);

        TimetableModel updatedClassroom = mapper.entityToModel(repository.save(any()));

        assertEquals(actual.getId(), updatedClassroom.getId());
        assertEquals(actual.getDayOfTheWeek(), updatedClassroom.getDayOfTheWeek());
    }

    @Test
    void updateTimetable_incorrectValuesTest() {
        when(repository.getById(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actual = mapper.entityToModel(repository.getById(any()));

        actual.setId(1000L);
        actual.setDayOfTheWeek(DayOfTheWeek.FRIDAY);

        when(mapper.entityToModel(any())).thenReturn(actual);

        TimetableModel updatedClassroom = mapper.entityToModel(repository.save(any()));

        assertFalse(!updatedClassroom.getId().equals(actual.getId()));
        assertNotEquals(DayOfTheWeek.THURSDAY, updatedClassroom.getDayOfTheWeek());
    }

    @Test
    void deleteTimetable_correctValuesTest() {
        when(repository.getById(any())).thenReturn(timetable);
        when(mapper.entityToModel(any())).thenReturn(timetableModel);

        TimetableModel actual = mapper.entityToModel(repository.getById(any()));
        timetableService.save(actual);
        verify(repository, times(1)).save(mapper.modelToEntity(actual));

        repository.deleteById(actual.getId());
        verify(repository, times(1)).deleteById(actual.getId());
    }
}