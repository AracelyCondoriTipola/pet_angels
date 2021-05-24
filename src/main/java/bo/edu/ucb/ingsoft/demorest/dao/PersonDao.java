package bo.edu.ucb.ingsoft.demorest.dao;

import bo.edu.ucb.ingsoft.demorest.dto.PersonDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonDao {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private SequenceDao sequenceDao;

    public PersonDto crearPerson (PersonDto personDto) {

        personDto.setId_person(sequenceDao.getPrimayKeyForTable("person"));
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO person VALUES (?,?,?,?,?,?,?,?,?,?) ");
            stmt.setInt(1, personDto.getId_person());
            stmt.setInt(2, personDto.getId_geolocation());
            stmt.setString(3, personDto.getFirst_name());
            stmt.setString(4, personDto.getLast_name());
            stmt.setString(5, personDto.getEmail());
            stmt.setString(6, String.valueOf(personDto.getDate_of_birth()));
            stmt.setString(7, personDto.getPhone_number());
            stmt.setString(8, personDto.getAddress());
            stmt.setString(9, personDto.getCity());
            stmt.setInt(10, personDto.getStatus());


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException sqex) {
                    // No hacer nada intencionalemte;
                }
            }
        }
        return personDto;
    }

    public PersonDto findPersonById(Integer id_person) {

        PersonDto result = new PersonDto();

        try (Connection conn = dataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("SELECT id_person,id_geolocation,first_name,last_name,email,date_of_birth, phone_number,address, city, status FROM person " +
                    "where id_person = ?")
        ){ //TRY WITH RESOURCES
            pstmt.setInt(1, id_person);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setId_person(rs.getInt("id_person"));
                result.setId_geolocation(rs.getInt("id_geolocation"));
                result.setFirst_name(rs.getString("first_name"));
                result.setLast_name(rs.getString("last_name"));
                result.setEmail(rs.getString("email"));
                result.setDate_of_birth(rs.getDate("date_of_birth"));
                result.setPhone_number(rs.getString("phone_number"));
                result.setAddress(rs.getString("address"));
                result.setCity(rs.getString("city"));
                result.setStatus(rs.getInt("status"));
            } else { // si no hay valores de BBDD
                result = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<PersonDto> findAllPerson() {
        List<PersonDto> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("SELECT id_person,id_geolocation,first_name,last_name,email,date_of_birth, phone_number,address, city, status FROM person;");
            while (rs.next()) {
                PersonDto person = new PersonDto();
                person.setId_person(rs.getInt("id_person"));
                person.setId_geolocation(rs.getInt("id_geolocation"));
                person.setFirst_name(rs.getString("first_name"));
                person.setLast_name(rs.getString("last_name"));
                person.setEmail(rs.getString("email"));
                person.setDate_of_birth(rs.getDate("date_of_birth"));
                person.setPhone_number(rs.getString("phone_number"));
                person.setAddress(rs.getString("address"));
                person.setCity(rs.getString("city"));
                person.setStatus(rs.getInt("status"));

                result.add(person);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
/*
    public PersonDto findByLastName(String last_name) {
        PersonDto result1 = new PersonDto();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT id_person,id_geolocation,first_name,last_name,email,date_of_birth, phone_number,address, city FROM person " +
                     "where last_name=? ");
        ){ //TRY WITH RESOURCES
            pstmt.setString(1,last_name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result1.setId_person(rs.getInt("id_person"));
                result1.setId_geolocation(rs.getInt("id_geolocation"));
                result1.setFirst_name(rs.getString("first_name"));
                result1.setLast_name(rs.getString("last_name"));
                result1.setEmail(rs.getString("email"));
                result1.setDate_of_birth(rs.getDate("date_of_birth"));
                result1.setPhone_number(rs.getString("phone_number"));
                result1.setAddress(rs.getString("address"));
                result1.setCity(rs.getString("city"));
            } else { // si no hay valores de BBDD
                result1 = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result1;
    }

 */
}
