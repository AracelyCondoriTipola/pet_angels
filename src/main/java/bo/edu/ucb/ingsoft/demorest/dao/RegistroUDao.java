package bo.edu.ucb.ingsoft.demorest.dao;

import bo.edu.ucb.ingsoft.demorest.dto.RegistroUDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class RegistroUDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;


    public RegistroUDto crearRegistroU (RegistroUDto registroU){
        registroU.setId_person(sequenceDao.getPrimayKeyForTable("person"));
        registroU.setId_owner(sequenceDao.getPrimayKeyForTable("owner"));
        registroU.setId_pet(sequenceDao.getPrimayKeyForTable("pet"));
        registroU.setId_pet_type(sequenceDao.getPrimayKeyForTable("pet_type"));
        registroU.setId_races(sequenceDao.getPrimayKeyForTable("races"));
        Connection conn = null;
        try{
            conn = dataSource.getConnection();

            PreparedStatement stmt = conn.prepareStatement("INSERT  INTO person value (?,?,?,?,?,?,?,?,?,?)");
            stmt.setInt(1,registroU.getId_person());
            stmt.setInt(2,registroU.getId_owner());
            stmt.setString(3,registroU.getFirst_name());
            stmt.setString(4, registroU.getLast_name());
            stmt.setString(5, registroU.getEmail());
            stmt.setString(6, String.valueOf(registroU.getDate_of_birth()));
            stmt.setString(7, registroU.getPhone_number());
            stmt.setString(8, registroU.getAddress());
            stmt.setString(9, registroU.getCity());
            stmt.setInt(10,registroU.getStatus());


            PreparedStatement stmt1 = conn.prepareStatement("INSERT INTO owner values(?,?,?,?,?,?)");
            stmt1.setInt(1,registroU.getId_owner());
            stmt1.setInt(2,registroU.getId_person());
            stmt1.setString(3, registroU.getUser_o());
            stmt1.setString(4, registroU.getPassword_o());
            stmt1.setInt(5, registroU.getStatus());


            PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO pet value (?,?,?,?,?,?)");
            stmt2.setInt(1,registroU.getId_pet());
            stmt2.setInt(2,registroU.getId_owner());
            stmt2.setString(3,registroU.getNamep());
            stmt2.setString(4, String.valueOf(registroU.getDate_of_birthp()));
            stmt2.setInt(5,registroU.getStatus());


            PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO pet_type value (?,?,?)");
            stmt3.setInt(1, registroU.getId_pet_type());
            stmt3.setInt(2, registroU.getId_pet());
            stmt3.setString(3,registroU.getType_pet());


            PreparedStatement stmt4 = conn.prepareStatement("INSERT INTO races value (?,?,?)");
            stmt4.setInt(1, registroU.getId_races());
            stmt4.setInt(2, registroU.getId_pet_type());
            stmt4.setString(3,registroU.getNamer());


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            if(conn != null){
                try{
                    conn.close();
                }catch (SQLException sqex){

                }
            }
        }
        return registroU;
    }
    public RegistroUDto findRegistroUById(Integer id_owner) {

        RegistroUDto result = new RegistroUDto();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select a.id_person, a.first_name, a.last_name, a.email, a.date_of_birth,a.phone_number,a.address,a.city, o.id_owner, o.user_o,o.password_o, p.name,p.sex, p.date_of_birth,pt.type_pet, r.name from person a join  owner o on a.id_person = o.id_person join pet p on o.id_owner = p.id_owner join pet_type pt on p.id_pet = pt.id_pet join races r on pt.id_pet_type = r.id_pet_type where o.id_owner = ?");
        //
        ){ //TRY WITH RESOURCES
            pstmt.setInt(1, id_owner);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setId_owner(rs.getInt("id_owner"));
                result.setFirst_name(rs.getString("first_name"));
                result.setLast_name(rs.getString("last_name"));
                result.setEmail(rs.getString("email"));
                result.setUser_o(rs.getString("user_o"));
                result.setPassword_o(rs.getString("password_o"));
                result.setDate_of_birth(rs.getDate("date_of_birth"));
                result.setPhone_number(rs.getString("phone_number"));
                result.setAddress(rs.getString("address"));
                result.setCity(rs.getString("city"));
                result.setNamep(rs.getString("name"));
                result.setSex(rs.getString("sex"));
                result.setDate_of_birthp(rs.getDate("date_of_birth"));
                result.setType_pet(rs.getString("type_pet"));
                result.setNamer(rs.getString("name"));
            } else {
                result = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<RegistroUDto> findAllRegistroU() {
        List<RegistroUDto> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {


          //  ResultSet rs = stmt.executeQuery("select a.id_person, a.first_name, a.last_name, a.email, a.date_of_birth, a.phone_number,a.address,a.city, o.id_owner, o.user_o,o.password_o from person a join owner o on a.id_person = o.id_person where id_owner = ?");
            ResultSet rs = stmt.executeQuery("Select id_person, first_name, last_name, email, date_of_birth, phone_number, address, city from person where id_owner=? ");

            while (rs.next()) {
                RegistroUDto registroU = new RegistroUDto();
                registroU.setId_owner(rs.getInt("id_owner"));
                registroU.setFirst_name(rs.getString("first_name"));
                registroU.setLast_name(rs.getString("last_name"));
                registroU.setEmail(rs.getString("email"));
                registroU.setUser_o(rs.getString("user_o"));
                registroU.setPassword_o(rs.getString("password_o"));
                registroU.setDate_of_birth(rs.getDate("date_of_brith"));
                registroU.setPhone_number(rs.getString("phone_number"));
                registroU.setAddress(rs.getString("address"));
                registroU.setCity(rs.getString("city"));

                result.add(registroU);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }


}
