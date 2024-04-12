package com.jspiders.cardekhousinghibernet.dao;

import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.jspiders.cardekhousinghibernet.dto.CarDTO;

public class CarDAO {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;
	private static EntityTransaction entityTransaction;

	static Scanner scanner = new Scanner(System.in);

	static CarDTO car = new CarDTO();
	
	public static void main(String[] args) {
		boolean flag1 = true;
		
		System.out.println("* * * WELCOME TO CAR DEKHO APPLICATION * * * ");
		System.out.println("============================================\n");
		
		openConnection();
		entityTransaction.begin();
		
		while (flag1) {
			System.out
					.println("\nCHOOSE ANY NUMBER \n \n1. Add Car \n2. View All Car \n3. Search Car \n4. Edit Car \n5. Remove Car \n6. Exit");
			int option = scanner.nextInt();

			switch (option) {
			case 1:
				insertCar();
				entityManager.persist(car);
				flag1 = false;
				break;
				
				
			case 2:
				viewAllCar();
				flag1 = false;
				break;
				
			case 3:
				searchCar();
				flag1 = false;
				break;	
				
			case 4:
				CarDTO car = editCar();
				entityManager.persist(car);
				flag1 = false;
				break;
				
			case 5:
				removeCar();
				flag1 = false;
				break;	
				
			case 6:
				flag1 = false;
				break;
				
			default:
				System.out.println("invalid number try again other wise enter 6 for exit");
				main(null);
				break;
			}
		}
		entityTransaction.commit();
		closeConnection();
	}
	
	private static void insertCar() {

		scanner.nextLine();
		System.out.println("Enter Car ID");
		int id = scanner.nextInt();
		scanner.nextLine();
		
		System.out.println("Enter Car Name");
		String name = scanner.nextLine();

		System.out.println("Enter Car Colour");
		String colour = scanner.nextLine();

		System.out.println("Enter Car FuleType \n1. Petrol \n2. Diesel");
		int num = scanner.nextInt();
		boolean flag2 = true;
		String fuleType = "";
		while (flag2) {

			switch (num) {
			case 1:
				fuleType = "Petrol";
				flag2 = false;
				break;

			case 2:
				fuleType = "Diesel";
				flag2 = false;
				break;

			default:
				System.out.println("Invalid Number \n Enter Car FuleType \\n1. Petrol \\n2. Diesel");
				break;
			}
		}

		System.out.println("Enter Car Price");
		Double price = scanner.nextDouble();

		car.setId(id);
		car.setName(name);
		car.setColour(colour);
		car.setFuleType(fuleType);
		car.setPrice(price);
		
	}
	private static void viewAllCar() {

		Query query = entityManager.createQuery( "SELECT car FROM CarDTO car");
		@SuppressWarnings("unchecked")
		List<CarDTO> cars = query.getResultList();
		for(CarDTO car2 : cars) {
			System.out.println(car2);
		}
		
	}
	private static void searchCar() {

		boolean flag = true;
		
		while (flag) {
			
			System.out.println(" 1. Search by ID \n 2. Search by Name \n 3. Search by FuleType \n 4. Go Back");
			int num = scanner.nextInt();
			
			switch (num) {
			
			case 1:
				System.out.println("Enter Id");
				int id = scanner.nextInt();
				CarDTO car = entityManager.find(CarDTO.class, id);
				System.out.println(car);
				break;

			case 2:
				
				System.out.println("Enter Name");
				scanner.nextLine();
				String name =  scanner.nextLine();
				
				Query query = entityManager.createQuery("SELECT car FROM CarDTO car WHERE car.name = ?1");
				query.setParameter(1, name);
				
				@SuppressWarnings("unchecked")
				List<CarDTO> result = query.getResultList();
				
				for(CarDTO car2 : result) {
					System.out.println(car2.toString());
				}
				
				
//				String jpql = "SELECT car FROM CarDTO car WHERE car.name = :name";
//				@SuppressWarnings("unchecked")
//				List<CarDTO> resultList = entityManager.createQuery(jpql)
//                        .setParameter("name", name)
//                        .getResultList();
//				
//				for(CarDTO cars2 : resultList) {
//				    System.out.println(cars2);
//				}
				break;

			case 3:
				
				System.out.println("Enter Car FuleType \n1. Petrol \n2. Diesel");
				num = scanner.nextInt();
				boolean flag2 = true;
				String fuleType = "";
				while (flag2) {

					switch (num) {
					case 1:
						fuleType = "Petrol";
						flag2 = false;
						break;

					case 2:
						fuleType = "Diesel";
						flag2 = false;
						break;

					default:
						System.out.println("Invalid Number \n Enter Car FuleType \\n1. Petrol \\n2. Diesel");
						break;
					}
				}
				
				Query query2 = entityManager.createQuery("SELECT car FROM CarDTO car WHERE car.fuleType = ?1");
				query2.setParameter(1, fuleType);
				
				@SuppressWarnings("unchecked") 
				List<CarDTO> cars = query2.getResultList();
				for(CarDTO car2 : cars) {
					System.out.println(car2);
				}
				
				break;

			case 4:
				main(null);
				break;

			default:
				System.out.println("invalid number");
				break;
			}
		}
		
	}
	private static CarDTO editCar() {
		
		System.out.println("Enter Your Car ID for Edit Record");
		int id = scanner.nextInt();
		
		System.out.println("Choose Anyone for Update \n1. Name \n2. Colour \n3. FuleType \n4. Price \n5. Go Back");
		int num = scanner.nextInt();
		
		CarDTO car = new CarDTO();
		switch (num) {
		case 1:
			System.out.println("Enter New Name");
			scanner.nextLine();
			String name = scanner.nextLine();
			car = entityManager.find(CarDTO.class, id);
			car.setName(name);
			
			break;
		
		case 2:
			System.out.println("Enter Colour");
			scanner.nextLine();
			String colour = scanner.nextLine();
			car = entityManager.find(CarDTO.class, id);
			car.setColour(colour);
			
			
		case 3:
			
			System.out.println("Enter Car FuleType \n1. Petrol \n2. Diesel");
			num = scanner.nextInt();
			boolean flag2 = true;
			String fuleType = "";
			while (flag2) {

				switch (num) {
				case 1:
					fuleType = "Petrol";
					flag2 = false;
					break;

				case 2:
					fuleType = "Diesel";
					flag2 = false;
					break;

				default:
					System.out.println("Invalid Number \n Enter Car FuleType \\n1. Petrol \\n2. Diesel");
					break;
				}
			}
			car = entityManager.find(CarDTO.class, id);
			car.setFuleType(fuleType);
			
			break;
			
		case 4:
			System.out.println("Enter Price");
			scanner.nextLine();
			Double price = scanner.nextDouble();
			car = entityManager.find(CarDTO.class, id);
			car.setPrice(price);
			
			break;
			
		case 5:
			main(null);
			break;
			
		default:
			break;
		}
		return car;
	}
	private static void removeCar() {
		
		System.out.println("Enter Your Car ID for Remove");
		int id = scanner.nextInt();
		
		CarDTO car = entityManager.find(CarDTO.class, id);
		entityManager.remove(car);
		
	}
	
	private static void openConnection() {

		entityManagerFactory = Persistence.createEntityManagerFactory("cardekhousinghibernet");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
	}
	private static void closeConnection() {

		if (entityManagerFactory != null) {
			entityManagerFactory.close();

		}

		if (entityManager != null) {
			entityManager.close();

		}

		if (entityTransaction != null) {
			if (entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}

	}

}
