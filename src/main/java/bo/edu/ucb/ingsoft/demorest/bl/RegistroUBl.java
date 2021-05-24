package bo.edu.ucb.ingsoft.demorest.bl;

import bo.edu.ucb.ingsoft.demorest.dao.RegistroUDao;
import bo.edu.ucb.ingsoft.demorest.dto.RegistroUDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistroUBl {
    @Autowired
    RegistroUDao registroUDao;

    public RegistroUDto crearRegistroU(RegistroUDto registro){

        return  registroUDao.crearRegistroU(registro);
    }

    public RegistroUDto findRegistroUById(Integer id_owner) {

        return registroUDao.findRegistroUById(id_owner);
    }

    public List<RegistroUDto> findAllRegistroU() {

        return registroUDao.findAllRegistroU();
    }



}
