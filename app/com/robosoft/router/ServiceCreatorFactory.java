package com.robosoft.router;

import static com.robosoft.constants.StringConstants.STR_SERVER_ISSUE;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.robosoft.models.Service;
import play.Logger;

import com.robosoft.commands.Command;
import com.robosoft.commands.CommandParams;

public class ServiceCreatorFactory {
	
	@SuppressWarnings("unchecked")
	public static Command createService(Service service, CommandParams params) {
		
		Command rsmpService = null;
		Class serviceClass = null;
		Constructor<?> constructor = null;
        Class<?>[] param_types = new Class<?>[2];
        Object[] arguments = new Object[2];
        param_types[0] = Service.class;
        param_types[1] = CommandParams.class;
        
		try {
			Logger.debug("createService = " + service.apiClass);
			serviceClass = Class.forName(service.apiClass);
			if(serviceClass != null) {
				constructor = serviceClass.getConstructor(param_types);
				arguments = new Object[2];
		        arguments[0] = service;
		        
		        arguments[1] = params;
				//rsmpService = (RSMPCommand) serviceClass.newInstance();
				rsmpService = (Command) constructor.newInstance(arguments);
				System.out.println("............rsmpService =============  "+rsmpService);
			}	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		}  catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		} catch (IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(STR_SERVER_ISSUE);
		}
		return rsmpService;
	}

}
