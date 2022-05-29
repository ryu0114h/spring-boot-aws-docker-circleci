package com.customer_management_api.entity;

import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Staff {

    Long id;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50)
    String lastName;

    @NotBlank(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    @Size(min = 0, max = 50)
    String firstName;

    @NotNull(groups = {CreateStaffGroup.class, UpdateStaffGroup.class})
    LocalDateTime birthday;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public interface CreateStaffGroup {
    }

    public interface UpdateStaffGroup {
    }
}
