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
		new Mainframe().CreateUI();
	}
}
