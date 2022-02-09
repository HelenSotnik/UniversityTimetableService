package trainee.task.university.core.domain.service;

import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import trainee.task.university.core.database.entity.Lesson;
import trainee.task.university.core.database.repository.LessonRepository;
import trainee.task.university.core.domain.model.LessonModel;
import trainee.task.university.core.mapper.LessonMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;
    private final LessonMapper lessonMapper;

    public LessonService(LessonRepository lessonRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.lessonMapper = lessonMapper;
    }

    public List<LessonModel> findAllLessons(){
        List<Lesson> lessons = lessonRepository.findAll();
        return lessons.stream().map(lessonMapper::entityToModel).collect(Collectors.toList());
    }

    public LessonModel findLessonById(Long id) throws NotFoundException {
        return lessonRepository.findById(id).map(lessonMapper::entityToModel)
                .orElseThrow(()-> new NotFoundException(String.format("Lesson not found with id: " + id)));

    }

    public LessonModel save(LessonModel lessonModel) {
        Lesson lesson = lessonMapper.modelToEntity(lessonModel);
        return lessonMapper.entityToModel(lessonRepository.save(lesson));
    }

    public LessonModel update(Long id, LessonModel lessonModel) throws NotFoundException {
        lessonRepository.findById(id).map(lessonMapper::entityToDto)
                .orElseThrow(() -> new NotFoundException(String.format("Lesson not found with id: " + id)));
        Lesson lesson = lessonMapper.modelToEntity(lessonModel);
        lesson.setId(id);
        return lessonMapper.entityToModel(lessonRepository.save(lesson));
    }

    public void delete(Long id) throws NotFoundException {
        lessonRepository.findById(id).map(lessonMapper::entityToModel)
                .orElseThrow(() -> new NotFoundException(String.format("Lesson not found with id: " + id)));
        lessonRepository.deleteById(id);
    }
}
