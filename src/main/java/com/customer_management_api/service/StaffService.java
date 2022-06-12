package com.customer_management_api.service;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import java.util.List;

public interface StaffService {

    List<Staff> getStaffs(StaffSelector selector);

    Staff getStaff(Long id, Long storeId);

    Staff createStaff(Staff staff);

    Staff updateStaff(Staff staff);

    void deleteStaff(Long id, Long storeId);
}
