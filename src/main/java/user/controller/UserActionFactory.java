package user.controller;

import user.controller.action.JoinAction;
import user.controller.action.LoginAction;
import user.controller.action.LogoutAction;
import user.controller.action.UpdateAction;
import util.Action;

public class UserActionFactory {

   private UserActionFactory() {

   }

   private static UserActionFactory instance = new UserActionFactory();

   public static UserActionFactory getInstance() {
      return instance;
   }
   
   public Action getAction (String command) {
	   Action action = null;
      
      if (command.equals("userJoin")) {
         action = new JoinAction();
      }
      else if (command.equals("userLogin")) {
         action = new LoginAction();
      }  
      else if (command.equals("userLogout")) {
         action = new LogoutAction();
      } 
      else if (command.equals("userUpdate")) {
         action = new UpdateAction();
      } 
      else if (command.equals("userDelete")) {
         action = new LoginAction();
      }

      
      return action;
      
   }
   
}