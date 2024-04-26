package S05T01N03.model.service.implementations;

import S05T01N03.exceptions.ResourceNotFoundException;
import S05T01N03.message.ResponseMessage;
import S05T01N03.model.dto.FlowerEntityDTO;
import S05T01N03.model.service.interfaces.FlowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Objects;

@Service
public class FlowerServiceImpl implements FlowerService {
    @Autowired
    private WebClient flowerWebClient;  // Realiza la conexion con el servidor (la API del nivel 2, en este caso)

    // add flower
    @Override
    public Mono<FlowerEntityDTO> addFlowerEntityDTO(FlowerEntityDTO flowerEntityDTOToAdd) {
        return flowerWebClient  // Realiza la conexion con el servidor (la API del nivel 2, en este caso)
                .post()// Operacion que se realiza
                .uri("/flower/add")  // Endpoint en la api que estoy llamando
                .bodyValue(flowerEntityDTOToAdd)// parámetro que le paso al endpoint
                .retrieve()// metodo para recibir el resultado de la API llamada
                /*
                            ParameterizedTypeReference se utiliza en WebClient para indicar cómo se debe deserializar la
                            respuesta del servidor en un objeto ResponseEntity<ResponseMessage<FlowerEntityDTO>>. Esto se
                            necesita porque WebClient necesita información adicional sobre el tipo de respuesta para realizar
                            la deserialización correctamente.
                */
                // Convierte el resultado obtenido a un objeto tipo Mono
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<FlowerEntityDTO>>() {})
                // getData() es un metodo de la clase ResponseMessage, la cual pertenece a este proyecto. Se usa para obtener la
                // entidad FlowerEntityDTO, ya que dicvha entidad está encapsulada en un objeto clase en ResponseMessage
                .map(ResponseMessage::getData);
    }

    // update flower
    @Override
    public Mono<FlowerEntityDTO> updateFlowerEntityDTO (FlowerEntityDTO flowerEntityDTOUpdatedValues) {
        return flowerWebClient
                .put()
                .uri("/flower/update")
                .bodyValue(flowerEntityDTOUpdatedValues)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<ResponseMessage<FlowerEntityDTO>>() {})
                .map(ResponseMessage::getData)
                .onErrorResume(throwable -> {
                    // Manejo de excepciones aquí
                    if (throwable instanceof WebClientResponseException.NotFound) {
                        // Si se produce una excepción de recurso no encontrado (404), devuelvo una
                        // ResourceNotFoundException, que es personalizada
                        throw new ResourceNotFoundException("The flower", "id", flowerEntityDTOUpdatedValues.getPk_flowerDTOID());
                    } else {
                        // Manejar otros tipos de excepciones aquí, como lanzar otra excepción personalizada.
                            /*
                                Si el servidor devuelve una respuesta con un código de estado de error
                                (por ejemplo, 500 Internal Server Error), esto
                                podría provocar una excepción que entre aquí.
                             */
                        return Mono.error(throwable);
                    }
                });
    }

    // delete flower
    @Override
    public  Mono<Void> deleteFlowerEntityDTOById (Integer id){
        if (id == null){
            // Tambien podria personalizar una excpecion para este caso
            return Mono.error(new IllegalArgumentException("Id must not be null"));
        } else {
            return flowerWebClient
                    .delete()
                    .uri("/flower/delete/{id}", id)
                    .retrieve()
                    .toBodilessEntity()
                    .onErrorResume(throwable -> {
                        // Manejo de excepciones aquí
                        if (throwable instanceof WebClientResponseException.NotFound) {
                            // Si se produce una excepción de recurso no encontrado (404), devuelvo una
                            // ResourceNotFoundException, que es personalizada
                            throw new ResourceNotFoundException("The flower", "id", id);
                        } else {
                            // Manejar otros tipos de excepciones aquí, como lanzar otra excepción personalizada.
                            /*
                                Si el servidor devuelve una respuesta con un código de estado de error
                                (por ejemplo, 500 Internal Server Error), esto
                                podría provocar una excepción que entre aquí.
                             */
                            return Mono.error(throwable);
                        }
                    })
                    /*
                    El método then() en Reactor (el marco de programación reactiva utilizado en Spring WebFlux) se
                    utiliza para indicar que se realizará una tarea después de completarla tarea actual, sin necesidad
                    de devolver ningún valor. Aqui se usa después de la llamada a toBodilessEntity() para
                    indicar que la operación de eliminación se realizará sin devolver ningún resultado al cliente.
                     */
                    .then();
        }
    }

    // get one by id
    @Override
    public Mono<FlowerEntityDTO> getFlowerEntityDTOById (Integer id) {
        if (id == null){
            return Mono.error(new ResourceNotFoundException("The flower", "id", id));
        } else {
            return flowerWebClient
                    .get()
                    .uri("/flower/getOne/{id}", id)
                    .retrieve()
                    /*
                        ParameterizedTypeReference se utiliza en WebClient para indicar cómo se debe deserializar la
                        respuesta del servidor en un objeto ResponseEntity<ResponseMessage<FlowerEntityDTO>>. Esto se
                        necesita porque WebClient necesita información adicional sobre el tipo de respuesta para realizar
                        la deserialización correctamente.
                    */
                    .bodyToMono(new ParameterizedTypeReference<ResponseMessage<FlowerEntityDTO>>() {})
                    .map(ResponseMessage::getData)
                    .onErrorResume(throwable -> {
                        // Manejo de excepciones aquí
                        if (throwable instanceof WebClientResponseException.NotFound) {
                            // Si se produce una excepción de recurso no encontrado (404), devuelvo una
                            // ResourceNotFoundException, que es personalizada
                            throw new ResourceNotFoundException("The flower", "id", id);
                        } else {
                            // Manejar otros tipos de excepciones aquí, como lanzar otra excepción personalizada.
                            /*
                                Si el servidor devuelve una respuesta con un código de estado de error
                                (por ejemplo, 500 Internal Server Error), esto
                                podría provocar una excepción que entre aquí.
                             */
                            return Mono.error(throwable);
                        }
                    });
        }
    }

    // get all
    @Override
    public Flux<FlowerEntityDTO> getAllFlowersEntityDTO () {
        ResponseEntity<ResponseMessage<List<FlowerEntityDTO>>> responseEntity =
                flowerWebClient.get()
                        .uri("/flower/getAll") // Ruta del endpoint
                        .retrieve()
                        .toEntity(new ParameterizedTypeReference<ResponseMessage<List<FlowerEntityDTO>>>() {
                        })
                        .block();
        // Me aseguro que la lista de lfores no sea nula
        if (responseEntity == null) throw new AssertionError("La Lista de flores es 'null'");
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            /*
                El metodo "fromIterable()" de la clase Flux crea un flujo de elementos a partir de una lista

                Si el cuerpo de la respuesta (getBody()) es nulo, la llamada a "Objects.requireNonNull()" generará una
                excepción NullPointerException. Esto ocurrirá porque Objects.requireNonNull() intenta garantizar que el
                objeto que se pasa como argumento no sea nulo. Si el objeto es nulo, lanzará una excepción NullPointerException.
             */
            return Flux.fromIterable(Objects.requireNonNull(responseEntity.getBody()).getData()); // Retorna el Flux con los FlowerEntityDTO.
        } else {
            // Manejo cualquier error aquí. Podria una excepción o retornar un Flux vacío (como en este caso)
            return Flux.empty();
        }
    }
    
}