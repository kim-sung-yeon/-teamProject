package member.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberRegisterController {
	@Autowired
	MemberDao mdao;
	
	private final String command = "register.mb";
	private String getPage = "register";
	private String gotoPage = "redirect:/list.mb";
	
	@RequestMapping(value=command, method = RequestMethod.GET)
	public String doAction() {
		
		return getPage;
	}
	
	@RequestMapping(value=command, method = RequestMethod.POST)
	public String doAction(@Valid @ModelAttribute("mb")MemberBean mb,
							BindingResult result) {
		if(result.hasErrors()) {
			return getPage;
		}
		int cnt = mdao.insertMember(mb);
		return gotoPage;
	}
}
