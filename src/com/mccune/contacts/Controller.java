package com.mccune.contacts;

import com.mccune.contacts.datamodel.Contact;
import com.mccune.contacts.datamodel.ContactData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.util.Optional;

public class Controller {

    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TableView contactTable;



    @FXML
    public void showAddContactDialog(){
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainBorderPane.getScene().getWindow());
        dialog.setTitle("Add New Contact");
        dialog.setHeaderText("Use this dialog to create a new contact.");
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("contactDialog.fxml"));

        try{
            Parent root = FXMLLoader.load(getClass().getResource("contactDialog.fxml"));
            dialog.getDialogPane().setContent(fxmlLoader.load());

        }catch(IOException e){
            System.out.println("Couldnt load the dialog");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ContactDialogController controller = fxmlLoader.getController();
            controller.processResults();
            Contact newContact = controller.processResults();
//            todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
            // selects new item after it is added.
            ContactData.getInstance().addContact(newContact);
//            todoListView.getSelectionModel().select(newContact);
            System.out.println("OK pressed");
        }else{
            System.out.println("Cancel pressed");
        }
    }


}





