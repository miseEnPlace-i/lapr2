package app.domain.model.list;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import app.domain.model.CenterEvent;
import app.domain.model.SNSUser;
import app.domain.shared.CenterEventType;

public class CenterEventList {
    private List<CenterEvent> events;

    public CenterEventList() {
        this.events = new ArrayList<CenterEvent>();
    }

    public CenterEvent create(Calendar date, CenterEventType event, SNSUser snsUser) {
        CenterEvent centerEvent = new CenterEvent(date, event, snsUser);
        this.events.add(centerEvent);
        return centerEvent;
    }

    public void save(CenterEvent event) {
        this.events.add(event);
    }

    public int size() {
        return this.events.size();
    }
}
