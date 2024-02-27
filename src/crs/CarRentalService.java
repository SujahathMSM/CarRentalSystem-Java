package crs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class CarRentalService {
    private List<Car> cars;
    private List<Customer> customers;
    private List<BookedCarInformation> bookedCarInformations;
    public CarRentalService(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        bookedCarInformations = new ArrayList<>();
    }
    public void addCar(Car car){
        cars.add(car);
    }
    public void addCustomer(Customer customer){
        customers.add(customer);
    }

    public void bookedCar(Car car, Customer customer, int days){
       if (car.getNoOfAvailableCars() > 0){
           car.setNoOfAvailableCars(car.getNoOfAvailableCars()-1);
           bookedCarInformations.add(new BookedCarInformation(car, customer, days));
       }else{
           System.out.println("No Cars available for booking");
       }
    }
    public void returnCar(Car car, BookedCarInformation bookedCarInformation){
        car.setNoOfAvailableCars(car.getNoOfAvailableCars()+1);
        bookedCarInformations.remove(bookedCarInformation);
    }

    public void options(){
        Scanner input = new Scanner(System.in);

        while (true) {
            System.out.println("======Welcome to Car Rental System======");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Available Car");
            System.out.println("4. Exit");

            System.out.println("Enter your choice: ");

            int choice = input.nextInt();
            input.nextLine();

            if (choice == 4){
                System.out.println("Thank you for choosing us");
                break;
            }

            switch (choice){
                case 1:
                    System.out.println("===For Car Renting Please Provide us following details");

                    System.out.println("Enter your name: ");
                    String customerName = input.nextLine();

                    System.out.println("Enter the Car ID: ");
                    String carId = input.nextLine();

                    System.out.println("Enter the number of days for rental: ");
                    int days = input.nextInt();

                    Customer customer = new Customer("CUSTOMER_"+customers.size()+1, customerName);
                    addCustomer(customer);

                    Optional<Car> optionalCar = cars.stream()
                            .filter(c -> c.getCarId().equalsIgnoreCase(carId) && c.getNoOfAvailableCars() > 0)
                            .findAny();

                    if(optionalCar.isEmpty()){
                        System.out.println("Car is not found, please try to book another car");
                        options();
                        return;
                    }

                    // Just print the type of each variable
                    // for example: print(type(A))

                    Car selectedCar = optionalCar.get();

                    System.out.println("===  Bill Receipt ===");
                    System.out.println("Customer ID: "+customer.getId());
                    System.out.println("Customer Name: "+customer.getName());
                    System.out.println("Car Brand: "+ selectedCar.getBrand());
                    System.out.println("Rental Days: "+days);
                    System.out.println("Total Price: "+selectedCar.calculatePrice(days));

                    System.out.println("Confirm Rental (Y/N)");
                    String confirmation = input.next();

                    if (confirmation.equalsIgnoreCase("Y")){
                        bookedCar(selectedCar, customer, days);
                        System.out.println("Booking is done successfully");
                    }else {
                        System.out.println("Car booking is cancelled");
                    }
                    break;
                case 2:
                    System.out.println("====Return a Car====");
                    System.out.println("Enter the car ID you want to return: ");
                    String carID = input.nextLine();

                    Optional<Car> optionalCar1 = cars.stream()
                            .filter(c -> c.getCarId().equals(carID))
                            .findAny();
                    if (optionalCar1.isEmpty()){
                        System.out.println("Please provide valid car details");
                        options();
                        return;
                    }
                    Car carToReturn = optionalCar1.get();

                    BookedCarInformation bookedCarInformation = bookedCarInformations.stream()
                            .filter(b -> b.getCar() == carToReturn).findFirst()
                            .orElse(null);
                    if (bookedCarInformation == null){
                        System.out.println("car information is not available");
                        options();
                        return;
                    }
                    Customer cust = bookedCarInformation.getCustomer();
                    returnCar(carToReturn, bookedCarInformation);
                    System.out.println("Car returned Successfully: "+ cust.getName());
                    break;
                case 3:
                    System.out.println("===Available Cars===");
                    cars.stream()
                            .filter(c -> c.getNoOfAvailableCars() > 0)
                            .forEach(car -> System.out.println(car.getCarId()+" - "+ car.getBrand()+" - "+ car.getModel()+" - "+ car.getNoOfAvailableCars()));
                    break;
                default:
                    System.out.println("Please Enter a valid option");
            }

        }
    }
}
