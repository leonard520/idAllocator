package pathIdAllocation;

import java.util.LinkedList;
import java.util.List;

public class PathIdPool {
	
	public List<Integer> pool;//of most size LARGEST_SIZE
	private final int ALL_ONE = -1;
	private final int DEFAULT_ALLOCATE = 1;
	private final int LARGEST_SIZE = 11; // 2^16 / 32 = 2^11
	
	public PathIdPool(){
		pool = new LinkedList<Integer>();
	}
	
	/*
	 * update the special position of pool value
	 */
	public boolean updatePool(PathIdPoolProperty property){
		if(property.getPoolNum() > pool.size() - 1){
			return false;
		}
		pool.set(property.getPoolNum(), property.getPoolValue());
		return true;
	}
	
	/*
	 * allocate a new int to store 32 more number.
	 * by default, 1 will be allocated
	 */
	public boolean increasePool(){
		/*
		 * reach the largest size of the total pool 
		 */
		if(pool.size() == Math.pow(2, LARGEST_SIZE)){
			return false;
		}
		pool.add(new Integer(DEFAULT_ALLOCATE));
		return true;
	}
	
	public int nextId(){
		PathIdPoolProperty property = currentPool();
		
		if(property == null){
			boolean flag = increasePool();
			if(flag == false){
				return -1;
			} else {
				return (pool.size() - 1)* 32 + 1;//id start with 1
			}
		} else {
			int bitmap = property.getPoolValue();
			int id = nextIdInternal(bitmap);
			property.setPoolValue(bitmap | 1 << (id - 1));
			updatePool(property);
			return property.getPoolNum() * 32 + id;
		}
	}
	
	private int nextIdInternal(int bitmap){
		int newId = 0;
		bitmap = ~bitmap & (bitmap + 1);
		while(bitmap != 0){
			newId++;
			bitmap >>>= 1;
		}
		return newId;
	}
	
	public boolean removeId(int id){
		PathIdPoolProperty property = getPool(id);
		if(property == null){
			return false;
		}
		int bitmap = property.getPoolValue();
		if((bitmap & (1 << (id % 32 - 1))) == 0){
			return false;
		} else {
			pool.set(property.getPoolNum(), bitmap & ~(1 <<  (id % 32 - 1)));
			return true;
		}
	}
	
	/*
	 * free a int.
	 */
	public boolean reducePool(){
		/*
		 * nothing to free
		 */
		if(pool.size() == 0){
			return false;
		}
		/*
		 * free the biggest int
		 */
		pool.remove(pool.size() - 1);
		return true;
	}
	
	/*
	 * return the first int which can be allocated
	 */
	public PathIdPoolProperty currentPool(){
		int number = -1;
		for(int i = 0; i < pool.size(); i++){
			if(pool.get(i) != ALL_ONE){
				number = i;
				break;
			}
		}
		if(number == -1){
			return null;
		}
		int value = pool.get(number);
		PathIdPoolProperty property = new PathIdPoolProperty(number, value); 
		return property;
	}
	
	/*
	 * get the pool value at id position. caution, id start with 1 
	 */
	public PathIdPoolProperty getPool(int id){
		PathIdPoolProperty property = new PathIdPoolProperty();
		int number = (id - 1) / 32;
		if(number > pool.size() - 1){
			return null;
		}
		property.setPoolNum(number);
		property.setPoolValue(pool.get(number));
		return property;
	}
}

