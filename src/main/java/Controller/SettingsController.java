package Controller;

import Util.CssFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlendMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {
    ObservableList BlendModelist = FXCollections.observableArrayList();
    ObservableList Fontlist = FXCollections.observableArrayList();

    static boolean flagtoggle = false; //false == light

    @FXML private Pane SettingPanel;
    @FXML private Pane TopPanel;
    @FXML private TabPane TablPaneThemeDesign;
    @FXML private ColorPicker ColorPickerThemeDesgin;
    @FXML private ColorPicker ColorPickerButtonColor;
    @FXML private ColorPicker ColorPickerTopPaneColor;
    @FXML private Slider SliderFontSize;
    @FXML private ColorPicker ColorPickerTextColor;
    @FXML private ColorPicker ColorPickerBorderColor;
    @FXML private ChoiceBox<String> ChoiceBlendMode;
    @FXML private ToggleButton ToggleBtnDarkMode;
    @FXML private ToggleGroup Off;
    @FXML private Button BtnSetDefault;
    @FXML private TabPane TabPaneSystem;
    @FXML private ChoiceBox<?> ChoiceTimeZone;
    @FXML private DatePicker DatePicker;
    @FXML private ChoiceBox<String> ChoiceFontFamily;
    @FXML private TextField TextFieldEmail;
    @FXML private Button BtnSystemSave;

    @FXML
    void DarkModeTogglePressed(ActionEvent event) {
    }

    @FXML
    void OnClickSystemSave(ActionEvent event) {
    }

    public void JavafxChoiceFill() {
        BlendModelist.addAll("SRC_OVER", "SRC_ATOP", "ADD", "MULTIPLY", "SCREEN", "OVERLAY", "DARKEN", "LIGHTEN", "COLOR_DODGE", "COLOR_BURN", "HARD_LIGHT", "SOFT_LIGHT", "DIFFERENCE", "EXCLUSION", "RED", "GREEN", "BLUE");
        ChoiceBlendMode.getItems().addAll(BlendModelist);
        ChoiceBlendMode.setValue("SRC_OVER");
        Fontlist.addAll("System", "Aharoni", "Arial", "Traditional Arabic", "Viner Hand ITC");
        ChoiceFontFamily.getItems().addAll(Fontlist);
        ChoiceFontFamily.setValue("System");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        JavafxChoiceFill();
        if (flagtoggle == true) {
            SettingPanel.getStylesheets().clear();
            ToggleBtnDarkMode.setSelected(true);
            String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
            SettingPanel.getStylesheets().add(css);
        } else {
            SettingPanel.getStylesheets().clear();
            ToggleBtnDarkMode.setSelected(false);
            String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
            SettingPanel.getStylesheets().add(css);
        }

        ColorPickerThemeDesgin.setValue(Color.valueOf("#f2f2f2"));
        ColorPickerButtonColor.setValue(Color.valueOf("#787878"));
        ColorPickerTopPaneColor.setValue(Color.valueOf("#59b7ff"));
        ColorPickerTextColor.setValue(Color.valueOf("#000000"));
        ColorPickerBorderColor.setValue(Color.valueOf("#59b7ff"));

        ColorPickerThemeDesgin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerThemeDesgin.getValue());
                System.out.println(c);
                SettingPanel.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerButtonColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerButtonColor.getValue());
                System.out.println(c);
                BtnSetDefault.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerTopPaneColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerTopPaneColor.getValue());
                System.out.println(c);
                TopPanel.setStyle("-fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ColorPickerTextColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerTextColor.getValue());
                String textColor = "-fx-text-fill: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7);
                //or txtSearch.setStyle("-fx-text-fill: #BA55D3;");

                BtnSetDefault.setStyle(textColor);
                System.out.println(textColor);
            }
        });

        ColorPickerBorderColor.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String c = String.valueOf(ColorPickerBorderColor.getValue());
                System.out.println(c);
                String color = "-fx-border-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7);
                TablPaneThemeDesign.setStyle(color);
                TabPaneSystem.setStyle(color);
                System.out.println("fx-background-color: #" + c.charAt(2) + c.charAt(3) + c.charAt(4) + c.charAt(5) + c.charAt(6) + c.charAt(7));
            }
        });

        ToggleBtnDarkMode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (ToggleBtnDarkMode.isSelected()) {
                    System.out.println("Toggle On");
                    SettingPanel.getStylesheets().clear();
                    flagtoggle = true;
                    String css = this.getClass().getResource("/Css/darkmode.css").toExternalForm();
                    SettingPanel.getStylesheets().add(css);

                } else {
                    System.out.println("Toggle Off");
                    SettingPanel.getStylesheets().clear();
                    flagtoggle = false;
                    String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
                    SettingPanel.getStylesheets().add(css);
                }
            }
        });
    }

    public void OnSelectBlendMode(ActionEvent event) {
        String blendmode = ChoiceBlendMode.getValue().toString();
        SettingPanel.setBlendMode(BlendMode.valueOf(blendmode));
        System.out.println(blendmode);
    }

    public void onSliderChanged(MouseEvent mouseEvent) {
        double slideValue = SliderFontSize.getValue();
        System.out.println(slideValue);
        BtnSetDefault.setStyle("-fx-font-size: " + slideValue);
    }

    public void OnSelectFamilyStyle(ActionEvent event) {
        String font = ChoiceFontFamily.getValue().toString();
        BtnSetDefault.setFont(Font.font(font));
    }

    @FXML
    void OnClickDefault(ActionEvent event) throws IOException {
        System.out.println("Set Default Clicked");
        flagtoggle = false ;
        SettingPanel.getStylesheets().clear();
        String css = this.getClass().getResource("/Css/lightmode.css").toExternalForm();
        SettingPanel.getStylesheets().add(css);
    }

    public void OnClickSave(ActionEvent event) throws IOException, URISyntaxException {
        CssFile cssfile = new CssFile();
        String url =("src/main/resources/Css/userDesign.css");
        String PaneColor = ColorPickerThemeDesgin.getValue().toString();
        String ButtonColor = ColorPickerButtonColor.getValue().toString();
        String TopPaneColor = ColorPickerTopPaneColor.getValue().toString();
        String blendmode = ChoiceBlendMode.getValue().toString();
        Double FontSize = SliderFontSize.getValue();
        String TextColor = ColorPickerTextColor.getValue().toString();
        String BorderColor = ColorPickerBorderColor.getValue().toString();
        String FontFamily = ChoiceFontFamily.getValue();
    }
}