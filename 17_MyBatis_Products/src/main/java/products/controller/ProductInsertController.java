package products.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import utility.Paging;

@Controller
public class ProductInsertController {

	@Autowired
	ProductsDao pdao;
	
	@Autowired
	ServletContext servletContext;
	
	//kim 수정
	//kim 한 줄 추가
	//kim 한 줄 추가
	private final String command = "insert.prd";
	private String getPage = "productInsertForm";
	private String gotoPage = "redirect:/list.prd";
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doAction( HttpSession session  ) {
		
		System.out.println("PIC loginInfo : " + session.getAttribute("loginInfo"));
		
		if(session.getAttribute("loginInfo") == null) { // 로그인 안했으면
			session.setAttribute("destination", "redirect:/insert.prd"); 
			return "redirect:/loginForm.mb"; // MemberLoginController=>memberLoginForm.jsp 
		}else { // 로그인 했으면
			return getPage;
		}
	}
	@RequestMapping(value = command, method = RequestMethod.POST) 
	public ModelAndView postInsert(@ModelAttribute("pb") @Valid ProductsBean pb, 
			BindingResult result) {

		ModelAndView mav = new ModelAndView();

		if(result.hasErrors()) {
			mav.setViewName(getPage);
			return mav;
		}

		String uploadPath = servletContext.getRealPath("/resources/uploadImage/");
		System.out.println("uploadPath : " + uploadPath);

		File url = new File(uploadPath);
		url.mkdirs();

		File destination = new File(url, pb.getImage());

		MultipartFile multi = pb.getUpload() ;

		int cnt = pdao.insertProducts(pb); // db products insert
		if(cnt != -1) {
			mav.setViewName(gotoPage); 

			try {
				multi.transferTo(destination); // 해당 폴더에 이미지 업로드

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			mav.setViewName(getPage); 
		}

		return mav;
	}
	 
}
