package com.customer_management_api.service;

import com.customer_management_api.entity.Register;
import com.customer_management_api.entity.Staff;
import com.customer_management_api.entity.Store;
import com.customer_management_api.repository.StaffRepository;
import com.customer_management_api.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final StoreRepository storeRepository;

    private final StaffRepository staffRepository;

    @Autowired
    public RegisterServiceImpl(StoreRepository storeRepository,
                               StaffRepository staffRepository) {
        this.storeRepository = storeRepository;
        this.staffRepository = staffRepository;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Register createRegister(Register Register) {
        Store store = new Store(Register.getName(), Register.getDescription());
        Store createdStore = storeRepository.createStore(store);

        Staff staff = new Staff(
                Register.getLastName(),
                Register.getFirstName(),
                Register.getBirthday(),
                Register.getEmail(),
                Register.getPassword(),
                createdStore.getId()
        );
        staffRepository.createStaff(staff);

        return Register;
    }
}
