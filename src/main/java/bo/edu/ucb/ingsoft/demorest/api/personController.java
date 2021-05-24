package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.bl.PersonBl;
import bo.edu.ucb.ingsoft.demorest.dto.PersonDto;
import bo.edu.ucb.ingsoft.demorest.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
public class PersonController {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private PersonBl personaBl;

    @GetMapping(path = "/person/{id_persona}")
    public ResponseDto findPersonById(@PathVariable Integer id_persona) {
        PersonDto person = personaBl.findPersonById(id_persona);
        if (person != null) {
            return new ResponseDto( true, person, null);
        } else {
            return new ResponseDto( false, null, "No existe la persona con codigo:");
        }
    }
/*
    @GetMapping(path = "/person/{last_name}")
    public ResponseDto findByLastName(@PathVariable String last_name) {
        PersonDto person = personaBl.findByLastName(last_name);
        if (person != null) {
            return new ResponseDto( true, person, null);
        } else {
            return new ResponseDto( false, null, "No existe la persona con codigo:");
        }
    }
*/
    @GetMapping(path = "/person")
    public ResponseDto findAllPerson() {
        return new ResponseDto( true, personaBl.findAllPerson(), null);
    }

    @PostMapping(path = "/person")
    public ResponseDto crearPerson(@RequestBody PersonDto persona) {
        // Validar que los datos enviados son correctos.
        if (persona.getFirst_name() == null || persona.getFirst_name().trim().equals("")) {  // nombre: "     "
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre debe ser obligatorio" );
            return new ResponseDto( false, null, "El nombre debe ser obligatorio");
        }

        if (persona.getLast_name() == null || persona.getFirst_name().trim().equals("")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido debe ser obligatorio" );
            return new ResponseDto( false, null, "El apellido debe ser obligatorio");
        }

        return new ResponseDto(true, personaBl.crearPerson(persona), null);
    }

}