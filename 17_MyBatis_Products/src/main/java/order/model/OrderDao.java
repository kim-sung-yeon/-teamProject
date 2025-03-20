package order.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("odao")
public class OrderDao {
	
	private String namesapce = "order.model.Order";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	

	public int insertOrder(String memberId) {
		// TODO Auto-generated method stub
		int cnt = sqlSessionTemplate.insert(namesapce+".insertOrder",memberId);
		return cnt;
	}



	public int getMaxOid() {
		// TODO Auto-generated method stub
		int max = sqlSessionTemplate.selectOne(namesapce+".getMaxOid");
		return max;
	}



	public List<OrderBean> orderMall(String id) {
		// TODO Auto-generated method stub
		List<OrderBean> list = sqlSessionTemplate.selectList(namesapce+".orderMall", id);
		
		return list;
	}



	
	
	
}
