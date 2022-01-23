package com.study.restfulwebservice.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminUserController {
    private UserDaoService service;

    public AdminUserController(UserDaoService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public MappingJacksonValue retrieveAllUsers() {
        List<User> users = service.findAll();

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", User.userInfo());

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    /*
    * API 버전 관리
    *
    * URI를 통한 관리
    * ReqParam을 이용한 관리
    * Header 를 이용한 관리
    */

    // /admin/users/1 -> /admin/v1/users/1
//    @GetMapping("/v1/users/{id}")
//    @GetMapping(value = "/users/{id}", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public MappingJacksonValue retrieveUserV1ByUri(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not find", id));
        }

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", User.userInfo());

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

//    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue retrieveUserV2ByUri(@PathVariable int id) {
        User user = service.findOne(id);

        if (user == null) {
            throw new UserNotFoundException(String.format("ID[%s] not find", id));
        }

        //User -> User2 로 카피
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user,userV2);
        userV2.setGrade("VIP");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", UserV2.userInfoV2());

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }

}
