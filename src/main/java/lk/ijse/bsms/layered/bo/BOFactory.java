package lk.ijse.bsms.layered.bo;

import lk.ijse.bsms.layered.bo.custome.impl.*;

public class BOFactory {

    private static BOFactory instance;

    private BOFactory() {

    }
    public static BOFactory getInstance() {
        return instance == null ? instance = new BOFactory(): instance;
    }
    public enum BOType{
        CUSTOMER,
        CATEGORY,
        ITEM,
        RETURN,
        SUPPLIER,
        DASHBOADRD,
        ORDERITEM,
        ORDER,
        PROFILE,
        ORDERDETAILS
    }
    public SuperBO getBO(BOFactory.BOType boType){
        switch (boType){
            case CUSTOMER:
                return new CustomerBOImpl();
            case CATEGORY:
                return new CategoryBOImpl();
            case ITEM:
                return new ItemBOImpl();
                case RETURN:
                    return new ReturnBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case DASHBOADRD:
                return new DashboardBOImpl();
            case ORDERITEM:
                return new OrderItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
                case PROFILE:
                    return new ProfileBOImpl();
            case ORDERDETAILS:
                return new OrderDetailBOImpl();
            default:
                return null;
        }
    }
}
