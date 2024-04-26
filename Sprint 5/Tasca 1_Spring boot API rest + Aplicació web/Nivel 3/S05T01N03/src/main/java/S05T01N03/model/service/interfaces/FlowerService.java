package S05T01N03.model.service.interfaces;


import S05T01N03.model.dto.FlowerEntityDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface FlowerService {
    Mono<FlowerEntityDTO> addFlowerEntityDTO (FlowerEntityDTO flowerEntityDTOTOAdd);
    Mono<FlowerEntityDTO> updateFlowerEntityDTO (FlowerEntityDTO flowerEntityDTOUpdatedValues);
    Mono<Void> deleteFlowerEntityDTOById (Integer pk_flowerDTOID);
    Mono<FlowerEntityDTO> getFlowerEntityDTOById (Integer id);
    Flux<FlowerEntityDTO> getAllFlowersEntityDTO ();
}
