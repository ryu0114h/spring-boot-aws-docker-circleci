package com.customer_management_api.service;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
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
    public List<Staff> getStaffs(StaffSelector selector) {
        return staffRepository.getStaffs(selector);
    }

    @Override
    public Staff getStaff(Long id, Long storeId) {
        Optional<Staff> optionalStaff = staffRepository.getStaff(id, storeId);
        if (optionalStaff.isEmpty()) {
            throw new RuntimeException("スタッフが存在しません。");
        }
        return optionalStaff.get();
    }

    @Override
    public Staff createStaff(Staff staff) {
        Staff createdStaff = staffRepository.createStaff(staff);
        return getStaff(createdStaff.getId(), createdStaff.getStoreId());
    }

    @Override
    public Staff updateStaff(Staff staff) {
        Staff updatedStaff = staffRepository.updateStaff(staff);
        return getStaff(updatedStaff.getId(), updatedStaff.getStoreId());
    }

    @Override
    public void deleteStaff(Long id, Long storeId) {
        getStaff(id, storeId);
        staffRepository.deleteStaff(id);
    }
}
