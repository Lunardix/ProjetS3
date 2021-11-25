package views.login;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ckarimou01
 */

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.contextmenu.SubMenu;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.login.AbstractLogin;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import java.time.LocalDate;


@Route("") 
public class LoginView extends VerticalLayout { 
    
  public LoginView () {
    
LoginForm component = new LoginForm();
component.addLoginListener(e -> {
    boolean isAuthenticated = authenticate(e);
    if (isAuthenticated) {
        navigateToMainPage();
    } else {
        component.setError(true);
    }
});

add(component);   
      
MenuBar menuBar = new MenuBar();
Text selected = new Text("");
Div message = new Div(new Text("Selected: "), selected);

MenuItem project = menuBar.addItem("Project");
MenuItem personne = menuBar.addItem("Personne");
MenuItem module = menuBar.addItem("Modules");


SubMenu projectSubMenu = project.getSubMenu();
MenuItem users = projectSubMenu.addItem("Users");


SubMenu usersSubMenu = users.getSubMenu();
usersSubMenu.addItem("List", e -> selected.setText("List"));
usersSubMenu.addItem("Add", e -> selected.setText("Add"));

add(menuBar, message);

 IntegerField Nom = new IntegerField("Nom");
   add(Nom);
    
   IntegerField Prenom = new IntegerField("Prenom");
   add(Prenom);
    
   PasswordField passwordField = new PasswordField();
   passwordField.setLabel("Mot de passe");
   passwordField.setPlaceholder("Entrer Mot de passe");
   passwordField.setValue("pass");
   add(passwordField);
    
  
   DatePicker readonlyDatePicker = new DatePicker();
   readonlyDatePicker.setLabel("Read-only");
   readonlyDatePicker.setValue(LocalDate.now());
   readonlyDatePicker.setReadOnly(true);

   add(readonlyDatePicker);

add(textField, button);
  
   
    } 

    private boolean authenticate(AbstractLogin.LoginEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void navigateToMainPage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
