
package com.pluralsight;

import java.util.Scanner;

public class RentalCarCalculator {

    public static void main(String[] args) {

        // STEP 1: Create Scanner and rental prices
        Scanner scnr = new Scanner(System.in);

        final double DAILY_RATE = 29.99;
        final double TOLL_RATE = 3.95;
        final double GPS_RATE = 2.95;
        final double ROADSIDE_RATE = 3.95;

        // STEP 2: Display welcome message
        System.out.println("==================================");
        System.out.println("   WELCOME TO FUTURE CAR RENTALS");
        System.out.println("==================================");

        // STEP 3: Collect customer information
        System.out.print("\nPickup date (MM/dd/yyyy): ");
        String pickupDate = scnr.nextLine().trim();

        System.out.print("How many days are you renting? ");
        int numberOfDays = scnr.nextInt();
        scnr.nextLine(); // clear leftover enter key

        // Check that the rental duration is valid
        if (numberOfDays <= 0) {
            System.out.println("Rental days must be greater than 0.");
            scnr.close();
            return; // Stop main()
        }

        // STEP 4: Ask about optional add-ons
        System.out.print("Electronic toll tag ($3.95/day)? (yes/no): ");
        String tollAnswer = scnr.nextLine().trim();

        System.out.print("GPS ($2.95/day)? (yes/no): ");
        String gpsAnswer = scnr.nextLine().trim();

        System.out.print("Roadside assistance ($3.95/day)? (yes/no): ");
        String roadsideAnswer = scnr.nextLine().trim();

        // STEP 5: Ask for the driver's age
        System.out.print("How old are you? ");
        int driverAge = scnr.nextInt();

        if (driverAge <= 0) {
            System.out.println("Please enter a valid age.");
            scnr.close();
            return;
        }

        // STEP 6: Calculate the basic rental price
        double basicRentalCost =
                calcCarRental(numberOfDays, DAILY_RATE);

        // STEP 7: Calculate selected add-ons
        double optionsCost = calcOptionsCost(
                tollAnswer,
                gpsAnswer,
                roadsideAnswer,
                numberOfDays,
                TOLL_RATE,
                GPS_RATE,
                ROADSIDE_RATE
        );

        // STEP 8: Calculate underage surcharge
        double underageCost =
                calcUnderageSurcharge(driverAge, basicRentalCost);

        // STEP 9: Calculate final rental price
        double totalCost =
                calcTotalCost(basicRentalCost, optionsCost, underageCost);

        // STEP 10: Print rental receipt
        displayInfo(
                pickupDate,
                numberOfDays,
                basicRentalCost,
                optionsCost,
                underageCost,
                totalCost
        );

        scnr.close(); // Close Scanner
    }


    // METHOD 1: Calculate basic rental cost
    public static double calcCarRental(int days, double dailyRate) {

        double rentalCost = days * dailyRate;

        return rentalCost;
    }


    // METHOD 2: Calculate additional options
    public static double calcOptionsCost(
            String toll, String gps, String roadside,
            int days, double tollRate, double gpsRate,
            double roadsideRate) {

        double dailyOptionsCost = 0.0;

        // Add toll price if customer says yes
        if (toll.equalsIgnoreCase("yes")) {
            dailyOptionsCost += tollRate;
        }

        // Add GPS price if customer says yes
        if (gps.equalsIgnoreCase("yes")) {
            dailyOptionsCost += gpsRate;
        }

        // Add roadside price if customer says yes
        if (roadside.equalsIgnoreCase("yes")) {
            dailyOptionsCost += roadsideRate;
        }

        // Multiply selected daily costs by rental days
        double totalOptionsCost = dailyOptionsCost * days;

        return totalOptionsCost;
    }


    // METHOD 3: Calculate underage surcharge
    public static double calcUnderageSurcharge(
            int age, double rentalCost) {

        double surcharge = 0.0;

        // Drivers under 25 pay 30% of basic rental cost
        if (age < 25) {
            surcharge = rentalCost * 0.30;
        }

        return surcharge;
    }


    // METHOD 4: Calculate total rental price
    public static double calcTotalCost(
            double rentalCost, double optionsCost,
            double underageCost) {

        double total = rentalCost + optionsCost + underageCost;

        return total;
    }


    // METHOD 5: Display the final receipt
    public static void displayInfo(
            String pickupDate, int days,
            double rentalCost, double optionsCost,
            double underageCost, double totalCost) {

        System.out.println();
        System.out.println("==================================");
        System.out.println("       YOUR RENTAL RECEIPT");
        System.out.println("==================================");

        System.out.println("Pickup date: " + pickupDate);
        System.out.println("Rental days: " + days);

        System.out.printf("Basic rental:       $%.2f%n", rentalCost);
        System.out.printf("Optional add-ons:   $%.2f%n", optionsCost);
        System.out.printf("Underage surcharge: $%.2f%n", underageCost);

        System.out.println("----------------------------------");
        System.out.printf("TOTAL RENTAL COST:  $%.2f%n", totalCost);
        System.out.println("==================================");
        System.out.println("Thank you for renting with us!");
    }
}















