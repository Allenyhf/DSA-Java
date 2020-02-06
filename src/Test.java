/**
 * ≤‚ ‘vector¿‡
 * @author Allen
 *
 */


public class Test {

	public static void main(String[] args) {
		int[] myArr = {0,1,2,3,4,5,6,7,8};
		
//		Vector myVec = new Vector();
//		Vector myVec = new Vector(-1);
//		Vector myVec = new Vector(9);
//		Vector myVec = new Vector(10);
//		Vector myVec = new Vector(11);
		
		Vector myVec = new Vector(myArr,0,9);

		System.out.println("size:"+myVec.size());
		System.out.println("capacity:"+myVec.getCapacity());
		for(int i=0; i<myVec.size(); i++) {
			System.out.println(myVec.get(i));
		}
		System.out.println("*******");
		
		Vector vec2 = new Vector(myVec);
		System.out.println("size:"+vec2.size());
		System.out.println("capacity:"+vec2.getCapacity());
		for(int i=0; i<vec2.size(); i++) {
			System.out.println(vec2.get(i));
		}
		System.out.println(vec2.getLoadfactor());
		
		System.out.println("######");
		vec2.insert(9, 55);
		for(int i=0; i<vec2.size(); i++) {
			System.out.println(vec2.get(i));
		}
		System.out.println(vec2.size());
		System.out.println(vec2.capacity);
		System.out.println("$$$$$$$$$$$$");
		System.out.println(vec2.remove(4, 5));
		System.out.println(vec2.remove(9));
		System.out.println(vec2.remove(8));
		for(int i=0; i<vec2.size(); i++) {
			System.out.println(vec2.get(i));
		}
	}
	
}
