package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.bl.PetBl;

import bo.edu.ucb.ingsoft.demorest.dto.PetDto;
import bo.edu.ucb.ingsoft.demorest.dto.ResponseDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
public class PetController {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private PetBl petBl;

    @GetMapping(path = "/pet/{id_pet}")
    public ResponseDto findPetsById(@PathVariable Integer id_pet) {
        PetDto pet = petBl.findPetsById(id_pet);
        if (pet != null) {
            return new ResponseDto( true, pet, null);
        } else {
            return new ResponseDto( false, null, "No existe el usuario con este codigo:");
        }
    }


    @GetMapping(path = "/pet")
    public ResponseDto finAllPets(){
        return new ResponseDto(true, petBl.findAllPets(), null);
    }


    @PostMapping(path = "/pet")
    public ResponseDto crearPets(@RequestBody PetDto pet) {
        if (pet.getName() == null || pet.getName().trim().equals("")) {  // nombre: "     "
            return new ResponseDto( false, null, "El nombre debe ser obligatorio");
        }

        if (pet.getSex() == null || pet.getSex().trim().equals("")) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido debe ser obligatorio" );
            return new ResponseDto( false, null, "El sexo de la mascota debe ser obligatorio");
        }

        return new ResponseDto(true, petBl.crearPets(pet), null);
    }

}
