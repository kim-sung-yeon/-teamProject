package orderdetail.model;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("oddao")
public class OrderdetailDao {
	
	private String namesapce = "orderdetail.model.Orderdetail";
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	public int  insertOrderDetail(OrderdetailBean obean) {
		int cnt = sqlSessionTemplate.insert(namesapce+".insertOrderDetail",obean);
		return cnt;
	}
}


	
	
