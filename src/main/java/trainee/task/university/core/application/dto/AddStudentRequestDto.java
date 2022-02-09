package trainee.task.university.core.application.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import trainee.task.university.core.database.entity.Faculty;
import trainee.task.university.core.database.entity.GroupName;
import trainee.task.university.core.database.entity.Speciality;

import javax.validation.constraints.*;
import java.util.List;

@Getter
@Setter
@ApiModel(value = "Add Student Request", description = "Request Model for creating student")
public class AddStudentRequestDto {

    @ApiModelProperty(value = "Student firstName", example = "Lena", required = true)
    @NotBlank
    private String firstName;

    @ApiModelProperty(value = "Student lastName", example = "Politova", required = true)
    @NotBlank
    private String lastName;

    @ApiModelProperty(value = "Student age", example = "18", required = true)
    @Min(15)
    @Max(100)
    @NotNull
    private Integer age;

    @ApiModelProperty(value = "Phone", example = "380501112244", required = true)
    @NotNull
    private Long phone;

    @ApiModelProperty(value = "Student email", example = "name@gmail.com", required = true)
    @Pattern(regexp = "([a-zA-Z0-9\\.]{1,30})\\@gmail[\\.]com", message = "Email has not valid form.")
    @Email
    @NotBlank
    private String email;

    @ApiModelProperty(value = "Student password", example = "Query1224", required = true)
    @Pattern(regexp = "([a-zA-Z0-9\\.]{6,30})", message = "Password not valid")
    @NotBlank
    private String password;

    @ApiModelProperty(value = "Student Group name", example = "FLA_109", required = true)
    @NotNull
    private GroupName groupName;

    @ApiModelProperty(value = "Student Faculty name", example = "MEF", required = true)
    @NotNull
    private Faculty faculty;

    @ApiModelProperty(value = "Student Speciality", example = "AERODROME_EQUIPMENT", required = false)
    private Speciality speciality;

    @ApiModelProperty(value = "Timetable for study week", required = false)
    private List<TimetableDto> timetables;
}
