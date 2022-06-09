package com.customer_management_api.security;

import com.customer_management_api.entity.Staff;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Getter
@Setter
public class LoginStaff extends User {
    private final Staff staff;

    public LoginStaff(Staff staff, List<GrantedAuthority> authorities) {
        super(staff.getEmail(), staff.getPassword(), authorities);
        this.staff = staff;
    }
}
