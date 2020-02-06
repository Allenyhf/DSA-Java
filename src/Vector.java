import java.util.Arrays;

/**
 * 编写基于int类型的Vector
 * @author Allen
 *
 */



public class Vector {
	protected int capacity = Constant.DEFAUT_CAPACITY; //容量
	protected int size = 0;//实际元素个数
	protected int[] arr;//arr：存放元素的数组

	//构造函数
	//默认构造函数
	Vector() {
		arr = new int[capacity];
	}
	//指定容量
	Vector(int _capacity){
		//容量大于等于默认容量
		if(_capacity < Constant.DEFAUT_CAPACITY)
			this.capacity = Constant.DEFAUT_CAPACITY;
		else
			this.capacity = _capacity;
		arr = new int[capacity];
	}
	//数组区间复制
	Vector(int[] _arr, int lo, int hi) {
//		if(hi-lo+1 > Constant.DEFAUT_CAPACITY)
//			this.capacity = hi-lo+1;
//		else
//			this.capacity = Constant.DEFAUT_CAPACITY;
		this.size = hi-lo;
		//必要时扩容
		if(hi-lo > this.capacity)
		{
			expand(0);
		}
		arr = new int[this.capacity];
		copyFrom(_arr, lo, hi);
	}
	//vector复制
	Vector(Vector vec){
		this.size = vec.size;
		expand(0);
		arr = new int[this.capacity];
		copyFrom(vec.arr, 0, vec.size());
		
	}
	
	//当前规模
	int size() {
		return size;
	}
	//容量
	int getCapacity() {
		return this.capacity;
	}
	//r指向的元素
	int get(int r) {
		if(r<0 || r>=size) return -1; //r指向的元素不存在，返回-1
		return arr[r];
	}
	//获取填充因子
	float getLoadfactor() {
		return (float)this.size/(float)this.capacity;
	}
	//在[lo,hi)中查找
	//返回最后一个小于等于ele的元素的位置或-1
	int find(int ele, int lo, int hi) {
		int i = hi-1;
		while(i>=lo && this.arr[i]>ele) i--;
		//要么this.arr[i]<=ele，正确
		//要么 i<lo，即i=lo-1,查找失败，返回-1
		if(i < lo) return -1;
		return i;
	}
	//返回删除的元素个数
	int deduplicate() {
		int oldSize = this.size;//原始规模
		int toRm = 0;//待删除元素的位置
		//{0,0,1,2,1,3,4,4,5,9,9,5};
		for(int i=0; i<this.size; i++) {
			toRm = find(this.arr[i],1,this.size);
			if(this.arr[toRm] == this.arr[i] && toRm!=i)
			{
				remove(toRm);
				this.size--;
				System.out.println(Arrays.toString(this.arr));
			}	
		}
		return (oldSize-this.size);
	}
	//重写Arrays的toString方法
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(this.arr);
	}
	
	//复制数组
	void copyFrom(int[] _arr,int lo,int hi) {
		if(hi<=lo) return;
		for(int i= 0; i< hi-lo; i++) {
			this.arr[i] = _arr[i];
		}
	}
	//扩容：分摊复杂度O(1)	返回扩容后的容量capacity
	int expand(int oldSize) {
		if(this.size < this.capacity) {
			return this.capacity;
		}else {
			this.capacity <<=1; //两倍扩容
			int[] oldArr = this.arr;
			this.arr = new int[this.capacity];
			copyFrom(oldArr, 0, oldSize);
			return this.capacity;
		}
	}
	//插入元素
	//返回-1：插入失败  否则返回插入位置r
	int insert(int r,int ele) {
		//插入位置不合法
		if(r<0 || r>this.size)
			return -1;
		
		this.size += 1;
		//必要时，扩容
//		System.out.println(expand(this.size-1));
		expand(this.size-1);
		for(int i=this.size-1; i>r; i--) {
			this.arr[i] = this.arr[i-1];
		}
		this.arr[r] = ele;
		return r;//返回插入位置
	}
	//区间删除:返回删除元素的个数
	int remove(int lo,int hi) {
		if(lo>=hi) return 0;
		int rmSize = hi - lo;
		
		for(int i=0; i<hi-lo && hi<this.size; i++) {
			this.arr[lo+i] = this.arr[hi];
			hi++;
		}
		this.size -= rmSize;
		return rmSize;
	}
	//单元素删除:基于区间删除方法实现
	//返回删除元素的位置	返回-1，删除失败
	int remove(int r) {
		if(r>=this.size || r<0) return -1;
		remove(r, r+1);
		return r;
	}
}
