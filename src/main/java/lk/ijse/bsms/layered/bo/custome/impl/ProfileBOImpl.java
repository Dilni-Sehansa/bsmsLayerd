package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.ProfileBO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.OrderItemDAO;
import lk.ijse.bsms.layered.dao.custom.ProfileDAO;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.dto.ProfileDTO;
import lk.ijse.bsms.layered.entity.Customer;
import lk.ijse.bsms.layered.entity.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileBOImpl implements ProfileBO {

    ProfileDAO profileDAO = (ProfileDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.PROFILE);

    public ArrayList<ProfileDTO> getAll() throws SQLException, ClassNotFoundException {

        ArrayList<Profile> profiles = profileDAO.getAll();
        ArrayList<ProfileDTO> dtoList = new ArrayList<>();

        for (Profile profile : profiles) {
            dtoList.add(new ProfileDTO(
                    profile.getUserId(),
                    profile.getUserName(),
                    profile.getPassword(),
                    profile.getRole()
            ));
        }

        return dtoList;
    }

    public boolean save(ProfileDTO dto) throws SQLException, ClassNotFoundException {

        Profile profile = new Profile(
                dto.getUserId(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getRole()
        );

        return profileDAO.save(profile);
    }

    public boolean update(ProfileDTO dto) throws SQLException, ClassNotFoundException {

        Profile profile = new Profile(
                dto.getUserId(),
                dto.getUserName(),
                dto.getPassword(),
                dto.getRole()
        );

        return profileDAO.update(profile);
    }

    public  boolean delete(long userId) throws SQLException, ClassNotFoundException {
        return profileDAO.delete(userId);

    }

    public ProfileDTO search(long id) throws SQLException, ClassNotFoundException {

        Profile profile = profileDAO.search(id);

        if (profile == null) {
            return null;
        }

        return new ProfileDTO(
                profile.getUserId(),
                profile.getUserName(),
                profile.getPassword(),
                profile.getRole()
        );
    }
}
