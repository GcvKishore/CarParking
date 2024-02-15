import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParkingLotManager {
    private ParkingLot parkingLot;
    private int ticketCounter;
    private double totalRevenue;
    private double totalDuration;
    private int parkedCars;

    public ParkingLotManager(int capacity) {
        parkingLot = new ParkingLot(capacity);
        ticketCounter = 1;
        totalRevenue = 0;
        totalDuration = 0;
        parkedCars = 0;
    }

    public String parkCar(String registrationNumber, String colour) {
        ParkingSlot nearestSlot = parkingLot.findNearestAvailableSlot();
        if (nearestSlot != null) {
            nearestSlot.parkCar(new Car(registrationNumber, colour));
            Ticket ticket = new Ticket(ticketCounter++, new Date());
            return "Allocated slot number: " + nearestSlot.getSlotNumber() + ", Ticket number: " + ticket.getTicketNumber();
        } else {
            return "Sorry, parking lot is full";
        }
    }

    public String leave(int slotNumber) {
        if (slotNumber < 1 || slotNumber > parkingLot.getCapacity()) {
            return "Invalid slot number";
        }

        ParkingSlot slot = parkingLot.getSlots().get(slotNumber - 1);
        if (slot.isOccupied()) {
            slot.removeCar();
            Ticket ticket = new Ticket(slotNumber, new Date()); // Dummy ticket for calculation
            long duration = (new Date().getTime() - ticket.getEntryTime().getTime()) / (60 * 1000);
            double fee = calculateParkingFee(duration);
            totalRevenue += fee;
            totalDuration += duration;
            parkedCars++;
            return "Slot number " + slotNumber + " is free. Parking duration: " + duration + " minutes. Parking fee: $" + fee;
        } else {
            return "Slot number " + slotNumber + " is already empty";
        }
    }

    public double calculateParkingFee(long duration) {
        // Assuming $1 per hour parking fee
        return duration / 60.0;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public double getAverageParkingDuration() {
        return totalDuration / parkedCars;
    }

    public String getPeakParkingHours() {
        // Assume peak hours are from 12 PM to 2 PM
        return "Peak parking hours: 12 PM to 2 PM";
    }

    public void captureSecurityCameraImage(String eventType, Car car) {
        System.out.println("Security camera image captured for " + eventType + " event: " + car.getRegistrationNumber());
    }

    public List<String> getRegistrationNumbersForCarsWithColour(String colour) {
        List<String> registrationNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (slot.isOccupied() && slot.getCar().getColour().equalsIgnoreCase(colour)) {
                registrationNumbers.add(slot.getCar().getRegistrationNumber());
            }
        }
        return registrationNumbers;
    }

    public int getSlotNumberForRegistrationNumber(String registrationNumber) {
        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (slot.isOccupied() && slot.getCar().getRegistrationNumber().equalsIgnoreCase(registrationNumber)) {
                return slot.getSlotNumber();
            }
        }
        return -1;
    }

    public List<Integer> getSlotNumbersForCarsWithColour(String colour) {
        List<Integer> slotNumbers = new ArrayList<>();
        for (ParkingSlot slot : parkingLot.getSlots()) {
            if (slot.isOccupied() && slot.getCar().getColour().equalsIgnoreCase(colour)) {
                slotNumbers.add(slot.getSlotNumber());
            }
        }
        return slotNumbers;
    }

    public ParkingLot getParkingLot() {
        return parkingLot;
    }
}
