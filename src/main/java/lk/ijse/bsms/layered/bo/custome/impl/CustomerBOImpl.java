package lk.ijse.bsms.layered.bo.custome.impl;

import lk.ijse.bsms.layered.bo.custome.CustomerBO;
import lk.ijse.bsms.layered.dao.DAOFactory;
import lk.ijse.bsms.layered.dao.custom.CustomerDAO;
import lk.ijse.bsms.layered.dto.CustomerDTO;
import lk.ijse.bsms.layered.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.CUSTOMER);

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getCusId(), customerDTO.getCusName(), customerDTO.getPhone(), customerDTO.getAddress(), customerDTO.getEmail()));
    }

    @Override
    public CustomerDTO searchCustomer(Long customerId) throws SQLException, ClassNotFoundException {
        Customer customer =customerDAO.search(Long.parseLong(Long.toString(customerId)));
        CustomerDTO customerDTO = new CustomerDTO(customer.getCusId(),customer.getCusName(),customer.getPhone(),customer.getAddress(),customer.getEmail());
        return customerDTO;
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getPhone(),customerDTO.getEmail(),customerDTO.getAddress()));
    }

    @Override
    public boolean deleteCustomer(Long customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(Long.parseLong(Long.toString(customerId)));

    }

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException, ClassNotFoundException{
        ArrayList<Customer> customers= customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTO=new ArrayList<>();
        for (Customer customer : customers) {
            customerDTO.add(new CustomerDTO(customer.getCusId(),customer.getCusName(),customer.getPhone(),customer.getAddress(),customer.getEmail()));   }
        return customerDTO;
    }

    @Override
    public List<Long> getAllCustomerIds() throws SQLException, ClassNotFoundException {

        return customerDAO.getAllIds();
    }
}
