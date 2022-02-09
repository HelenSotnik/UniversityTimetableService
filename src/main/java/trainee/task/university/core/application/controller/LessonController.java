package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.core.application.dto.LessonDto;
import trainee.task.university.core.application.facade.LessonFacade;

import javax.validation.Valid;
import java.util.List;

@Api("Lessons Controller")
@RestController
@RequestMapping("/lessons")
public class LessonController {

    @Autowired
    private LessonFacade lessonFacade;

    @ApiOperation("Find All Lessons method, returns list of all lessons")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LessonDto> findAll() {
        return lessonFacade.findAllLessons();
    }

    @ApiOperation("Returns Lesson Info found by Lesson Id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public LessonDto findLessonById(@PathVariable Long id) throws NotFoundException {
        return lessonFacade.findLessonById(id);
    }

    @ApiOperation("Deletes Lesson found by Lesson Id from Database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        lessonFacade.delete(id);
    }

    @ApiOperation("Saves new Lesson to Database")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto save(@RequestBody @Valid LessonDto lessonDto) {
        return lessonFacade.save(lessonDto);
    }

    @ApiOperation("Updates Lesson info found by Lesson Id")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public LessonDto update(@PathVariable Long id, @RequestBody @Valid LessonDto lessonDto)
            throws NotFoundException {
        return lessonFacade.update(id, lessonDto);
    }
}
