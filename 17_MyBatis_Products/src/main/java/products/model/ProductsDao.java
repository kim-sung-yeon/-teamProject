package products.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import orderdetail.model.OrderdetailBean;
import utility.Paging;

@Component("pdao")
public class ProductsDao {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	String namespace = "product.model.Product";

	public List<ProductsBean> getAllList(Map<String, String> map, Paging paging) {
		// TODO Auto-generated method stub
		RowBounds rowbounds = new RowBounds(paging.getOffset(),paging.getLimit());
		List<ProductsBean> list = sqlSessionTemplate.selectList(namespace+".getAllList",map,rowbounds);
		System.out.println("list.size : "+list.size());
		return list;
		
	}

	public int totalCount(Map<String, String> map) {
		// TODO Auto-generated method stub
		int totalCount = sqlSessionTemplate.selectOne(namespace+".totalCount",map);
		return totalCount;
	}

	public int deleteProduct(int num) {
		// TODO Auto-generated method stub
		int cnt = sqlSessionTemplate.delete(namespace+".deleteProduct",num);
		return cnt;
	}

	public int insertProducts(ProductsBean pb) {
		// TODO Auto-generated method stub
		int cnt = sqlSessionTemplate.insert(namespace+".insertProducts",pb);
		return cnt;
	}
	
	public ProductsBean getOneProduct(int num) {
		ProductsBean pb = sqlSessionTemplate.selectOne(namespace + ".getOneProduct", num); 
		return pb;
	}//getOneProduct

	public int updateProducts(ProductsBean products) {
		int cnt = -1;
		cnt = sqlSessionTemplate.update(namespace + ".updateProducts",products);
		return cnt;
	}//updateProducts

	

	public int updateStock(int pnum, int qty) {
		// TODO Auto-generated method stub
		/*
		 * Map<String, Integer> map = new HashMap<String, Integer>(); map.put("pnum",
		 * pnum); map.put("qty", qty);
		 */
		ProductsBean pb = new ProductsBean();
		pb.setNum(pnum);
		pb.setStock(qty);
		int cnt = sqlSessionTemplate.update(namespace+".updateStock",pb);
		return cnt;
	}

	
}
