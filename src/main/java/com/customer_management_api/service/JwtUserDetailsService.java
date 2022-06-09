package com.customer_management_api.service;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.repository.StaffRepository;
import com.customer_management_api.security.LoginStaff;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    private final StaffRepository staffRepository;

    public JwtUserDetailsService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Staff> optionalStaff = staffRepository.getStaffByEmail(username);

        if (optionalStaff.isEmpty()) {
            throw new UsernameNotFoundException("ユーザが存在しません。");
        }

        Staff staff = optionalStaff.get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("STAFF_ROLE"));
        return new LoginStaff(staff, authorities);
    }
}
