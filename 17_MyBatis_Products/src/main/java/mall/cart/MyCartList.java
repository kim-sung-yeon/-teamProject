package mall.cart;

import java.util.HashMap;
import java.util.Map;




public class MyCartList {//장바구니 역할
	//3번 상품 4개 이 정보를 담을 것
	//두개가 한쌍이여야..
	//hello  
	
	private Map<Integer,Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
		
	}
	
	public void addOrder(int pnum, int oqty) {//key(상품번호), value(주문수량)
		if(orderlists.containsKey(pnum)==false) {
			//이미 상품번호가 장바구니 리스트에 포함x
			orderlists.put(pnum, oqty);
		}else {
			orderlists.put(pnum, orderlists.get(pnum)+oqty);
		}
		
		
	}
	
	public Map<Integer,Integer> getAllOrderLists() {
		return orderlists;
	}
}
