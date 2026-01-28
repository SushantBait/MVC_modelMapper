package com.sprinboot.web_tutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    //DTO - Data Transfer layer - client to controller and service  they are between presentation layer and service layer

    private Long id;

    @NotNull(message= "Required field in Employee: name")
    @Size(min = 3,max = 10, message = "NUmber of character should be in the range: [3, 10]")
    private String name;

    @Email(message = "it should be valid email")
    private String email;

    @NotNull(message = "Age of the Employee cannot be blank")
    @Min(value =80, message = "Age can not be grater then 80")
    @Max(value =18, message = "Age can not be less then 18")
    private Integer age;

    @NotNull(message = "Role of the Employee cannot be blank")
    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of Employee can be USER or ADMIN")
    private String role;  //ADMIN, USER

    @NotNull(message = "Salary of Employee should not be null")
    @Positive(message = "Salary of Employee should not be positive")
    @Digits(integer = 6, fraction = 2, message = "The salary can be in the form XXXXX.XX")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "joiningDate field in Employee can not be future ")
    private LocalDate dateOfJoining;

    @JsonProperty("isActive")
    private Boolean isActive;

}
