package app.domain.model;

import java.util.Calendar;
import app.domain.shared.CenterEventType;

public class CenterEvent {
    private Calendar date;
    private CenterEventType event;
    private SNSUser snsUser;

    public CenterEvent(Calendar date, CenterEventType event, SNSUser snsUser) {
        this.date = date;
        this.event = event;
        this.snsUser = snsUser;
    }
}
