package com.springboot.user.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springboot.user.entity.UserFollowers;
import com.springboot.user.payload.UserFollowersDTO;
import com.springboot.user.repository.UserFollowersRepository;
import com.springboot.user.repository.UserRepository;
import com.springboot.user.service.UserFollowersService;

@Service
public class UserFollowersServiceImpl implements UserFollowersService {

    private UserFollowersRepository userFollowersRepository;

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    public UserFollowersServiceImpl(UserRepository userRepository, ModelMapper modelMapper,
            UserFollowersRepository userFollowersRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userFollowersRepository = userFollowersRepository;
    }

    @Override
    public void followUser(UserFollowersDTO userFollowersDTO) {
        UserFollowers userFollowers = new UserFollowers();
        userFollowers.setUser(userRepository.findById(userFollowersDTO.getUserId())
                .orElseThrow(
                        () -> new IllegalArgumentException("User not found with id: " + userFollowersDTO.getUserId())));
        userFollowers.setFollowedUser(userRepository.findById(userFollowersDTO.getFollowedUserId())
                .orElseThrow(() -> new IllegalArgumentException(
                        "User not found with id: " + userFollowersDTO.getFollowedUserId())));
        userFollowersRepository.save(userFollowers);
    }

    @Override
    public void unfollowUser(Long followerID) {
        userFollowersRepository.deleteById(followerID);
    }

    @Override
    public List<UserFollowersDTO> getFollowers(Long userID) {
        List<UserFollowers> userFollowersList = userFollowersRepository.findByFollowedUserId(userID);

        List<UserFollowersDTO> response = userFollowersList.stream()
                .map((category) -> modelMapper.map(category, UserFollowersDTO.class))
                .collect(Collectors.toList());

        return response;
    }

}
