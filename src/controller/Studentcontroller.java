package controller;

import Db.DbConnection;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Studentcontroller {
    public JFXTextField txtemail;
    public JFXTextField txtsname;
    public JFXTextField txtSudentID;
    public JFXTextField txtcontact;
    public JFXTextField txtaddress;
    public JFXTextField txtnic;
    public TableView tblstudent;

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
    }

    public void btndelete(ActionEvent actionEvent) {
    }

    public void btnsearch(ActionEvent actionEvent) {
    }
}
