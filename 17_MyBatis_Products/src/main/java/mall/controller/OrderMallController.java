package mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import member.model.MemberBean;
import order.model.OrderBean;
import order.model.OrderDao;

@Controller
public class OrderMallController {
	private final String command = "order.mall";//start.jsp에서 요청
	private String getPage = "myShoppingList";
	
	@Autowired
	private OrderDao odao;
	
	@RequestMapping(command)
	public String doAction(HttpSession session,HttpServletRequest request) {
		//로그인 안했으면 
		MemberBean login = (MemberBean)session.getAttribute("loginInfo");
		if(login == null) {
			session.setAttribute("destination", "redirect:/order.mall");
			return "redirect:/loginForm.mb";
		}else {
			
			String id = login.getId();
			List<OrderBean> list = odao.orderMall(id);
			request.setAttribute("list", list);
			
			return getPage;
		}
		
	}
}
