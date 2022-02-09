package trainee.task.university.core.domain.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import trainee.task.university.core.database.entity.Faculty;
import trainee.task.university.core.database.entity.GroupName;
import trainee.task.university.core.database.entity.Speciality;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
public class StudentModel implements UserDetails {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private Long phone;
    private String email;
    private String password;
    private GroupName groupName;
    private Faculty faculty;
    private Speciality speciality;
    private Set<TimetableModel> timetables;
    private Set<GrantedAuthority> authoritySet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authoritySet;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
