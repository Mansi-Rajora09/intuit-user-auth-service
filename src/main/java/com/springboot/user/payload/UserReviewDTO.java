package com.springboot.user.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserReviewDTO {
    private Long userId;

    private Integer targetUserID;

    private int rating;
    private String reviewText;
}
