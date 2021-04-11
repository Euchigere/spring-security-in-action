package com.euchigere.exercise5.serivce;

import com.euchigere.exercise5.models.Otp;
import com.euchigere.exercise5.models.User;
import com.euchigere.exercise5.repository.OtpRepository;
import com.euchigere.exercise5.repository.UserRepository;
import com.euchigere.exercise5.util.GenerateCodeUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final OtpRepository otpRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, OtpRepository otpRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.otpRepository = otpRepository;
    }

    public void addUser(User user) {
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        userRepository.save(user);
    }

    public void auth(User user) {
        // Searches for the user in the database
        Optional<User> o = userRepository.findUserByUsername(user.getUsername());
        if(o.isPresent()) {
            User u = o.get();
            // If the user exists, verifies its password
            // If the password is correct, generates a new OTP
            if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
                renewOtp(u);
            } else {
        // If the password is not correct or username doesn’t exist, throws an exception
                throw new BadCredentialsException("Bad credentials.");
            }
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    private void renewOtp(User u) {
        String code = GenerateCodeUtil.generateCode();
        // Searches the OTP by username
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());

        //If an OTP exists for this username, updates its value
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            otp.setCode(code);
        } else {
            Otp otp = new Otp();
            otp.setUsername(u.getUsername());
            otp.setCode(code);
            otpRepository.save(otp);
        }
    }

    public boolean check(Otp otpToValidate) {
        Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());

        // Searches the OTP by username
        if (userOtp.isPresent()) {
            Otp otp = userOtp.get();
            if (otpToValidate.getCode().equals(otp.getCode())) {
                return true;
            }
        }
        return false;
    }
}