package com.study.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.filterOutAllExcept;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password", "ssn"}) // 해당 속성을 class 단위에서 제어
//@JsonFilter("UserInfo")
public class User {

    private Integer id;

    /* javax validation */
    @Size(min = 2, message = "Name은 2글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;

    // @JsonIgnore 필드값 제어
    private String password;
    private String ssn;

    public static SimpleBeanPropertyFilter userInfo() {
        return SimpleBeanPropertyFilter.filterOutAllExcept("id","name","joinDate", "ssn");
    }

}
