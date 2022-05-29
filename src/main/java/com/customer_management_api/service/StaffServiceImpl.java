package com.customer_management_api.service;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.repository.StaffRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    private final StaffRepository staffRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getStaffs() {
        return staffRepository.getStaffs();
    }

    @Override
    public Staff getStaff(Long id) {
        Optional<Staff> optionalStaff = staffRepository.getStaff(id);
        if (optionalStaff.isEmpty()) {
            throw new RuntimeException("店舗が存在しません。");
        }
        return optionalStaff.get();
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.createStaff(staff);
    }

    @Override
    public Staff updateStaff(Staff staff) {
        return staffRepository.updateStaff(staff);
    }

    @Override
    public void deleteStaff(Long id) {
        getStaff(id);
        staffRepository.deleteStaff(id);
    }
}
