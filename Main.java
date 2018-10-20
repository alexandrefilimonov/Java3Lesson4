package ru.geekbrains.Lesson5;
import java.util.concurrent.*;

public class Main {
	int max = 5;
	int iA=0;
	int iB=0;
	int iC=0;
	public static void main ( String [] args ) {
		Main e1 = new Main ();
		new Thread(() -> e1.method1()).start();

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		executorService.execute(new Runnable() {
			public void run() {
				System.out.println("Asynch");
			}
		});
		executorService.shutdown();
	}
	
	public synchronized void method1() {

		if (iA==max ) return;
		else {
			//System.out.println(iA+"A" );
			System.out.print("A" );
			iA++;
			try {
				notify();
				new Thread(() -> method2()).start();
				if (iA==max ) return;
				wait();
			}
			catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void method2() {
		if (iB==max ) return;
		else {
			//System.out.println(iB+"B" );
			System.out.print("B" );
			iB++;
			try {
				notify();
				new Thread(() -> method3()).start();
				if (iB==max ) return;
				wait();
			}
			catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
		
	public synchronized void method3() {
		if (iC==max ) return;
		else {
			//System.out.println(iC+"C" );
			System.out.print("C" );
			iC++;
			try {
				notify();
				new Thread(() -> method1()).start();
				if (iC==max ) return;
				wait();
			}
			catch ( InterruptedException e ) {
				e.printStackTrace();
			}
		}
	}
}







