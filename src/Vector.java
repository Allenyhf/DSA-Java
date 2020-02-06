import java.util.Arrays;

/**
 * ��д����int���͵�Vector
 * @author Allen
 *
 */



public class Vector {
	protected int capacity = Constant.DEFAUT_CAPACITY; //����
	protected int size = 0;//ʵ��Ԫ�ظ���
	protected int[] arr;//arr�����Ԫ�ص�����

	//���캯��
	//Ĭ�Ϲ��캯��
	Vector() {
		arr = new int[capacity];
	}
	//ָ������
	Vector(int _capacity){
		//�������ڵ���Ĭ������
		if(_capacity < Constant.DEFAUT_CAPACITY)
			this.capacity = Constant.DEFAUT_CAPACITY;
		else
			this.capacity = _capacity;
		arr = new int[capacity];
	}
	//�������临��
	Vector(int[] _arr, int lo, int hi) {
//		if(hi-lo+1 > Constant.DEFAUT_CAPACITY)
//			this.capacity = hi-lo+1;
//		else
//			this.capacity = Constant.DEFAUT_CAPACITY;
		this.size = hi-lo;
		//��Ҫʱ����
		if(hi-lo > this.capacity)
		{
			expand(0);
		}
		arr = new int[this.capacity];
		copyFrom(_arr, lo, hi);
	}
	//vector����
	Vector(Vector vec){
		this.size = vec.size;
		expand(0);
		arr = new int[this.capacity];
		copyFrom(vec.arr, 0, vec.size());
		
	}
	
	//��ǰ��ģ
	int size() {
		return size;
	}
	//����
	int getCapacity() {
		return this.capacity;
	}
	//rָ���Ԫ��
	int get(int r) {
		if(r<0 || r>=size) return -1; //rָ���Ԫ�ز����ڣ�����-1
		return arr[r];
	}
	//��ȡ�������
	float getLoadfactor() {
		return (float)this.size/(float)this.capacity;
	}
	//��[lo,hi)�в���
	//�������һ��С�ڵ���ele��Ԫ�ص�λ�û�-1
	int find(int ele, int lo, int hi) {
		int i = hi-1;
		while(i>=lo && this.arr[i]>ele) i--;
		//Ҫôthis.arr[i]<=ele����ȷ
		//Ҫô i<lo����i=lo-1,����ʧ�ܣ�����-1
		if(i < lo) return -1;
		return i;
	}
	//����ɾ����Ԫ�ظ���
	int deduplicate() {
		int oldSize = this.size;//ԭʼ��ģ
		int toRm = 0;//��ɾ��Ԫ�ص�λ��
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
	//��дArrays��toString����
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return Arrays.toString(this.arr);
	}
	
	//��������
	void copyFrom(int[] _arr,int lo,int hi) {
		if(hi<=lo) return;
		for(int i= 0; i< hi-lo; i++) {
			this.arr[i] = _arr[i];
		}
	}
	//���ݣ���̯���Ӷ�O(1)	�������ݺ������capacity
	int expand(int oldSize) {
		if(this.size < this.capacity) {
			return this.capacity;
		}else {
			this.capacity <<=1; //��������
			int[] oldArr = this.arr;
			this.arr = new int[this.capacity];
			copyFrom(oldArr, 0, oldSize);
			return this.capacity;
		}
	}
	//����Ԫ��
	//����-1������ʧ��  ���򷵻ز���λ��r
	int insert(int r,int ele) {
		//����λ�ò��Ϸ�
		if(r<0 || r>this.size)
			return -1;
		
		this.size += 1;
		//��Ҫʱ������
//		System.out.println(expand(this.size-1));
		expand(this.size-1);
		for(int i=this.size-1; i>r; i--) {
			this.arr[i] = this.arr[i-1];
		}
		this.arr[r] = ele;
		return r;//���ز���λ��
	}
	//����ɾ��:����ɾ��Ԫ�صĸ���
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
	//��Ԫ��ɾ��:��������ɾ������ʵ��
	//����ɾ��Ԫ�ص�λ��	����-1��ɾ��ʧ��
	int remove(int r) {
		if(r>=this.size || r<0) return -1;
		remove(r, r+1);
		return r;
	}
}
