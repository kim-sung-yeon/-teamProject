package member.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import member.model.MemberBean;
import member.model.MemberDao;
import utility.Paging;

@Controller
public class MemberListController {
	
	private final String command = "/list.mb";
	private String getPage = "memberList";
	
	@Autowired
	MemberDao mdao;
	@RequestMapping(command)
	public String doAction(@RequestParam(value="whatColumn",required=false)String whatColumn,
			@RequestParam(value="keyword",required=false)String keyword,
			@RequestParam(value="pageNumber",required=false)String pageNumber,
			HttpServletRequest request) {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%"+keyword+"%");
		int totalCount = mdao.getTotalCount(map);
		String url = request.getContextPath()+"/list.mb";
		Paging pageInfo = new Paging(pageNumber,"3",totalCount,url,whatColumn,keyword);
		
		List<MemberBean> list = mdao.getAllMemberList(map,pageInfo);
		
		request.setAttribute("pageInfo", pageInfo);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("list", list);
		return getPage;
	}
	
}
