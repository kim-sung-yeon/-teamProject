package mall.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import member.model.MemberBean;
import member.model.MemberDao;
import order.model.OrderBean;
import order.model.OrderDao;
import orderdetail.model.OrderdetailBean;
import orderdetail.model.OrderdetailDao;
import products.model.ProductsDao;

@Controller
public class CalculateController {
	private final String command = "calculate.mall";//mallList.jsp 결제하기 클릭
	private final String gotoPage = "redirect:/list.prd";
	
	@Autowired
	private MemberDao mdao;
	
	@Autowired
	private ProductsDao pdao;
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private OrderdetailDao orderdetailDao;
	@RequestMapping(command)
	public String doAction(HttpSession session,
						HttpServletResponse response) throws IOException {
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		
		Map<Integer, Integer> orderlists = mycart.getAllOrderLists();
		Set<Integer> keyList = orderlists.keySet();
		for(Integer num : keyList) {
			System.out.println(num+"번"+orderlists.get(num)+"개");
		}
		//System.out.println(orderlists.toString());
		
		
		MemberBean loginInfo = (MemberBean)session.getAttribute("loginInfo");
		
		
		System.out.println("id:"+loginInfo.getId());//MemberloginController
		
		int cnt = orderDao.insertOrder(loginInfo.getId()); 
		System.out.println("insertOrder cnt : "+cnt);
		int maxOid = orderDao.getMaxOid();
		System.out.println("maxOid cnt : "+maxOid);
		
		for(Integer pnum :orderlists.keySet()) {
			Integer qty = orderlists.get(pnum);
			OrderdetailBean odBean = new OrderdetailBean();
			odBean.setOid(maxOid);
			odBean.setPnum(pnum);
			odBean.setQty(qty);
			
			int cnt2 = orderdetailDao.insertOrderDetail(odBean);
			//여러상품을 한번에 구매할 수도 잇으니까
			//그러면 insert문을 for문안에 넣어야 반복하며 여러개 상품 정보를 넣을 수있다. 
			System.out.println("insertOrderDetail cnt : "+cnt2);
			//상품 재고 수량 감소
			int cnt3 = pdao.updateStock(pnum,qty);
			System.out.println("updateStock cnt : "+cnt3);
			if(cnt3 == 0) {
				 response.setContentType("text/html; charset=UTF-8");
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert('재고가 부족합니다!')");
				pw.print("</script>");
				
				pw.flush();
				
			}
			
		}//for
		int cnt4 = mdao.updateMpoint(loginInfo.getId(),100);
		System.out.println("updateStock cnt : "+cnt4);
		//session.invalidate(); 모든 세션 해제x
		session.removeAttribute("mycart");//장바구니 세션만 해제
		return gotoPage;  
	}
}
