package app.domain.model.list;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.SNSUser;

/**
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class WaitingRoomList {
    private List<SNSUser> waitingRoomList;

    /**
     * Constructor for the WaitingRoomList class.
     */
    public WaitingRoomList() {
        waitingRoomList = new ArrayList<>();
    }

    /**
     * Adds a SNSUser to the waiting room list.
     * 
     * @param user The SNSUser to be added to the waiting room list.
     */
    public void addUser(SNSUser user) {
        waitingRoomList.add(user);
    }

    /**
     * Removes a SNSUser from the waiting room list.
     */
    public void removeUser(SNSUser user) {
        waitingRoomList.remove(user);
    }

    /**
     * Returns the waiting room list.
     * 
     * @return The waiting room list.
     */
    public List<SNSUser> getList() {
        return waitingRoomList;
    }

    /**
     * Returns the size of the waiting room list.
     *
     * @return The size of the waiting room list.
     */
    public int getSize() {
        return waitingRoomList.size();
    }
}
