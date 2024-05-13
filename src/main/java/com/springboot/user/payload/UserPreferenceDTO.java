package com.springboot.user.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserPreferenceDTO {

    private String language;

    private String notificationSetting;
    private String mn;

}
