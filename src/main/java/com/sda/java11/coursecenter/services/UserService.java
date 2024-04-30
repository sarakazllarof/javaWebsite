package com.sda.java11.coursecenter.services;

import com.sda.java11.coursecenter.dto.user.UserWrite;
import com.sda.java11.coursecenter.entities.User;
import com.sda.java11.coursecenter.repositories.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    protected @NonNull UserRepository userRepository;
    protected @NonNull PasswordEncoder encoder;


    @Override
    public String register(UserWrite user, String roles) {
        if (user == null || StringUtils.isBlank(user.getUsername()))
            return "Provide a valid username";

        User dbAdmin = userRepository.findByUsername(user.getUsername());
        if (dbAdmin != null)
            return String.format("Username '%s' is present on database", user.getUsername());

        if (StringUtils.isBlank(user.getPassword()))
            return "Password can not be null!";

        if (StringUtils.isBlank(user.getRepeatedPassword()))
            return "Repeated password can not be null!";

        if (!user.getRepeatedPassword().equals(user.getPassword()))
            return "Password and repeated password are not the same!";

        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setRoles(roles);
        newUser.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(newUser);

        return "User created successfully!";
    }

    @Override
    public User getByUserName(String userName) {
        return null;
    }

    @Override
    public String getRoles(String userName) {
        return null;
    }
}
