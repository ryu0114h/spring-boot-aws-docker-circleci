package com.customer_management_api.repository;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.StaffSelector;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StaffMapper {

    List<Staff> getStaffs(StaffSelector selector);

    Optional<Staff> getStaff(Long id, Long storeId);

    Optional<Staff> getStaffByEmail(String email);

    int createStaff(Staff staff);

    int updateStaff(Staff staff);

    int deleteStaff(Long id);
}
