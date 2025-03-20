package products.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;
import utility.Paging;

@Controller
public class ProductDeleteController {

	@Autowired
	ProductsDao pdao;
	
	@Autowired
	ServletContext servletContext;
	private final String gotoPage="redirect:/list.prd";
	@RequestMapping("delete.prd")
	public ModelAndView insert(@RequestParam("num") int num,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam(value = "whatColumn",required = false) String whatColumn,
			@RequestParam(value = "keyword", required = false) String keyword) {
		
		ProductsBean pbean =  pdao.getOneProduct(num);
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		File url = new File(uploadPath);
		File destination = new File(url, pbean.getImage());
		
		ModelAndView mav = new ModelAndView();
		int cnt = pdao.deleteProduct(num);
		System.out.println("PDC cnt :" + cnt);
		if(cnt != -1) {
			destination.delete();
		}
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("whatColumn", whatColumn);
		mav.addObject("keyword", keyword);
		mav.setViewName(gotoPage);
		return mav;
		
	}
	
}
