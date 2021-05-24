package bo.edu.ucb.ingsoft.demorest.dao;

import bo.edu.ucb.ingsoft.demorest.dto.PetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PetDao {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private SequenceDao sequenceDao;

    public PetDto crearPets (PetDto pet){
        pet.setId_pet(sequenceDao.getPrimayKeyForTable("pet"));
        Connection conn = null;
        try{
            conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT  INTO pet value (?,?,?,?)");
            stmt.setInt(1, pet.getId_pet());
            stmt.setString(2, pet.getName());
            stmt.setString(3, pet.getSex());
            stmt.setString(4, String.valueOf(pet.getDate_of_birth()));


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
        return pet;
    }
    public PetDto findPetsById(Integer id_pet) {

        PetDto result = new PetDto();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("select p.id_pet,p.id_owner,p.name,p.sex,p.date_of_birth from pet p WHERE id_pet=?");
        ){ //TRY WITH RESOURCES
            pstmt.setInt(1, id_pet);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                result.setId_pet(rs.getInt("id_pet"));
                result.setId_owner(rs.getInt("id_owner"));
                result.setName(rs.getString("name"));
                result.setSex(rs.getString("sex"));
                result.setDate_of_birth(rs.getDate("date_of_birth"));

            } else {
                result = null;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public List<PetDto> findAllPets() {
        List<PetDto> result = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery("select p.id_pet,p.id_owner,p.name,p.sex,p.date_of_birth from pet p");
            while (rs.next()) {
                PetDto pet= new PetDto();
                pet.setId_pet(rs.getInt("id_pet"));
                pet.setId_owner(rs.getInt("id_owner"));
                pet.setName(rs.getString("name"));
                pet.setSex(rs.getString("sex"));
                pet.setDate_of_birth(rs.getDate("date_of_birth"));

                result.add(pet);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return result;
    }



}


