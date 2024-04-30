package com.sda.java11.coursecenter.security;

import com.sda.java11.coursecenter.dto.user.UserWrite;
import com.sda.java11.coursecenter.services.IUserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class CreateAdminCommandLineRunner implements CommandLineRunner {
    protected @NonNull IUserService userService;

    @Override
    public void run(String...args) throws Exception {
        UserWrite admin = UserWrite.builder()
                .username("admin")
                .password("&pass123")
                .repeatedPassword("&pass123")
                .build();
        String result = userService.register(admin, "admin");
        log.info(String.format("Admin creation resutl: %s", result));
    }
}
