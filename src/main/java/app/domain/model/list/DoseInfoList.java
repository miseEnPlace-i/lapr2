package app.domain.model.list;

import java.util.ArrayList;
import java.util.List;
import app.domain.model.DoseInfo;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class DoseInfoList {
    private List<DoseInfo> doseInfoList;

    public DoseInfoList() {
        doseInfoList = new ArrayList<>();
    }

    public void addDoseInfo(DoseInfo doseInfo) {
        doseInfoList.add(doseInfo);
    }

    public List<DoseInfo> getList() {
        return doseInfoList;
    }

    public DoseInfo getDoseInfoByDoseNumber(int doseNumber) {
        return doseInfoList.get(doseNumber - 1);
    }
}
