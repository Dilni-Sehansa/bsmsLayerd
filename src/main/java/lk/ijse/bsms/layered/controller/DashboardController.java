package lk.ijse.bsms.layered.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.bsms.layered.bo.BOFactory;
import lk.ijse.bsms.layered.bo.custome.DashboardBO;
import lk.ijse.bsms.layered.dto.DashboardDTO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML private Label lblProfit;
    @FXML private Label lblCustomers;
    @FXML private Label lblOrders;
    @FXML private Label lblCustomers1;
    @FXML private AreaChart<String, Number> salesChart;
    @FXML private StackPane dashbord_form;
    @FXML private Label lblDate;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getInstance().getBO(BOFactory.BOType.DASHBOADRD);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadDashboardData();
        initClock();
    }

    private void loadDashboardData() {
        try {

            double totalProfit = dashboardBO.getTotalProfit();
            int totalCustomers = dashboardBO.getCustomerCount();
            int totalOrders = dashboardBO.getOrderCount();
            int lowStock = dashboardBO.getLowStockCount();

            lblProfit.setText(String.format("Rs. %.2f", totalProfit));
            lblCustomers.setText(String.valueOf(totalCustomers));
            lblOrders.setText(String.valueOf(totalOrders));
            lblCustomers1.setText(String.valueOf(lowStock));

            loadChart();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadChart() throws SQLException, ClassNotFoundException {
        salesChart.getData().clear();
        XYChart.Series<String, Number> series = new XYChart.Series<>(); //table eka adina eka
        series.setName("Weekly Sales");

        Map<String, Double> chartData = dashboardBO.getSalesChartData(); //database
        for (Map.Entry<String, Double> entry : chartData.entrySet()) { //chart eke lakunukirima
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        salesChart.getData().add(series);
    }
    @FXML
    void logoutOnAction(MouseEvent event) {
        try {

            dashbord_form.getScene().getWindow().hide();

            Parent root = FXMLLoader.load(getClass().getResource("/view/LoginForm.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();

        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred while logging out!").show();
        }
    }
    private void initClock() {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {

            lblDate.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);// clock eka nawaththanna epa

        clock.play(); // clock eka wada karanna patan ganna
    }
}
