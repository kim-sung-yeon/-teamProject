package mall.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mall.cart.ShoppingInfo;
import products.model.CompositeDao;

@Controller
public class OrderDetailViewController {
	
	private final String command = "orderDetailView.mall";
	private final String getPage = "orderDetailView";
	
	@Autowired
	private CompositeDao compositeDao;
	@RequestMapping(command)
	public String doAction(@RequestParam(value="oid", required = true)int oid,
						HttpServletRequest request) {
		
		List<ShoppingInfo> shopLists = compositeDao.orderDetail(oid);
		request.setAttribute("lists", shopLists);
		
		return getPage;
	}
}
