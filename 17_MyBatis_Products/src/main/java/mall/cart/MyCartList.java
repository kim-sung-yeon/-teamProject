package mall.cart;

import java.util.HashMap;
import java.util.Map;




public class MyCartList {//��ٱ��� ����
	//3�� ��ǰ 4�� �� ������ ���� ��
	//�ΰ��� �ѽ��̿���..
	//hello  
	
	private Map<Integer,Integer> orderlists = null;
	
	public MyCartList() {
		orderlists = new HashMap<Integer,Integer>();
		
	}
	
	public void addOrder(int pnum, int oqty) {//key(��ǰ��ȣ), value(�ֹ�����)
		if(orderlists.containsKey(pnum)==false) {
			//�̹� ��ǰ��ȣ�� ��ٱ��� ����Ʈ�� ����x
			orderlists.put(pnum, oqty);
		}else {
			orderlists.put(pnum, orderlists.get(pnum)+oqty);
		}
		
		
	}
	
	public Map<Integer,Integer> getAllOrderLists() {
		return orderlists;
	}
}
