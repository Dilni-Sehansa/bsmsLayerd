package lk.ijse.bsms.layered.dto;

import java.io.Serializable;

public class DashboardDTO implements Serializable {
    double totalProfit;
    int totalCustomers;
    int totalOrders;
    int lowStockCount;

    public DashboardDTO() {
    }

    public DashboardDTO(double totalProfit, int totalCustomers, int totalOrders, int lowStockCount) {
        this.totalProfit = totalProfit;
        this.totalCustomers = totalCustomers;
        this.totalOrders = totalOrders;
        this.lowStockCount = lowStockCount;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }

    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }

    public int getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(int totalOrders) {
        this.totalOrders = totalOrders;
    }

    public int getLowStockCount() {
        return lowStockCount;
    }

    public void setLowStockCount(int lowStockCount) {
        this.lowStockCount = lowStockCount;
    }
}
