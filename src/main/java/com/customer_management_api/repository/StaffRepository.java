package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    List<Staff> getStaffs();

    Optional<Staff> getStaff(Long id);

    Optional<Staff> getStaffByEmail(String email);

    Staff createStaff(Staff staff);

    Staff updateStaff(Staff staff);

    void deleteStaff(Long id);
}
