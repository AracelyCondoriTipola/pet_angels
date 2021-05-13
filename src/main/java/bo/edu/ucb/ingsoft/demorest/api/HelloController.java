package bo.edu.ucb.ingsoft.demorest.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping(path = "/hola")
    public String helloWord(){
        return "Hola Mundo";
    }
}
