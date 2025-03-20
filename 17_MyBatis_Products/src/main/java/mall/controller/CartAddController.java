package mall.controller;

import javax.servlet.http.HttpSession;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mall.cart.MyCartList;


@Controller
public class CartAddController {
	private final String command = "add.mall";
	private String gotoPage="redirect:/list.mall";
	
	
	@RequestMapping(command)
	public ModelAndView add(@RequestParam("num")int num,
			@RequestParam("orderqty")int orderqty,
						HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("loginInfo") == null) {
			
			mav.setViewName("redirect:/loginForm.mb");
			session.setAttribute("destination", "redirect:/list.prd");
			
			return mav;
		}else {//주문
			
			MyCartList mycart = (MyCartList)session.getAttribute("mycart");
			System.out.println("mycart:"+mycart);
			if(mycart==null) {
				mycart = new MyCartList();//한번만 객체 생성
			}
			session.setAttribute("mycart", mycart);
			mycart.addOrder(num, orderqty);
			
			mav.setViewName(gotoPage);
			return mav;
		}
		
		
	}
}
