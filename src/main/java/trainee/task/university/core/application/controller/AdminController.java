package trainee.task.university.core.application.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import trainee.task.university.core.application.dto.AddStudentRequestDto;
import trainee.task.university.core.application.dto.StudentDto;
import trainee.task.university.core.application.facade.StudentFacade;

import javax.validation.Valid;

@Api("Admin Controller, saves and deletes users from database")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private StudentFacade studentFacade;

    @ApiOperation("Delete user from DB")
    @DeleteMapping("students/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws NotFoundException {
        studentFacade.delete(id);
    }

    @ApiOperation("Save new student to DB")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDto save(@RequestBody @Valid AddStudentRequestDto studentDto) {
        return studentFacade.save(studentDto);
    }
}
