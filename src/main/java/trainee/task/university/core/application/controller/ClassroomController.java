package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.core.application.dto.ClassroomDto;
import trainee.task.university.core.application.facade.ClassroomFacade;

import javax.validation.Valid;
import java.util.List;

@Api("Classroom Controller")
@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    @Autowired
    private ClassroomFacade classroomFacade;

    @ApiOperation("Find All Classrooms method,returns list of classrooms")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClassroomDto> findAll() {
        return classroomFacade.findAllClassrooms();
    }

    @ApiOperation("Returns information about classroom by id")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClassroomDto findClassroomById(@PathVariable Long id) throws NotFoundException {
        return classroomFacade.findClassroomById(id);
    }

    @ApiOperation("Delete classroom from Database")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        classroomFacade.delete(id);
    }

    @ApiOperation("Saves new Classroom to Database")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClassroomDto save(@RequestBody @Valid ClassroomDto classroomDto) {
        return classroomFacade.save(classroomDto);
    }

    @ApiOperation("Update information about classroom by its id")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ClassroomDto update(@PathVariable Long id, @RequestBody @Valid ClassroomDto classroomDto)
            throws NotFoundException {
        return classroomFacade.update(id, classroomDto);
    }
}
