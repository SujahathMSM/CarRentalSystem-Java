package crs;

public class CRSEntry {
    public static void main(String[] args) {
        Car toyotaCar = new Car();
        toyotaCar.setCarId("CAR-1");
        toyotaCar.setBrand("Toyota");
        toyotaCar.setModel("Fortuner");
        toyotaCar.setNoOfAvailableCars(1);
        toyotaCar.setPricePerDay(3000);

        Car tataCar = new Car();
        tataCar.setCarId("CAR-2");
        tataCar.setBrand("Tata");
        tataCar.setModel("Harier");
        tataCar.setNoOfAvailableCars(3);
        tataCar.setPricePerDay(2500);

        Car mahindraCar = new Car();
        mahindraCar.setCarId("CAR-3");
        mahindraCar.setBrand("Mahindra");
        mahindraCar.setModel("XUV");
        mahindraCar.setNoOfAvailableCars(12);
        mahindraCar.setPricePerDay(3500);

        CarRentalService carRentalService = new CarRentalService();
        carRentalService.addCar(toyotaCar);
        carRentalService.addCar(tataCar);
        carRentalService.addCar(mahindraCar);

        carRentalService.options();

    }
}
