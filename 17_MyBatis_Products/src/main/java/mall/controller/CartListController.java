package mall.controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import mall.cart.MyCartList;
import mall.cart.ShoppingInfo;
import products.model.ProductsBean;
import products.model.ProductsDao;

@Controller
public class CartListController {
	private final String command = "list.mall";//CartAddController���� ��û
	private String getPage = "mallList";
	@Autowired
	ProductsDao pdao;
	
	@RequestMapping(command)
	public String doAction(HttpSession session) {
		
		MyCartList mycart = (MyCartList)session.getAttribute("mycart");
		Map<Integer,Integer> orderLists =  mycart.getAllOrderLists();//��ǰ��ȣ, �ֹ�����
		Set<Integer> keyList = orderLists.keySet();//Ű�� ���� ���� �޼���
		System.out.println("keyList:"+keyList);//keyList:[5, 6, 7]
		List<ShoppingInfo> shopLists = new ArrayList<ShoppingInfo>();
		int totalAmount = 0;
		
		for(Integer pnum : keyList) {
			
			Integer qty = orderLists.get(pnum);
			ProductsBean pb = pdao.getOneProduct(pnum);
			String pname = pb.getName();
			int price = pb.getPrice();
			
			System.out.println(pnum+"�� ��ǰ �ֹ� ����:"+qty+"��");
			System.out.println("�̸�:"+pname+" ����:"+price);
			
			ShoppingInfo shopInfo = new ShoppingInfo();
			shopInfo.setPnum(pnum);
			shopInfo.setPname(pname);
			shopInfo.setQty(qty);
			shopInfo.setPrice(price);
			shopInfo.setAmount(qty*price);
			shopLists.add(shopInfo);
			totalAmount += qty*price;

		
	} 
		session.setAttribute("shopLists", shopLists);
		session.setAttribute("totalAmount", totalAmount);
		return getPage;
}
}
