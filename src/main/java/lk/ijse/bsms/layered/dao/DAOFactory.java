package lk.ijse.bsms.layered.dao;

import lk.ijse.bsms.layered.dao.custom.QueryDAO;
import lk.ijse.bsms.layered.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    private  DAOFactory () {
    }
    public static DAOFactory getInstance() {
        return instance == null ? instance = new DAOFactory(): instance;
    }

    public enum DAOType{
        CUSTOMER,
        CATEGORY,
        ITEM,
        RETURN,
        SUPPLIER,
        DASHBOADRD,
        ORDERITEM,
        ORDER,
        PROFILE,
        ORDERDETAILS,
        QUERY
//        ORDERDETAILS,
//        QUARY
    }
    public SuperDAO getDAO(DAOType daoType){
        switch (daoType){
            case CUSTOMER:
                return new CustomerDAOImpl();
                case CATEGORY:
                    return new CategoryDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case RETURN:
                return new ReturnDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
                case ORDERITEM:
                return new OrderItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
                case PROFILE:
                    return new ProfileDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDAOImpl();
            default:
                return null;
        }
    }
}
