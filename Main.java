import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ParkingLotManager parkingLotManager = null;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            String[] tokens = command.split(" ");

            switch (tokens[0]) {
                case "create_parking_lot":
                    int capacity = Integer.parseInt(tokens[1]);
                    parkingLotManager = new ParkingLotManager(capacity);
                    System.out.println("Created a parking lot with " + capacity + " slots");
                    break;

                case "park":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    String registrationNumber = tokens[1];
                    String colour = tokens[2];
                    System.out.println(parkingLotManager.parkCar(registrationNumber, colour));
                    break;

                case "leave":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    int slotNumber = Integer.parseInt(tokens[1]);
                    System.out.println(parkingLotManager.leave(slotNumber));
                    break;

                case "status":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    System.out.println("Slot No. Registration No Colour");
                    for (ParkingSlot slot : parkingLotManager.getParkingLot().getSlots()) {
                        if (slot.isOccupied()) {
                            Car car = slot.getCar();
                            System.out.println(slot.getSlotNumber() + " " + car.getRegistrationNumber() + " " + car.getColour());
                        }
                    }
                    break;

                case "registration_numbers_for_cars_with_colour":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    String colourFilter = tokens[1];
                    List<String> registrationNumbers = parkingLotManager.getRegistrationNumbersForCarsWithColour(colourFilter);
                    System.out.println(String.join(", ", registrationNumbers));
                    break;

                case "slot_number_for_registration_number":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    String registrationNumberFilter = tokens[1];
                    int slot = parkingLotManager.getSlotNumberForRegistrationNumber(registrationNumberFilter);
                    if (slot != -1) {
                        System.out.println(slot);
                    } else {
                        System.out.println("Not found");
                    }
                    break;

                case "slot_numbers_for_cars_with_colour":
                    if (parkingLotManager == null) {
                        System.out.println("Please create a parking lot first");
                        break;
                    }
                    String colourFilter2 = tokens[1];
                    List<Integer> slotNumbers = parkingLotManager.getSlotNumbersForCarsWithColour(colourFilter2);
                    System.out.println(slotNumbers.stream().map(String::valueOf).collect(Collectors.joining(", ")));
                    break;

                case "exit":
                    System.out.println("Exiting program");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
    }
}
