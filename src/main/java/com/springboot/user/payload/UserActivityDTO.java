package com.springboot.user.payload;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserActivityDTO {

    private Long userId;

    @NotNull
    private String activityType;
    @NotNull
    private String activityDescription;

}
