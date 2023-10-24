package com.backy.controller.action;


public class ActionFactory {

	private ActionFactory() {}
	private static ActionFactory instance = new ActionFactory();
	public static ActionFactory getInstance() {
		return instance;
	}
	
	public Action getAction(String command) {
		Action action = null;
		if(command.equals("shop_input_form"))
			action = new ShopInputFormAction();
		else if(command.equals("shop_input"))
			action = new ShopInputAction();
		else if(command.equals("shop_list"))
			action = new ShopList();
		return action;
	}
}
