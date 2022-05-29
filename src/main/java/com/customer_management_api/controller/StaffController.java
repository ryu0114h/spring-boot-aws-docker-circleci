package com.customer_management_api.controller;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.service.StaffService;
import java.util.List;
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

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @GetMapping
    public List<Staff> getStaffs() {
        return staffService.getStaffs();
    }

    @GetMapping("/{id}")
    public Staff getStaff(@PathVariable Long id) {
        return staffService.getStaff(id);
    }

    @PostMapping
    public Staff createStaff(@RequestBody @Validated({Staff.CreateStaffGroup.class}) Staff staff) {
        return staffService.createStaff(staff);
    }

    @PatchMapping("/{id}")
    public Staff updateStaff(@PathVariable Long id,
                             @RequestBody @Validated({Staff.UpdateStaffGroup.class}) Staff body) {
        Staff staff = staffService.getStaff(id);
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
    public void deleteStaff(@PathVariable Long id) {
        staffService.deleteStaff(id);
    }
}
