package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.bl.RegistroUBl;

import bo.edu.ucb.ingsoft.demorest.dto.ResponseDto;
import bo.edu.ucb.ingsoft.demorest.dto.RegistroUDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;

@RestController
public class RegistroUController {
    @Autowired
    public DataSource dataSource;
    @Autowired
    private RegistroUBl registroUBl;

    @GetMapping(path = "/registro/{id_owner}")
    public ResponseDto findRegistroUById(@PathVariable Integer id_owner) {
        RegistroUDto registroUDto = registroUBl.findRegistroUById(id_owner);
        if (registroUDto != null) {
            return new ResponseDto( true, registroUDto, null);
        } else {
            return new ResponseDto( false, null, "No existe el registro con este codigo:");
        }
    }


    @GetMapping(path = "/registro")
    public ResponseDto finAllRegistroU(){
        return new ResponseDto(true, registroUBl.findAllRegistroU(), null);
    }


    @PostMapping(path = "/registro")
    public RegistroUDto crearRegistroU(@RequestBody RegistroUDto registroU) {

        return registroUBl.crearRegistroU(registroU);

    }




}
