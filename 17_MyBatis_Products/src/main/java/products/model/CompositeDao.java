package products.model;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mall.cart.ShoppingInfo;

@Component
public class CompositeDao {//����
	private final String namespace = "product.model.Composite";
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public List<ShoppingInfo> orderDetail(int oid) {
		// TODO Auto-generated method stub
		List<ShoppingInfo> lists = sqlSessionTemplate.selectList(namespace+".orderDetail", oid);
		return lists;
		
	}
	
}
