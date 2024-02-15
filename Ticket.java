import java.util.Date;

public class Ticket {
    private int ticketNumber;
    private Date entryTime;

    public Ticket(int ticketNumber, Date entryTime) {
        this.ticketNumber = ticketNumber;
        this.entryTime = entryTime;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public Date getEntryTime() {
        return entryTime;
    }
}
