package S05T01N03.controller;

import S05T01N03.model.dto.FlowerEntityDTO;
import S05T01N03.model.service.implementations.FlowerServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/flower")
public class FlowerController {
    @Autowired
    FlowerServiceImpl flowerSeviceImpl;

    // add
    @PostMapping("/clientAddFlower")
    @Operation(summary = "ADD NEW FLOWER", description = "This operation allows you to create a new flower")
    public Mono<FlowerEntityDTO> addFlowerEntityDTO(@RequestBody FlowerEntityDTO flowerEntityDTOToAdd){
        return flowerSeviceImpl.addFlowerEntityDTO(flowerEntityDTOToAdd);
    }

    // update
    @PutMapping("/clientUpdateFlower")
    @Operation(summary = "UPDATE FLOWER", description = "Provide id and values you want to update for an existing flower")
    public Mono<FlowerEntityDTO> updateFlowerEntityDTO (@RequestBody FlowerEntityDTO flowerEntityDTOUpdatedValues){
        return flowerSeviceImpl.updateFlowerEntityDTO(flowerEntityDTOUpdatedValues);
    }

    // delete
    @DeleteMapping("/clientDeleteFlower/{id}")
    @Operation(summary = "DELETE FLOWER", description = "Provide the id of the flower you want to delete")
    public Mono<Void> deleteFlowerEntityDTOById(@PathVariable("id") Integer id){
        return flowerSeviceImpl.deleteFlowerEntityDTOById(id);
    }

    // get one by id
    @GetMapping("/clientGetFlower/{id}")
    @Operation(summary = "GET A FLOWER BY ID", description = "Provide the id of the flower you want to visualize")
    public Mono<FlowerEntityDTO> getFlowerEntityDTOById (@PathVariable ("id") Integer id){
        return flowerSeviceImpl.getFlowerEntityDTOById(id);
    }

    // get all
    @GetMapping("/clientGetAllFlowers")
    @Operation(summary = "GET ALL STOCKED FLOWERS", description = "List all flowers at the BBDD")
    public Flux<FlowerEntityDTO> getAllFlowerEntityDTO(){
        return flowerSeviceImpl.getAllFlowersEntityDTO();
    }

}
