package com.customer_management_api.controller;

import com.customer_management_api.entity.Register;
import com.customer_management_api.repository.StaffRepository;
import com.customer_management_api.service.JwtUserDetailsService;
import com.customer_management_api.service.RegisterService;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    protected final Log logger = LogFactory.getLog(getClass());

    private final StaffRepository staffRepository;
    private final RegisterService RegisterService;
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(StaffRepository staffRepository,
                                    RegisterService RegisterService,
                                    AuthenticationManager authenticationManager,
                                    JwtUserDetailsService userDetailsService,
                                    JwtTokenUtil jwtTokenUtil,
                                    PasswordEncoder passwordEncoder) {
        this.staffRepository = staffRepository;
        this.RegisterService = RegisterService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> createStore(
            @RequestBody @Validated({Register.CreateRegisterGroup.class}) Register register
    ) {
        Map<String, Object> responseMap = new HashMap<>();
        register.setPassword(passwordEncoder.encode(register.getPassword()));
        RegisterService.createRegister(register);
        UserDetails userDetails = userDetailsService.loadUserByUsername(register.getEmail());
        String token = jwtTokenUtil.generateToken(userDetails);
        responseMap.put("message", "Account created successfully");
        responseMap.put("token", token);
        return ResponseEntity.ok(responseMap);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginStaff(@RequestBody HashMap<String, String> body) {
        Map<String, Object> responseMap = new HashMap<>();

        try {
            String email = body.get("email");
            String password = body.get("password");
            Authentication auth =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            if (auth.isAuthenticated()) {
                logger.info("Logged In");
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                String token = jwtTokenUtil.generateToken(userDetails);
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
}