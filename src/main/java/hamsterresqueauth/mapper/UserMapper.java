package hamsterresqueauth.mapper;

import hamsterresqueauth.dto.user.UserDto;
import hamsterresqueauth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "address", target = "addressDto")
    @Mapping(source = "loginInfo", target = "loginInfoDto")
    @Mapping(source = "contactInfo", target = "contactInfoDto")
    UserDto toUserDto(User host);

    Set<UserDto> toUserDto(Set<User> hosts);
}
