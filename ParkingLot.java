import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private List<ParkingSlot> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        slots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            slots.add(new ParkingSlot(i + 1));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public List<ParkingSlot> getSlots() {
        return slots;
    }

    public ParkingSlot findNearestAvailableSlot() {
        for (ParkingSlot slot : slots) {
            if (!slot.isOccupied()) {
                return slot;
            }
        }
        return null;
    }
}
