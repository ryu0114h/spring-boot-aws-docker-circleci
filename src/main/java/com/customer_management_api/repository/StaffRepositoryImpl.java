package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StaffRepositoryImpl implements StaffRepository {

    private final StaffMapper staffMapper;

    @Autowired
    public StaffRepositoryImpl(StaffMapper staffMapper) {
        this.staffMapper = staffMapper;
    }

    @Override
    public List<Staff> getStaffs(StaffSelector selector) {
        return staffMapper.getStaffs(selector);
    }

    @Override
    public Optional<Staff> getStaff(Long id) {
        return staffMapper.getStaff(id);
    }

    @Override
    public Optional<Staff> getStaffByEmail(String email) {
        return staffMapper.getStaffByEmail(email);
    }

    @Override
    public Staff createStaff(Staff staff) {
        staffMapper.createStaff(staff);
        return staff;
    }

    @Override
    public Staff updateStaff(Staff staff) {
        staffMapper.updateStaff(staff);
        return staff;
    }

    @Override
    public void deleteStaff(Long id) {
        staffMapper.deleteStaff(id);
    }
}
