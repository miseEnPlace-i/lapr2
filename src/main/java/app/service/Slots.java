package app.service;

import app.domain.model.VaccinationCenter;

/**
 * Slots class that aims to help to manipulate the slots of a vaccination center.
 * 
 * @author Ricardo Moreira <1211285@isep.ipp.pt>
 */
public class Slots {
    /**
     * Calculates the number of slots a given center has. To calculate the number of slots, we need the difference between
     * the closing and opening hours. Then, we need to divide the difference by the slot duration.
     * 
     * @param center the center to calculate the number of slots.
     * @return the number of slots.
     */
    public static int calculateSlots(VaccinationCenter center) {
        String[] openingHours = center.getOpeningHours().split(":");
        String[] closingHours = center.getClosingHours().split(":");

        int openingMinutesOfDay =
                Integer.parseInt(openingHours[0]) * 60 + Integer.parseInt(openingHours[1]);
        int closingMinutesOfDay =
                Integer.parseInt(closingHours[0]) * 60 + Integer.parseInt(closingHours[1]);

        return (closingMinutesOfDay - openingMinutesOfDay) / center.getSlotDuration();
    }

    /**
     * Gets the index of a slot given a time.
     * TODO: implement
     */

    /**
     * Gets the time of a slot given an index.
     * TODO: implement
     */
}
