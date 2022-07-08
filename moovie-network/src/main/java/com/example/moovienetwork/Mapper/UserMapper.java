package com.example.moovienetwork.Mapper;

import com.example.moovienetwork.Dto.UserDto;
import com.example.moovienetwork.Model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MovieMapper.class)
public interface UserMapper {

    User dtoToEntity(UserDto userDto);
    UserDto entityToDto(User user);

    List<User> dtoToEntity(List<UserDto> userDtos);
    List<UserDto> entityToDto(List<User> users);
}
