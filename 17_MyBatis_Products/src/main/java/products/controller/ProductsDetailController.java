package products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;

@Controller
public class ProductsDetailController {
	private final String command="detail.prd";
	private final String getPage="productsDetailForm";
	
	@Autowired
	ProductsDao pdao;
	
	@RequestMapping(command)
	public ModelAndView doAction(
			@RequestParam("num") int num,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam(value = "whatColumn", required= false) String whatColumn,
			@RequestParam(value = "keyword" , required= false) String keyword) {
		
		ModelAndView mav = new ModelAndView();
		ProductsBean products = pdao.getOneProduct(num);
		mav.addObject("pb",products);
		mav.addObject("pageNumber",pageNumber);
		mav.setViewName(getPage);
		
		return mav;

		//kim 팀원이 추가함
		//kim 팀원이 추가함
		//kim 팀원이 추가함

	}
  	  
}
