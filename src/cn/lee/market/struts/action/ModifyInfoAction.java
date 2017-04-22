/*    */ package cn.lee.market.struts.action;
/*    */ 
/*    */ import cn.lee.market.dao.UserDAO;
import cn.lee.market.model.User;
/*    */ import cn.lee.market.util.DateConverter;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.apache.struts.action.Action;
/*    */ import org.apache.struts.action.ActionForm;
/*    */ import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
/*    */ 
/*    */ public class ModifyInfoAction extends Action
/*    */ {
/* 18 */   DateConverter dc = new DateConverter();
/*    */ 
/*    */   public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
/*    */     throws Exception
/*    */   {
/* 24 */     HttpSession session = request.getSession();
/* 25 */     User user = (User)session.getAttribute("user");
             UserDAO userDao = new UserDAO();
/* 26 */     String result = null;
             String error = null;
/* 27 */     //result = "修改成功！点确定进入市场主页！";
             
/* 29 */     String photo_ = request.getParameter("photo");
/* 30 */     int photo = Integer.parseInt(photo_);
/* 31 */     String str = request.getParameter("birthday");
/* 32 */     Date birthday = (Date)this.dc.convert(Date.class, str);
/* 33 */     System.out.println(str + "->" + birthday);
/* 34 */     String email = request.getParameter("email");
/* 35 */     String doModPWD = request.getParameter("doModPWD");
            

               String oldPassword = request.getParameter("oldPassword");
/* 39 */       String newPassword = request.getParameter("newPassword");
/* 40 */       String confirmPassword = request.getParameter("confirmPassword");
               System.out.println(doModPWD + "->" + doModPWD);
/* 36 */     if ("1".equals(doModPWD)){
	             if (oldPassword !=null&&newPassword !=null && confirmPassword !=null&&oldPassword !="" &&newPassword !="" && confirmPassword !="")
	/*    */     {
	/* 38 */      
	/* 41 */       if (oldPassword.equals(user.getPassword()))
	/*    */       {
	/* 43 */         if (newPassword.equals(confirmPassword)) {
	/* 44 */           user.setPassword(newPassword);
	/*    */         }
	/*    */         else {
		               error = "修改密码与确认密码不同！请重新输入！";
	/*    */         }
	/*    */       }
	/*    */       else
	/*    */       {
		            error = "原始密码输入错误！请重新输入！";
	/*    */       }
	/*    */     }else{
		        error = "密码不能为空！";
				}
			}	
/* 57 */     user.setBirthday(birthday);
/* 58 */     user.setEmail(email);
/* 59 */     user.setPhoto((short)photo);
/*    */     if (error == null) {
	             result = "修改成功！点确定进入市场主页！";
               }else{
            	   request.setAttribute("error", error);
               }
/* 61 */     request.setAttribute("result", result);
/* 62 */     return mapping.findForward("modifyInfo");
/*    */   }
/*    */ }

/* Location:           C:\Users\muye\Desktop\51\校园二手交易平台设计与论文\market\code\market\WEB-INF\classes\
 * Qualified Name:     cn.lee.market.struts.action.ModifyInfoAction
 * JD-Core Version:    0.6.1
 */