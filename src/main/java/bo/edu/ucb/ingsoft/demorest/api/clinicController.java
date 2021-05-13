package bo.edu.ucb.ingsoft.demorest.api;

import bo.edu.ucb.ingsoft.demorest.dto.clinic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RestController
public class clinicController {

    @Autowired
    public DataSource dataSource;

    @GetMapping (path = "/clinic/{id_clinic}")  // buscar con el id
    public clinic findClinicById(@PathVariable Integer id_clinic) {
        clinic result = new clinic();

        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            ResultSet rs = stnt.executeQuery("select id_clinic, id_geolocalizacion, name_clinic," +
                    " email, phone_number, city, address " +
                    "from clinic where id_clinic =" + id_clinic); //FIXME SQL INJECTION !!! (NO ES SEGURO)

            //
            if (rs.next()) {
                result.id_clinic = rs.getInt("id_clinic");
                result.id_geolocalizacion = rs.getInt("id_geolocation");
                result.name_clinic = rs.getString("name_clinic");
                result.email = rs.getString("email");
                result.phone_number = rs.getString("phone_number");
                result.city = rs.getString("city");
                result.address = rs.getString("address");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @GetMapping (path = "/clinic")   // agregar
    public List<clinic> findAllClinic() {  //listar personas
        List<clinic>result = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            ResultSet rs = stnt.executeQuery("select id_clinic, id_geolocalizacion, name_clinic," +
                    " email, phone_number, city, address " +
                    "from clinic");

            //
            while (rs.next()) {
                clinic clinic = new clinic();
                clinic.id_clinic = rs.getInt("id_clinic");
                clinic.id_geolocalizacion = rs.getInt("id_geolocation");
                clinic.name_clinic = rs.getString("name_clinic");
                clinic.email = rs.getString("email");
                clinic.phone_number = rs.getString("phone_number");
                clinic.city = rs.getString("city");
                clinic.address = rs.getString("address");
                result.add(clinic);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @PostMapping (path = "/clinic")
    public clinic createClinic(@RequestBody clinic clinic) {
        //validar los datos correctos.


        try {
            Connection conn = dataSource.getConnection();
            Statement stnt = conn.createStatement();
            stnt.execute ("INSERT INTO clinic VALUE ("
                    + clinic.id_clinic+", '"
                    + clinic.id_geolocalizacion+"', '"
                    + clinic.name_clinic+"', '"
                    + clinic.email+"', '"
                    + clinic.phone_number+"', '"
                    + clinic.city+"', '"
                    + clinic.address+"')");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return clinic;
    }

}