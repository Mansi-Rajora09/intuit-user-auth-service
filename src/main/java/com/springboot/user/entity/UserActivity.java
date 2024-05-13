package com.springboot.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "UserActivities")
public class UserActivity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityID;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "UserID", nullable = false)
  private User user;

  @NotNull
  @NotEmpty
  @Column(name = "ActivityType", length = 50,nullable=false)
  private String activityType;

  @NotNull
  @NotEmpty
  @Column(name = "ActivityDescription", columnDefinition = "TEXT",nullable=false)
  private String activityDescription;

  @Column(name = "ActivityDate")
  private Date activityDate;

  @Column(name = "created_at", nullable = true, updatable = false)
  @CreationTimestamp
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at", nullable = true)
  private Date updatedAt;
}
