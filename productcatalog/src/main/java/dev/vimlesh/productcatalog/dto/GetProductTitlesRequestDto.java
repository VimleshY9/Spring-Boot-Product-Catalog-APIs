package dev.vimlesh.productcatalog.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetProductTitlesRequestDto {
    private List<String> uuids;
}
