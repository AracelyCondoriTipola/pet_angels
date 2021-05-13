package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.dto.person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class personController {
    @Autowired
    public DataSource dataSource;
    @GetMapping(path = "/person/{id_person}")
    public person findPersonById(@PathVariable Integer id_person) {
        person result = new person();
        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            ResultSet rs = stnt.executeQuery("select id_person, first_name, last_name, email, date_of_birth, " +
                    "phone_number,address,city  from person " +
                    "where id_person = + id_person");
            if (rs.next()) {
                result.first_name = rs.getString("first_name");
                result.last_name = rs.getString("last_name");
                result.email = rs.getString("email");
                result.date_of_birth = rs.getDate("date_of_brith");
                result.phone_number = rs.getString("phone_number");
                result.address = rs.getString("address");
                result.city = rs.getString("city");

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping (path = "/person")   // agregar
    public List<person> findAllPerson() {  //listar personas
        List<person>result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            ResultSet rs = stnt.executeQuery("select id_person, first_name, last_name, email, date_of_birth, " +
                    "phone_number,address,city  from person " +
                    "where id_person = + id_person");

            //
            while (rs.next()) {
                person person = new person();
                result.add(person);
 //               result.first_name = rs.getString("first_name");
 //               result.last_name = rs.getString("last_name");
 //               result.email = rs.getString("email");
 //               result.date_of_birth = rs.getDate("date_of_brith");
 //               result.phone_number = rs.getString("phone_number");
 //               result.address = rs.getString("address");
 //               result.city = rs.getString("city");
 //               result.add(person);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
    @PostMapping(path = "/person")
    public person createPerson(@RequestBody person person) {
        //validar los datos correctos.


        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            stnt.executeQuery ("INSERT INTO person VALUE ("
                    + person.id_person+", '"
                    + person.first_name+"', '"
                    + person.last_name+"', '"
                    + person.email+"', '"
                    + person.date_of_birth+"', '"
                    + person.phone_number+"', '"
                    + person.address+"', '"
                    + person.city+"')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return person;

    }

}
