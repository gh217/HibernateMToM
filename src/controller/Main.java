package controller;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Car;
import model.Colour;

import java.util.Map;
import java.util.TreeMap;

public class Main {

	static SessionFactory factory=new Configuration()
			.configure("Config.xml")
			.addAnnotatedClass(Car.class)
			.addAnnotatedClass(Colour.class)
			.buildSessionFactory();
	
	static Session session=factory.getCurrentSession();
	
	public static void main(String[] args) {
		
		try {
			
			

			session.beginTransaction();
			
//			add1();
//			getByCar();
//			getByColour();
		delete();
			
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
	public static void add1() {
		
		Car c1=new Car(); c1.setName("car1");
		Car c2=new Car(); c2.setName("car2");
		Car c3=new Car(); c3.setName("car3");
		
		
		Colour co1 = new Colour(); co1.setName("red");
		Colour co2 = new Colour(); co2.setName("black");
		Colour co3 = new Colour(); co3.setName("white");
		
		c1.getColour().add(co1);
		c1.getColour().add(co2);
		c1.getColour().add(co3);
		
		c2.getColour().add(co1);
		c2.getColour().add(co2);
		c2.getColour().add(co3);
		
		c3.getColour().add(co1);
		c3.getColour().add(co2);
		c3.getColour().add(co3);
		
		
		/// if do not use all 
		session.save(co1);
		session.save(co2);
		session.save(co3);
		
		session.save(c1);
		session.save(c2);
		session.save(c3);
		
	}
	
	public static void add2() {
		Car c1=new Car(); c1.setName("car1");
		Car c2=new Car(); c2.setName("car2");
		Car c3=new Car(); c3.setName("car3");
		
		
		Colour co1 = new Colour(); co1.setName("red");
		Colour co2 = new Colour(); co2.setName("black");
		Colour co3 = new Colour(); co3.setName("white");
		
		
		co1.getCar().add(c1); co1.getCar().add(c2); co1.getCar().add(c3);
		
		co2.getCar().add(c1); co2.getCar().add(c2); co2.getCar().add(c3);
		
		co3.getCar().add(c1); co3.getCar().add(c2); 
		
		session.save(co1);
		session.save(co2);
		session.save(co3);
		
	}
	
	public static boolean getByCar() {
		
		try {
			int carId=2;
			
			Car car=session.get(Car.class, carId);

			for(Colour c : car.getColour()) {
				System.out.println(c.getName()+" "+c.getId());
			}
			return true;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}

	public static boolean getByColour() {
		
		try {
			int colourId=4;
			Colour col=session.get(Colour.class, colourId);
			System.out.println(col.getName()+" "+col.getId());
			
			for(Car c : col.getCar()) {
				System.out.println(c.getName()+" "+c.getId());
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static void delete() {
		int carId=20;
		
		Car car=session.get(Car.class, carId);
		session.delete(car);
		
	}
}
