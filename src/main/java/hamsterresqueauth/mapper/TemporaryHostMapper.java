package hamsterresqueauth.mapper;

import hamsterresqueauth.dto.user.TemporaryHostDto;
import hamsterresqueauth.model.TemporaryHost;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TemporaryHostMapper {

    TemporaryHostDto toHostDto(TemporaryHost host);

    Set<TemporaryHostDto> toHostDto (Set<TemporaryHost> hosts);
}
