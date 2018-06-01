package serverapp;


public class ProducerConsumer {

	public static void main(String args[]){
		
		Producer obj1=new Producer();
		Consumer obj2 =new Consumer(obj1);
	
                Thread t1=new Thread(obj1);
                Thread t2=new Thread(obj2);
                
                t1.start();
                t2.start();
                
	}
	
	
}
class Producer extends Thread{
	
	
	StringBuffer sb;
	boolean s=true;
	
	Producer(){
		
		sb =new StringBuffer();
		
	}
	public void run(){
		
		for(int i=0;i<10;i++){
			
			try{
				sb.append(i+":");
				Thread.sleep(100);
				System.out.println("Producing Data");
				
			}catch(Exception e){
				
				
			}
			
		}
		s=false;
		
		
	}
	
	
}
class Consumer extends Thread{
	
	Producer prod;

	Consumer(Producer prod){
		
		this.prod=prod;
		
	}
	
	public void run(){
		
		try{
			
			while(prod.s){
				
				Thread.sleep(10);
			}
			
		}catch(Exception e){
			
			
		}
		System.out.println("Consuming Data "+prod.sb);
		
	}
	
}
