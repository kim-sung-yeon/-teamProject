package member.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utility.Paging;

@Service("myMemberDao")
public class MemberDao {

	private final String namespace="member.model.Member";

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public MemberBean getMember(String id) {
		MemberBean login = null;
		login = sqlSessionTemplate.selectOne(namespace + ".getMember", id);
		System.out.println("MDao login : " + login);
		return login;
	}//getMember

	public int insertMember(MemberBean mb) {
		// TODO Auto-generated method stub
		int cnt = sqlSessionTemplate.insert(namespace+".insertMember",mb);
		System.out.println("insert member cnt"+cnt);
		return cnt;
	}

	public List<MemberBean> getAllMemberList(Map<String, String> map, Paging pageInfo) {
		// TODO Auto-generated method stub
		RowBounds rowbounds = new RowBounds(pageInfo.getOffset(),pageInfo.getLimit());
		List<MemberBean> list = sqlSessionTemplate.selectList(namespace+".getAllMemberList",map,rowbounds);
		return list;
	}

	public int getTotalCount(Map<String,String> map) {
		// TODO Auto-generated method stub
		int count = sqlSessionTemplate.selectOne(namespace+".getTotalCount",map);
		return count;
	}

	public int updateMpoint(String id, int i) {
		// TODO Auto-generated method stub
		MemberBean mb = new MemberBean();
		mb.setId(id);
		mb.setMpoint(i);
		int cnt = sqlSessionTemplate.update(namespace+".updateMpoint",mb);
		return cnt;
	}

}
