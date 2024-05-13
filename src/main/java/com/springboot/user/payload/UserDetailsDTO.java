package com.springboot.user.payload;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsDTO {

    private String fullName;

    private Date dateOfBirth;

    private String gender;

    private String address;

    private String phoneNumber;

    private String profilePicture;

    private String biography;

    private String Location;
    private String email;
}
