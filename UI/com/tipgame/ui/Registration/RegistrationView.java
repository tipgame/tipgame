package com.tipgame.ui.Registration;

import com.tipgame.CustomExceptions.CustomRegistrationFieldsException;
import com.tipgame.data.User;
import com.tipgame.processor.RegistrationProcessor;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RegistrationView extends CustomComponent {
	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private GridLayout RegistrationMainLayout;

	@AutoGenerated
	private Button BtnRegistration;

	@AutoGenerated
	private VerticalLayout verticalLayout_1;

	@AutoGenerated
	private Label labelError;

	@AutoGenerated
	private PasswordField PasswordField;

	@AutoGenerated
	private Label LabelPassword;

	@AutoGenerated
	private TextField TextFieldUsername;

	@AutoGenerated
	private Label LabelUsername;

	@AutoGenerated
	private TextField TextFieldEmail;

	@AutoGenerated
	private Label LabelEmail;

	@AutoGenerated
	private TextField TextFieldName;

	@AutoGenerated
	private Label LabelName;

	@AutoGenerated
	private TextField TextFieldChristianname;

	@AutoGenerated
	private Label LabelChristianname;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	private static final long serialVersionUID = 7233701619203736013L;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public RegistrationView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	public void createRegistrationButtonClick(Button.ClickEvent event) {
		labelError.setValue("");
		RegistrationProcessor registrationProducer = new RegistrationProcessor();
		try
		{
			registrationProducer.CreateRegistration(CreateUser());
			((Window) getWindow().getParent()).removeWindow(getWindow());
		}
		catch (Exception e)
		{
			labelError.setValue(e.getMessage());
			e.printStackTrace();
		}
    }
	
	private void AreFieldsFilled() throws Exception
	{
		if(((String)TextFieldChristianname.getValue() == "") || 
				((String) TextFieldName.getValue() == "") || 
				((String) TextFieldUsername.getValue() == "") || 
				((String) PasswordField.getValue() == "")|| 
				((String)TextFieldEmail.getValue() == ""))
		{
			throw new CustomRegistrationFieldsException("Bitte f�llen Sie alle Felder aus!");
		}
	}
	
	private User CreateUser() throws Exception
	{
		AreFieldsFilled();
		User user = new User();
		try {
			user.setChristianname((String)TextFieldChristianname.getValue());
			user.setName((String) TextFieldName.getValue());
			user.setUsername((String) TextFieldUsername.getValue());
			user.setPassword(TipgameUtils.byteArrayToHexString(TipgameUtils.computeHash((String) PasswordField.getValue())));
			user.setEmail((String)TextFieldEmail.getValue());
			user.setRights(1);
			user.setGermanytipp("");
			user.setWinnertipp("");
			user.setStatisticId(0);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return user;
	}
	

	@AutoGenerated
	private AbsoluteLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new AbsoluteLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("100%");
		mainLayout.setHeight("100%");
		mainLayout.setMargin(false);
		
		// top-level component properties
		setWidth("100.0%");
		setHeight("100.0%");
		
		// RegistrationMainLayout
		RegistrationMainLayout = buildRegistrationMainLayout();
		mainLayout.addComponent(RegistrationMainLayout,
				"top:0.0px;left:20.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private GridLayout buildRegistrationMainLayout() {
		// common part: create layout
		RegistrationMainLayout = new GridLayout();
		RegistrationMainLayout.setImmediate(false);
		RegistrationMainLayout.setWidth("260px");
		RegistrationMainLayout.setHeight("300px");
		RegistrationMainLayout.setMargin(false);
		RegistrationMainLayout.setColumns(2);
		RegistrationMainLayout.setRows(8);
		
		// LabelChristianname
		LabelChristianname = new Label();
		LabelChristianname.setImmediate(false);
		LabelChristianname.setWidth("-1px");
		LabelChristianname.setHeight("-1px");
		LabelChristianname.setValue("Vorname: ");
		RegistrationMainLayout.addComponent(LabelChristianname, 0, 1);
		
		// TextFieldChristianname
		TextFieldChristianname = new TextField();
		TextFieldChristianname.setImmediate(false);
		TextFieldChristianname.setWidth("-1px");
		TextFieldChristianname.setHeight("-1px");
		TextFieldChristianname.setRequired(true);
		TextFieldChristianname.setSecret(false);
		RegistrationMainLayout.addComponent(TextFieldChristianname, 1, 1);
		
		// LabelName
		LabelName = new Label();
		LabelName.setImmediate(false);
		LabelName.setWidth("-1px");
		LabelName.setHeight("-1px");
		LabelName.setValue("Name: ");
		RegistrationMainLayout.addComponent(LabelName, 0, 2);
		
		// TextFieldName
		TextFieldName = new TextField();
		TextFieldName.setImmediate(false);
		TextFieldName.setWidth("-1px");
		TextFieldName.setHeight("-1px");
		TextFieldName.setRequired(true);
		TextFieldName.setSecret(false);
		RegistrationMainLayout.addComponent(TextFieldName, 1, 2);
		
		// LabelEmail
		LabelEmail = new Label();
		LabelEmail.setImmediate(false);
		LabelEmail.setWidth("-1px");
		LabelEmail.setHeight("-1px");
		LabelEmail.setValue("E-Mail:");
		RegistrationMainLayout.addComponent(LabelEmail, 0, 3);
		
		// TextFieldEmail
		TextFieldEmail = new TextField();
		TextFieldEmail.setImmediate(false);
		TextFieldEmail.setWidth("-1px");
		TextFieldEmail.setHeight("-1px");
		TextFieldEmail.setRequired(true);
		TextFieldEmail.setSecret(false);
		RegistrationMainLayout.addComponent(TextFieldEmail, 1, 3);
		
		// LabelUsername
		LabelUsername = new Label();
		LabelUsername.setImmediate(false);
		LabelUsername.setWidth("-1px");
		LabelUsername.setHeight("-1px");
		LabelUsername.setValue("Benutzername: ");
		RegistrationMainLayout.addComponent(LabelUsername, 0, 4);
		
		// TextFieldUsername
		TextFieldUsername = new TextField();
		TextFieldUsername.setImmediate(false);
		TextFieldUsername.setWidth("-1px");
		TextFieldUsername.setHeight("-1px");
		TextFieldUsername.setRequired(true);
		TextFieldUsername.setSecret(false);
		RegistrationMainLayout.addComponent(TextFieldUsername, 1, 4);
		
		// LabelPassword
		LabelPassword = new Label();
		LabelPassword.setImmediate(false);
		LabelPassword.setWidth("-1px");
		LabelPassword.setHeight("-1px");
		LabelPassword.setValue("Passwort: ");
		RegistrationMainLayout.addComponent(LabelPassword, 0, 5);
		
		// PasswordField
		PasswordField = new PasswordField();
		PasswordField.setImmediate(false);
		PasswordField.setWidth("-1px");
		PasswordField.setHeight("-1px");
		PasswordField.setRequired(true);
		RegistrationMainLayout.addComponent(PasswordField, 1, 5);
		
		// verticalLayout_1
		verticalLayout_1 = buildVerticalLayout_1();
		RegistrationMainLayout.addComponent(verticalLayout_1, 1, 6);
		
		// BtnRegistration
		BtnRegistration = new Button("",this,"createRegistrationButtonClick");
		BtnRegistration.setCaption("Registrierung");
		BtnRegistration.setImmediate(true);
		BtnRegistration.setWidth("-1px");
		BtnRegistration.setHeight("-1px");
		RegistrationMainLayout.addComponent(BtnRegistration, 1, 7);
		
		return RegistrationMainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildVerticalLayout_1() {
		// common part: create layout
		verticalLayout_1 = new VerticalLayout();
		verticalLayout_1.setImmediate(false);
		verticalLayout_1.setWidth("-1px");
		verticalLayout_1.setHeight("-1px");
		verticalLayout_1.setMargin(false);
		
		// labelError
		labelError = new Label();
		labelError.setImmediate(false);
		labelError.setWidth("135px");
		labelError.setHeight("-1px");
		verticalLayout_1.addComponent(labelError);
		
		return verticalLayout_1;
	}
}
