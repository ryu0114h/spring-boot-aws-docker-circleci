package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import java.util.List;
import java.util.Optional;

public interface StaffRepository {

    List<Staff> getStaffs(StaffSelector selector);

    Optional<Staff> getStaff(Long id, Long storeId);

    Optional<Staff> getStaffByEmail(String email);

    Staff createStaff(Staff staff);

    Staff updateStaff(Staff staff);

    void deleteStaff(Long id);
}
