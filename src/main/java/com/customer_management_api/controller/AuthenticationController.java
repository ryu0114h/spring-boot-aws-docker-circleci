package com.customer_management_api.controller;

import com.customer_management_api.entity.Staff;
import com.customer_management_api.repository.StaffRepository;
import com.customer_management_api.service.JwtUserDetailsService;
import com.customer_management_api.util.JwtTokenUtil;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());

    private final StaffRepository staffRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(StaffRepository staffRepository,
                                    AuthenticationManager authenticationManager,
                                    JwtUserDetailsService userDetailsService,
                                    JwtTokenUtil jwtTokenUtil,
                                    PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStaff(@RequestParam("email") String email,
                                        @RequestParam("password") String password) {
        Map<String, Object> responseMap = new HashMap<>();
        try {
            Authentication auth =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                String token = jwtTokenUtil.generateToken(userDetails);
                responseMap.put("error", false);
                responseMap.put("message", "Logged In");
                responseMap.put("token", token);
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("error", true);
                responseMap.put("message", "Invalid Credentials");
                return ResponseEntity.status(401).body(responseMap);
            }
        } catch (DisabledException e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Staff is disabled");
            return ResponseEntity.status(500).body(responseMap);
        } catch (BadCredentialsException e) {
            responseMap.put("error", true);
            responseMap.put("message", "Invalid Credentials");
            return ResponseEntity.status(401).body(responseMap);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("error", true);
            responseMap.put("message", "Something went wrong");
            return ResponseEntity.status(500).body(responseMap);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> createStaff(
            @RequestBody @Validated({Staff.CreateStaffGroup.class}) Staff staff
    ) {
        Map<String, Object> responseMap = new HashMap<>();
        staff.setPassword(passwordEncoder.encode(staff.getPassword()));
        staffRepository.createStaff(staff);
//        UserDetails userDetails = userDetailsService.loadUserByUsername(staff.getEmail());
//        String token = jwtTokenUtil.generateToken(userDetails);
        responseMap.put("error", false);
        responseMap.put("email", staff.getEmail());
        responseMap.put("message", "Account created successfully");
//        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }
}