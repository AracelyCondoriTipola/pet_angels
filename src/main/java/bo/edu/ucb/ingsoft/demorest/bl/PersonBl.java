package bo.edu.ucb.ingsoft.demorest.bl;

import bo.edu.ucb.ingsoft.demorest.dao.PersonDao;
import bo.edu.ucb.ingsoft.demorest.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonBl {
    @Autowired
    PersonDao personDao;

    public PersonDto crearPerson(PersonDto person) {

        return personDao.crearPerson(person);
    }

    public PersonDto findPersonById(Integer id_person) {

        return personDao.findPersonById(id_person);
    }

    public List<PersonDto> findAllPerson() {
        return personDao.findAllPerson();
    }
/*
    public PersonDto findByLastName(String last_name) {
        return personDao.findByLastName(last_name);
    }

 */
}

