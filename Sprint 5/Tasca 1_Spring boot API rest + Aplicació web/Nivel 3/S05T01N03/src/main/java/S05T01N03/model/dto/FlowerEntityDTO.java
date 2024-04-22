package S05T01N03.model.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlowerEntityDTO {

    private String pk_flowerDTOID;
    private String flowerName;
    private String flowerCountry;
    private String flowerType;

    public FlowerEntityDTO(String pk_flowerDTOID, String flowerName, String flowerCountry, String flowerType) {
        this.pk_flowerDTOID = pk_flowerDTOID;
        this.flowerName = flowerName;
        this.flowerCountry = flowerCountry;
        this.flowerType = flowerType;
    }
}