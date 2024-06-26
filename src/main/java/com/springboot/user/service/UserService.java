package com.springboot.user.service;

import com.springboot.user.payload.UserDetailsDTO;

public interface UserService {

    public void verifyUser(Long userId);

    public void addOrUpdateUserDetails(Long userId, UserDetailsDTO userDetailsDTO);

    public void deactivateAccount(Long userId);

    public Long getBonus(Long userId);

    public void increaseBonus(Long userId,String action);

}
