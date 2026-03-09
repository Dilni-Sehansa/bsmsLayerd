package lk.ijse.bsms.layered.bo.custome;

import lk.ijse.bsms.layered.bo.SuperBO;
import lk.ijse.bsms.layered.dto.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {

    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public CustomerDTO searchCustomer(Long customerId) throws SQLException, ClassNotFoundException;

    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    public boolean deleteCustomer(Long customerId) throws SQLException, ClassNotFoundException;

    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException ;

    public List<Long> getAllCustomerIds() throws SQLException, ClassNotFoundException;
}
