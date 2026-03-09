package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dto.ProfileDTO;
import lk.ijse.bsms.layered.entity.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ProfileBO extends SuperBO {

    public ArrayList<ProfileDTO> getAll() throws SQLException, ClassNotFoundException;

    public boolean save(ProfileDTO user) throws SQLException, ClassNotFoundException;

    public boolean update(ProfileDTO user) throws SQLException, ClassNotFoundException;

    public  boolean delete(long userId) throws SQLException, ClassNotFoundException;

    public  ProfileDTO search(long id) throws SQLException, ClassNotFoundException;
}
