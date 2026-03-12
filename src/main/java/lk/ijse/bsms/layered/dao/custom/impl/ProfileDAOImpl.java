package lk.ijse.bsms.layered.dao.custom.impl;

import lk.ijse.bsms.layered.dao.custom.ProfileDAO;
import lk.ijse.bsms.layered.dto.ProfileDTO;
import lk.ijse.bsms.layered.dao.CRUDUtil;
import lk.ijse.bsms.layered.entity.Profile;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfileDAOImpl implements ProfileDAO {

    public ArrayList<Profile> getAll() throws SQLException, ClassNotFoundException {

        ResultSet rs = CRUDUtil.execute("SELECT * FROM user");
        ArrayList<Profile> userList = new ArrayList<>();

        while (rs.next()) {
            userList.add(new Profile(
                    rs.getLong("userId"),
                    rs.getString("userName"),
                    rs.getString("password"),
                    rs.getString("role")
            ));
        }

        return userList;
    }



    public boolean save(Profile entity) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute(
                "INSERT INTO user (userName, password, role) VALUES (?, ?, ?)",
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole()
        );
    }

    public boolean update(Profile entity) throws SQLException, ClassNotFoundException {

        return CRUDUtil.execute(
                "UPDATE user SET userName = ?, password = ?, role = ? WHERE userId = ?",
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getUserId()
        );
    }

    public  boolean delete(long userId) throws SQLException, ClassNotFoundException {
        return CRUDUtil.execute("DELETE FROM user WHERE userId = ?", userId);

    }


    @Override
    public boolean exit(long id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public Profile search(long id) throws SQLException, ClassNotFoundException {

        ResultSet rst = CRUDUtil.execute("SELECT * FROM user WHERE userId = ?", id);

        if (rst.next()) {
            return new Profile(
                    rst.getLong("userId"),
                    rst.getString("userName"),
                    rst.getString("password"),
                    rst.getString("role")
            );
        }

        return null;
    }

    @Override
    public List<Long> getAllIds() throws SQLException, ClassNotFoundException {
        return List.of();
    }
}
