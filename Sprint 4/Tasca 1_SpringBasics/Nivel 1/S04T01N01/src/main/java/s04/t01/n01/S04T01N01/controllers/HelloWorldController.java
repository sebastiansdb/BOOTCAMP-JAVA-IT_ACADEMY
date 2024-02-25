package s04.t01.n01.S04T01N01.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class HelloWorldController {

    // Si no se recibe ningun valor para la variable "nom" a través de la petición del cliente (navegador web), dicha
    // variable asumirá el valor por defecto UNKNOWN
    @GetMapping("/HelloWorld")
    public String saluda(@RequestParam(defaultValue = "UNKNOWN") String name) {
        return "Hola, " + name.toUpperCase() + ". Estàs executant un projecte Maven.";
    }

    // Opcion 2 de "/HelloWorld"
    @GetMapping("/HelloWorldOption2")
    public String saludaOption2(@RequestParam Optional<String> name){
        return  "Hola, " + name.orElse("UNKNOWN").toUpperCase() + ". Estàs executant un projecte Maven.";
    }
    @GetMapping({"/HelloWorld2", "/HelloWorld2/{name}"})
    public String saluda2(@PathVariable(required = false) String name) {
        String ret = (name == null) ? "'no name given'" : name;
        return "Hola, " + ret.toUpperCase() + ". Estàs executant un projecte Maven.";
    }

    //                                                  FIN NIVEL 1


    /*
                                             EJEMPLOS de distintos parámetros de anotaciones
     */

    /*
     En este GET, al no tener el @RequestParam, podemos elegir si pasarle un parámetro (id) al hacer
     la petición o no. Otra manera de hacer lo mismo es colocar:
     - public String getexample1(@RequestParam(required = false) String id).

     Si no le pasamos parámetro en dicha petición, el valor por defecto que recibirá el método "getexample1" como
     parámetro, será "null". Caso contrario, el método recibirá un valor para "id" que será el especificado por el
     cliente
     */
    @GetMapping("/example1")
    @ResponseBody
    public String getexample1(String id) {
        return "ID: " + id;
    }
    /*
     Este GET, al tener @RequestParam definido de esta manera, requiere que si o si se le pase un parámetro al hacer
     la petición (id). Si no se lo pasamos al hacerla, el servidor arrojará un error (type=Bad Request, status=400)
     */
    @GetMapping("/example2")
    public String getexample2( @RequestParam String id) {
        return "ID: " + id;
    }

    /*
     Este GET, al tener @RequestParam definido de esta manera, requiere que si o si se le pase un parámetro al hacer
     la petición (id). Si no se lo pasamos al hacerla, el servidor arrojará un error (type=Bad Request, status=400)
     Además, el nombre que usaremos del lado del cliente para referenciar el parámetro en cuestión, será "id". Esto es
     porque hemos colocado (value = "id").
     "value" se denomina "atributo de la anotacion" (la anotación es @RequestParam, que corresponde al
     Spring MVC)
     */
    @GetMapping("/example3")
    public String getExample3( @RequestParam(value = "id") String userId) {
        return "ID: " + userId;
    }

}