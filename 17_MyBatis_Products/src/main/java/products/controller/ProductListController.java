package products.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import products.model.ProductsBean;
import products.model.ProductsDao;
import utility.Paging;

@Controller
public class ProductListController {

	@Autowired
	ProductsDao pdao;
	
	@RequestMapping("list.prd")
	public String list(@RequestParam(value = "whatColumn", required= false) String whatColumn,
			@RequestParam(value = "input" , required= false) String input,
			@RequestParam(value = "pageNumber", required= false) String pageNumber,
			HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
		map.put("whatColumn", whatColumn);
		map.put("input", "%"+input+"%");
		int count = pdao.totalCount(map);
		String url = request.getContextPath()+"/list.prd";
		
		Paging paging = new Paging(pageNumber,"3",count,url,whatColumn,input);
		List<ProductsBean> list = pdao.getAllList(map,paging);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging);
		request.setAttribute("count", count);
		request.setAttribute("pageNumber", pageNumber);
		return "list";
	}
}
