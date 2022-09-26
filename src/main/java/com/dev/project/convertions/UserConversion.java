package com.dev.project.convertions;

import com.dev.project.domain.User;
import com.dev.project.dto.UserTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import static org.springframework.beans.BeanUtils.copyProperties;


@Component
public class UserConversion {


    public UserTO convert(User user) {

        return new UserTO(user.getId(), user.getToken(), user.getName(),
                user.getEmail(), user.getStatus(),user.getGoogleAuth());
    }

    public User convert(UserTO dto) {
        User user = new User();
        copyProperties(dto, user);
        return user;
    }

    public List<UserTO> convert(List<User> users) {
        return users.stream().parallel().map(this::convert).collect(Collectors.toList());
    }
}
