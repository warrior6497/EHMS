package Controller;

import Model.Address;
import Model.Patient;
import Model.Therapist;
import Util.MessageAlerter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPatientController implements Initializable,Util.JavafxPaneHandler {

    @FXML private TextField TextFieldFirstName;
    @FXML private TextField TextFieldLastName;
    @FXML private TextField TextFieldCity;
    @FXML private TextField TextFieldContactNum;
    @FXML private ChoiceBox<?> Choice3Digits;
    @FXML private TextField TextFieldStreet;
    @FXML private TextField TextFieldHouseNum;
    @FXML private Button BtnClear;
    @FXML private Button BtnSave;
    @FXML private ChoiceBox<?> ChoiceNurse;
    @FXML private Label LblNurseID;
    @FXML private TextField TextFieldPatientID;

    DBH.patientDAO pbh = new DBH.patientDAO();
    ArrayList<Patient> list = new ArrayList<Patient>();
    MessageAlerter ma = new MessageAlerter();

    @FXML
    void OnClickBtnClear(ActionEvent event) {
        TextFieldPatientID.setText("");
        TextFieldFirstName.setText("");
        TextFieldLastName.setText("");
        TextFieldContactNum.setText("");
        TextFieldCity.setText("");
        TextFieldStreet.setText("");
        TextFieldHouseNum.setText("");

        String MessageInformation = "All Fields Cleared" ;
        ma.MessageWithoutHeader("Cleared", MessageInformation);
    }

    @FXML
    void OnClickBtnSave(ActionEvent event) throws SQLException {
        String MessageInformation = "" ;

        if(TextFieldPatientID.getLength()==0){
            MessageInformation += "You Have To Choose Patient To Edit :) \n" ;
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else if((TextFieldFirstName.getLength() == 0) || (TextFieldLastName.getLength() == 0)  || (TextFieldContactNum.getLength()==0) || (TextFieldCity.getLength() == 0) || (TextFieldStreet.getLength() == 0)  || (TextFieldHouseNum.getLength() == 0)){
            MessageInformation += "Messing Information : \n" ;
            if(TextFieldFirstName.getLength() == 0)  MessageInformation +=  "* First Name \n";
            if(TextFieldLastName.getLength() == 0)  MessageInformation += "* Last Name \n";
            if(TextFieldContactNum.getLength() == 0) MessageInformation += "* Contact Number \n";
            if(TextFieldCity.getLength() == 0)  MessageInformation += "* City \n";
            if(TextFieldStreet.getLength() == 0)  MessageInformation += "* Street \n";
            if(TextFieldHouseNum.getLength() == 0)  MessageInformation += "* House Number \n";
            ma.ShowErrorMessage("Unexpected Error", "Missing Information", MessageInformation);
        }
        else {
            MessageInformation += "Patient Edited Successfully :)" ;
            list = pbh.selectAll();
            for(Patient p : list) {
                if(p.getID().equals(TextFieldPatientID.getText())) {
                    p.setName(TextFieldFirstName.getText() + " " + TextFieldLastName.getText());
                    String ContactNum = TextFieldContactNum.getText();
                    p.setContactNo(ContactNum);
                    Address address = new Address(TextFieldCity.getText(), TextFieldStreet.getText(), Integer.parseInt(TextFieldHouseNum.getText()));
                    p.setAddress(address);
                    pbh.UpdatePatient(p);
                }
            }
            ma.MessageWithoutHeader("Added", MessageInformation);
        }


    }

    //Overrided by implementing Initializable
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML public void onEnter(ActionEvent ae) throws SQLException {
        String id =TextFieldPatientID.getText();

        list = pbh.selectAll();

        for (Patient p : list) {
            if(p.getID().equals(id)){
                TextFieldFirstName.setText(p.getFirstName());
                TextFieldLastName.setText(p.getLasttName());
                TextFieldContactNum.setText(p.getContactNo());
                TextFieldCity.setText(p.getAddress().getCity());
                TextFieldStreet.setText(p.getAddress().getStreet());
                TextFieldHouseNum.setText(Integer.toString(p.getAddress().getHouseNum()));
            }

        }
    }

    //Overrided by implementing JavafxPaneHandler
    @Override
    public void JavafxTableFill() {

    }

    @Override
    public void JavafxChoiceFill() {

    }

    @Override
    public void JavafxDiagramFill() {

    }
}
