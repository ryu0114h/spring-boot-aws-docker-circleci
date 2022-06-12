package com.customer_management_api.controller;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import com.customer_management_api.security.LoginStaff;
import com.customer_management_api.service.JwtUserDetailsService;
import com.customer_management_api.service.StaffService;
import com.customer_management_api.util.JwtTokenUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staffs")
public class StaffController {

    private final StaffService staffService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    public StaffController(StaffService staffService,
                           PasswordEncoder passwordEncoder,
                           JwtUserDetailsService userDetailsService,
                           JwtTokenUtil jwtTokenUtil) {
        this.staffService = staffService;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public List<Staff> getStaffs(@AuthenticationPrincipal LoginStaff loginStaff) {
        StaffSelector selector = new StaffSelector();
        Long storeId = loginStaff.getStaff().getStoreId();
        selector.setStoreId(storeId);
        return staffService.getStaffs(selector);
    }

    @GetMapping("/{id}")
    public Staff getStaff(@AuthenticationPrincipal LoginStaff loginStaff, @PathVariable Long id) {
        Long storeId = loginStaff.getStaff().getStoreId();
        return staffService.getStaff(id, storeId);
    }

    @PostMapping
    public Staff createStaff(@AuthenticationPrincipal LoginStaff loginStaff,
                             @RequestBody @Validated({Staff.CreateStaffGroup.class}) Staff staff) {
        Long storeId = loginStaff.getStaff().getStoreId();
        staff.setStoreId(storeId);
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));

        Staff createdStaff = staffService.createStaff(staff);

        Map<String, Object> responseMap = new HashMap<>();
        UserDetails userDetails = userDetailsService.loadUserByUsername(staff.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        responseMap.put("email", staff.getEmail());
        responseMap.put("message", "Account created successfully");
        responseMap.put("token", token);

        return createdStaff;
    }

    @PatchMapping("/{id}")
    public Staff updateStaff(@AuthenticationPrincipal LoginStaff loginStaff,
                             @PathVariable Long id,
                             @RequestBody @Validated({Staff.UpdateStaffGroup.class}) Staff body) {
        Long storeId = loginStaff.getStaff().getStoreId();
        Staff staff = staffService.getStaff(id, storeId);
        if (body.getLastName() != null) {
            staff.setLastName(body.getLastName());
        }
        if (body.getFirstName() != null) {
            staff.setFirstName(body.getFirstName());
        }
        if (body.getBirthday() != null) {
            staff.setBirthday(body.getBirthday());
        }
        return staffService.updateStaff(staff);
    }

    @DeleteMapping("/{id}")
    public void deleteStaff(@AuthenticationPrincipal LoginStaff loginStaff,
                            @PathVariable Long id) {
        Long storeId = loginStaff.getStaff().getStoreId();
        staffService.deleteStaff(id, storeId);
    }
}
