package pathIdAllocation;

import java.util.HashMap;
import java.util.Map;

public class PathIdGenerator {
	private static Map<PathIdGeneratorKey, PathIdPool> pathIdMgmt = new HashMap<PathIdGeneratorKey, PathIdPool>();
	
	public PathIdGenerator(){
		
	}
	
	public static int nextId(int src, int dst){
		PathIdGeneratorKey key = new PathIdGeneratorKey(src, dst);
		PathIdPool pool = pathIdMgmt.get(key);
		if(pool == null){
			pool = new PathIdPool();
			pool.increasePool();
			pathIdMgmt.put(key, pool);
			return 1;
		} else {
			return pool.nextId();
		}
	}
	
	public static boolean removeId(int src, int dst, int id){
		PathIdGeneratorKey key = new PathIdGeneratorKey(src, dst);
		PathIdPool pool = pathIdMgmt.get(key);
		if(pool == null){
			return false;
		}
		return pool.removeId(id);
	}
	
	public static void main(String args[]){
		int src1 = 1;
		int dst1 = 2;
		int src2 = 11;
		int dst2 = 12;
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.removeId(src1, dst1, 2));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.removeId(src1, dst1, 3));
		System.out.println(PathIdGenerator.removeId(src1, dst1, 5));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.nextId(src1, dst1));
		System.out.println(PathIdGenerator.removeId(src2, dst2, 2));

		for(int i = 0; i < 100; i++){
			System.out.println(PathIdGenerator.nextId(src2, dst2));
		}
		for(int i = 31; i < 68; i++){
			System.out.println(PathIdGenerator.removeId(src2, dst2, i));
		}
		System.out.println(PathIdGenerator.removeId(src2, dst2, 101));
		System.out.println(PathIdGenerator.removeId(src2, dst2, 97));
		for(int i = 0; i < 100; i++){
			System.out.println(PathIdGenerator.nextId(src2, dst2));
		}
		
	}
}
