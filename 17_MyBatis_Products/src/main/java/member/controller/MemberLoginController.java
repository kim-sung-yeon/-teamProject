package member.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import member.model.MemberBean;
import member.model.MemberDao;

@Controller
public class MemberLoginController {
	private final String command = "loginForm.mb";
	private final String getPage = "memberLoginForm";
	private final String gotoPage = "redirect:/list.mb";
	
	@Autowired
	MemberDao mdao; 

	@Autowired
	ServletContext servletContext;
	
	@RequestMapping(value=command, method=RequestMethod.GET)
	public String doAction() {
		
		return getPage;
	}
	
	//memberLoginForm.jsp submit
	@RequestMapping(value=command, method=RequestMethod.POST)
	public ModelAndView doAction(MemberBean mb, HttpServletResponse response, HttpSession session) throws IOException {
		
		System.out.println("mb.getId():"+mb.getId());
		System.out.println("mb.getPassword():"+mb.getPassword()); // �Է��� ���
		
		MemberBean login = mdao.getMember(mb.getId());
		
		PrintWriter pw = response.getWriter();
		
		response.setContentType("text/html;charset=UTF-8");
		
		if(login == null) { // ��ġ�ϴ� ���̵� ����.
			System.out.println("��ġ�ϴ� ���̵� ����.");
			pw.println("<script type='text/javascript'>");
			pw.println("alert('��ġ�ϴ� ���̵� �����ϴ�.')");
			pw.println("</script>");
			pw.flush();
			
			return new ModelAndView(getPage);
			
		}else { // ��ġ�ϴ� ���̵� �ִ�.
			if(login.getPassword().equals(mb.getPassword())) { // ���̵�, ��� ��� ��ġ
				
				session.setAttribute("loginInfo",login);
				return new ModelAndView((String)session.getAttribute("destination")); // redirect:/insert.prd
				
			}else { // ���̵� ��ġ, ��� ��ġ X
				pw.println("<script type='text/javascript'>");
				pw.println("alert('����� ��ġ���� �ʽ��ϴ�.')");
				pw.println("</script>");
				pw.flush();
				return new ModelAndView(getPage);
			}
		}
		
	}
	
}
