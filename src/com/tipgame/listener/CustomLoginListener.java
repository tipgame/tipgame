package com.tipgame.listener;

import org.vaadin.artur.icepush.ICEPush;

import com.vaadin.ui.Label;
import com.vaadin.ui.LoginForm;
import com.vaadin.ui.LoginForm.LoginEvent;
import com.vaadin.ui.ProgressIndicator;
import com.vaadin.ui.TabSheet;
import com.tipgame.Threads.LoginThread;

public class CustomLoginListener implements LoginForm.LoginListener
{
	private static final long serialVersionUID = -5685166950324371044L;
	private TabSheet _mainTabSheet;
	private ProgressIndicator _progressIndicator;
	private Label _errorLabel;

	public void onLogin(LoginEvent event) {
		final LoginThread thread = new LoginThread(_progressIndicator, event, _mainTabSheet, _errorLabel);
		thread.start();
	}

	public void SetMainTabSheet(TabSheet mainTabSheet) {
		this._mainTabSheet = mainTabSheet;	
	}

	public void setProgressIndicator(ProgressIndicator _progressIndicator) {
		this._progressIndicator = _progressIndicator;
	}

	public void set_errorLabel(Label _errorLabel) {
		this._errorLabel = _errorLabel;
	}
	
}

