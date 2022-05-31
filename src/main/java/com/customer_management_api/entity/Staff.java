package com.customer_management_api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Staff {

    Long id;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String lastName;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String firstName;

    @Email(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50, groups = {Store.CreateStoreGroup.class, Store.UpdateStoreGroup.class})
    String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    String password;

    @NotNull(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    LocalDate birthday;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public interface CreateStaffGroup {
    }

    public interface UpdateStaffGroup {
    }
}
