package com.tipgame.ui;

import com.tipgame.listener.CustomLoginListener;
import com.tipgame.ui.Registration.RegistrationView;
import com.tipgame.ui.Registration.RegistrationWindow;
import com.tipgame.utils.TipgameUtils;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

public class MainView extends CustomComponent {
	
	private static final long serialVersionUID = 651358529925652073L;

	@AutoGenerated
	private AbsoluteLayout mainLayout;

	@AutoGenerated
	private VerticalLayout mainVerticalLayout;

	@AutoGenerated
	private TabSheet mainTabSheet;

	@AutoGenerated
	private VerticalLayout tabLoginVerticalLayout;

	@AutoGenerated
	private GridLayout gridLayoutLogin;

	@AutoGenerated
	private HorizontalLayout horizontalLayoutRegistryButton;

	@AutoGenerated
	private Button buttonRegistration;

	@AutoGenerated
	private Label labelRegistry;

	@AutoGenerated
	private LoginForm loginForm;

	@AutoGenerated
	private Embedded emLogoImage;
	
	private ProgressIndicator progressIndicator;
	
	private Label errorLabel;

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */	
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */


	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	
	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */
	

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	public MainView() {
		buildMainLayout();
		setCompositionRoot(mainLayout);
	}

	public void onRegistrationButtonClick(Button.ClickEvent event)
	{
		RegistrationWindow registrationWindow = new RegistrationWindow("Registrierung", new RegistrationView());
		getWindow().addWindow(registrationWindow);
	}
	
	private CustomLoginListener CreateCustomLoginListener()
	{
		CustomLoginListener customLoginListener = new CustomLoginListener();			
		customLoginListener.SetMainTabSheet(mainTabSheet);
		customLoginListener.setProgressIndicator(progressIndicator);
		customLoginListener.set_errorLabel(errorLabel);
		
		return customLoginListener;		
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
		
		// mainVerticalLayout
		mainVerticalLayout = buildMainVerticalLayout();
		mainLayout.addComponent(mainVerticalLayout, "top:0.0px;left:0.0px;");
		
		return mainLayout;
	}

	@AutoGenerated
	private VerticalLayout buildMainVerticalLayout() {
		// common part: create layout
		mainVerticalLayout = new VerticalLayout();
		mainVerticalLayout.setImmediate(false);
		mainVerticalLayout.setWidth("100.0%");
		mainVerticalLayout.setHeight("100.0%");
		mainVerticalLayout.setMargin(false);

		// mainTabSheet
		mainTabSheet = buildMainTabSheet();
		mainVerticalLayout.addComponent(mainTabSheet);
		
		return mainVerticalLayout;
	}

	@AutoGenerated
	private TabSheet buildMainTabSheet() {
		// common part: create layout
		mainTabSheet = new TabSheet();
		mainTabSheet.setImmediate(true);
		mainTabSheet.setWidth("100.0%");
		mainTabSheet.setHeight("100.0%");
		
		// tabLoginVerticalLayout
		tabLoginVerticalLayout = buildTabLoginVerticalLayout();
		mainTabSheet.addTab(tabLoginVerticalLayout, "Login", new ThemeResource("resources/icons/login.gif"));
		
		return mainTabSheet;
	}

	@AutoGenerated
	private VerticalLayout buildTabLoginVerticalLayout() {
		// common part: create layout
		tabLoginVerticalLayout = new VerticalLayout();
		tabLoginVerticalLayout.setImmediate(false);
		tabLoginVerticalLayout.setWidth("100.0%");
		tabLoginVerticalLayout.setHeight("100.0%");
		tabLoginVerticalLayout.setMargin(true);
		
		// embedded_2
		emLogoImage = new Embedded();
		emLogoImage.setImmediate(false);
		emLogoImage.setWidth("380px");
		emLogoImage.setHeight("200px");
		emLogoImage.setSource(new ThemeResource(
				"resources/icons/ifba.jpg"));
		emLogoImage.setType(1);
		emLogoImage.setMimeType("image/png");
		tabLoginVerticalLayout.addComponent(emLogoImage);
		tabLoginVerticalLayout.setComponentAlignment(emLogoImage, new Alignment(
				48));
		
		// gridLayoutLogin
		gridLayoutLogin = buildGridLayoutLogin();
		tabLoginVerticalLayout.addComponent(gridLayoutLogin);
		tabLoginVerticalLayout.setComponentAlignment(gridLayoutLogin,
				Alignment.TOP_CENTER);
		
		return tabLoginVerticalLayout;
	}

	@AutoGenerated
	private GridLayout buildGridLayoutLogin() {
		// common part: create layout
		gridLayoutLogin = new GridLayout();
		gridLayoutLogin.setImmediate(false);
		gridLayoutLogin.setWidth("-1px");
		gridLayoutLogin.setHeight("-1px");
		gridLayoutLogin.setMargin(false);
		gridLayoutLogin.setRows(4);
		
		errorLabel = new Label();
		errorLabel.setValue("");
		gridLayoutLogin.addComponent(errorLabel, 0, 2);
		
		buildProgressIndicator();
		gridLayoutLogin.addComponent(progressIndicator, 0, 1);
		
		// loginForm
		loginForm = new LoginForm();
		loginForm.setStyleName("v-loginform");
		loginForm.addListener(CreateCustomLoginListener());
		loginForm.setImmediate(false);
		loginForm.setWidth("-1px");
		loginForm.setHeight("125px");
		gridLayoutLogin.addComponent(loginForm, 0, 0);
		
		// horizontalLayoutRegistryButton
		horizontalLayoutRegistryButton = buildHorizontalLayoutRegistryButton();
		gridLayoutLogin.addComponent(horizontalLayoutRegistryButton, 0, 3);
				
		return gridLayoutLogin;
	}
	
	private void buildProgressIndicator()
	{
		progressIndicator = new ProgressIndicator();
		progressIndicator.setPollingInterval(500);
		progressIndicator.setIndeterminate(false);  
		progressIndicator.setWidth("-1px");
		progressIndicator.setHeight("0px");
	}

	@AutoGenerated
	private HorizontalLayout buildHorizontalLayoutRegistryButton() {
		// common part: create layout
		horizontalLayoutRegistryButton = new HorizontalLayout();
		horizontalLayoutRegistryButton.setImmediate(false);
		horizontalLayoutRegistryButton.setWidth("-1px");
		horizontalLayoutRegistryButton.setHeight("-1px");
		horizontalLayoutRegistryButton.setMargin(false);
		
		// labelRegistry
		labelRegistry = new Label();
		labelRegistry.setImmediate(false);
		labelRegistry.setWidth("140px");
		labelRegistry.setHeight("-1px");
		labelRegistry.setValue("Noch nicht registriert? ");
		horizontalLayoutRegistryButton.addComponent(labelRegistry);
		
		// buttonRegistration
		Boolean setButtonEnabled = TipgameUtils.compareDates("10.06.2016 20:55");
		buttonRegistration = new Button("",this,"onRegistrationButtonClick");
		buttonRegistration.setEnabled(!setButtonEnabled);
		buttonRegistration.setCaption("Registrierung");
		buttonRegistration.setStyleName(Reindeer.BUTTON_LINK);
		buttonRegistration.setImmediate(true);
		buttonRegistration.setWidth("-1px");
		buttonRegistration.setHeight("-1px");
		horizontalLayoutRegistryButton.addComponent(buttonRegistration);
		
		return horizontalLayoutRegistryButton;
	}
}
