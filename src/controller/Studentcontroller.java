package controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Student;
import view.tm.StudentTm;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Studentcontroller implements Initializable {
    public JFXTextField txtemail;
    public JFXTextField txtsname;
    public JFXTextField txtSudentID;
    public JFXTextField txtcontact;
    public JFXTextField txtaddress;
    public JFXTextField txtnic;
    public TableView<StudentTm> tblstudent;
    public TableColumn colnic;
    public TableColumn colsname;
    public TableColumn colemail;
    public TableColumn colcontact;
    public TableColumn coladdress;
    public TableColumn colStuid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        colStuid.setCellValueFactory(new PropertyValueFactory<>("StudentID"));
        colsname.setCellValueFactory(new PropertyValueFactory<>("Studentname"));
        colemail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("Address"));
       colnic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        loadAllStudent();

        tblstudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtSudentID.setText(newValue.getStudentID());
            txtsname.setText(newValue.getStudentname());
            txtemail.setText(newValue.getEmail());
            txtcontact.setText(newValue.getContact());
            txtaddress.setText(newValue.getAddress());
            txtnic.setText(newValue.getNic());
        });
    }


        public void btnsave(ActionEvent actionEvent)  {
        Student student =new Student(txtSudentID.getText(),txtsname.getText(),txtemail.getText(),txtcontact.getText(),txtaddress.getText(),txtnic.getText());
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO student" + " VALUES(?,?,?,?,?,?) ");
            preparedStatement.setObject(1,student.getStudentID());
            preparedStatement.setObject(2,student.getStudentname());
            preparedStatement.setObject(3,student.getEmail());
            preparedStatement.setObject(4,student.getContact());
            preparedStatement.setObject(5,student.getAddress());
            preparedStatement.setObject(6,student.getNic());

            int save = preparedStatement.executeUpdate();
            if (save > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved", ButtonType.OK).show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try again", ButtonType.OK).show();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnupdate(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE student set " + "student_name=?, email=?, contact=?,address=?, nic=?" + "WHERE  student_id=? ");
            preparedStatement.setObject(1,txtsname.getText());
            preparedStatement.setObject(2,txtemail.getText());
            preparedStatement.setObject(3,txtcontact.getText());
            preparedStatement.setObject(4,txtaddress.getText());
            preparedStatement.setObject(5,txtnic.getText());
            preparedStatement.setObject(6,txtSudentID.getText());

            int update= preparedStatement.executeUpdate();
            if (update>0){
                new Alert(Alert.AlertType.CONFIRMATION,"Updated",ButtonType.OK).show();
            }else {
                new Alert(Alert.AlertType.WARNING,"Try agian",ButtonType.OK).show();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btndelete(ActionEvent actionEvent) throws SQLException {
        Connection connection = null;
        try {
             connection = DbConnection.getInstance().getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM student WHERE student_id=? ");
        preparedStatement.setObject(1,txtSudentID.getText());
        int delete= preparedStatement.executeUpdate();
        if (delete>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Deleted",ButtonType.OK).show();
        }else {
            new Alert(Alert.AlertType.WARNING,"Try again",ButtonType.OK).show();
        }

    }

    public void btnsearch(ActionEvent actionEvent) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE student_id=? ");
            preparedStatement.setObject(1,txtSudentID.getText());

            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                txtsname.setText(resultSet.getString(2));
                txtemail.setText(resultSet.getString(3));
                txtcontact.setText(resultSet.getString(4));
                txtaddress.setText(resultSet.getString(5));
                txtnic.setText(resultSet.getString(6));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
    private void loadAllStudent() {
        ArrayList<Student> students = new ArrayList<>();
        ObservableList<StudentTm> objects = FXCollections.observableArrayList();

        try {
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Student");
            ResultSet rst = preparedStatement.executeQuery();

            while (rst.next()) {
                students.add(new Student(
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6))
                );
            }
            for (Student stu : students) {
                objects.add(new StudentTm(stu.getStudentID(), stu.getStudentname(), stu.getEmail(), stu.getContact(), stu.getAddress(), stu.getNic()));
            }
            tblstudent.setItems(objects);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
