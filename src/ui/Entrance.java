package ui;

class A {
	public int a;
	public A(int a) {
		this.a = a;
	}
	
	public void f() {
		System.out.println(a);
	}
}
public class Entrance {
	public static void main(String[] argv) {
		/*
		Map <Integer, A> map  = new TreeMap<Integer, A>();
		
		for (int i=0; i<100; i++) {
			map.put(i, new A(i));
		}
		
		for (int i=0; i<100; i++) {
			A a = map.get(i);
			a.a *= 2;
		}
		
		for (int i=0; i<100; i++) {
			map.get(i).f();
		}
		*/
		new Mainframe().CreateUI();
	}
}
