package trainee.task.university.core.application.dto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Setter
@Getter
@ApiModel(value = "Login Student Request", description = "Request Model for student login")
public class LoginRequestDto {
    @Email
    private String username;
    private String password;
}
