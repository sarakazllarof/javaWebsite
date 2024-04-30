package com.sda.java11.coursecenter.services;


import com.sda.java11.coursecenter.dto.user.UserWrite;
import com.sda.java11.coursecenter.entities.User;

public interface IUserService {
    String register(UserWrite user, String roles);
    User getByUserName(String userName);
    String getRoles(String userName);
}
