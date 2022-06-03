package com.customer_management_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {

    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @Size(min = 0, max = 50, groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String name;

    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @Size(min = 0, max = 200, groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String description;

    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @Size(min = 0, max = 50, groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String lastName;

    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @Size(min = 0, max = 50, groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String firstName;

    @Email(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    @Size(min = 0, max = 50, groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    String password;

    @NotNull(groups = {CreateRegisterGroup.class, UpdateRegisterGroup.class})
    LocalDate birthday;

    public interface CreateRegisterGroup {
    }

    public interface UpdateRegisterGroup {
    }
}
