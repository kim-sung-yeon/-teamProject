package products.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import products.model.ProductsBean;
import products.model.ProductsDao;

@Controller
public class ProductUpdateController {

	private final String command = "update.prd";
	private String getPage = "productUpdateForm";
	private String gotoPage = "redirect:/list.prd";
	
	@Autowired
	ProductsDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public ModelAndView doAction(HttpServletRequest request,
								@RequestParam("num") int num,
								@RequestParam(value="pageNumber",required = false) String pageNumber,
								@RequestParam(value="whatColumn",required = false) String whatColumn,
								@RequestParam(value="keyword",required = false) String keyword,
								HttpSession session) {
		
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("loginInfo") == null) { // 로그인 안했으면
			session.setAttribute("destination", "redirect:/update.prd?num=" + num);//num을 꼭 기억하고 가져가야..
			
			mav.addObject("pageNumber",pageNumber);
			mav.addObject("whatColumn",whatColumn);
			mav.addObject("keyword",keyword);
			
			
			
			mav.setViewName("redirect:/loginForm.mb");
			return mav; // MemberLoginController=>memberLoginForm.jsp 
			
		}else { // 로그인 했으면
			ProductsBean pb = pdao.getOneProduct(num);
			System.out.println("pb.getNum():"+pb.getNum());
			mav.addObject("pb",pb);
			mav.setViewName(getPage);
			return mav;
		}
		
		
		
	}
	
	@RequestMapping(value = command,method = RequestMethod.POST)
	public ModelAndView doActionPost(@ModelAttribute("pb") @Valid ProductsBean products,BindingResult result,
									@RequestParam("pageNumber") int pageNumber,
									@RequestParam(value = "whatColumn",required = false) String whatColumn,
									@RequestParam(value = "keyword",required = false) String keyword) {
		
		
		System.out.println("PUC");
		System.out.println("whatColumn : " + whatColumn);
		System.out.println("keyword : " + keyword);
		
		if(products.getImage().equals("")) {
			products.setImage(products.getUpload2());
		}
		
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			//mav.addObject("pageNumber",pageNumber);
			mav.setViewName(getPage);
			return mav;
		}
		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		System.out.println("uploadPath:"+uploadPath);
		File url = new File(uploadPath);
		
		File destination = new File(url,products.getImage()); // 새이미지 업로드할 경로
		File destination2 = new File(url,products.getUpload2()); // 기존이미지 삭제할 경로
		
		int cnt = pdao.updateProducts(products); // DB table 수정
		if(cnt != -1) {
			mav.addObject("pageNumber",pageNumber);
			mav.addObject("whatColumn", whatColumn);
			mav.addObject("keyword", keyword);
			mav.setViewName(gotoPage);
			
			destination2.delete(); // 기존 이미지 삭제
			MultipartFile multi = products.getUpload();
			try {
				multi.transferTo(destination);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			mav.setViewName(getPage);
		}
		
//		mav.addObject("pageNumber",pageNumber);
//		mav.addObject("whatColumn", whatColumn);
//		mav.addObject("keyword", keyword);
		//mav.addObject("pb",products);
		return mav;
	}
}
