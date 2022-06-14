package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecoveryRoom implements Serializable {
  private List<VaccineAdministration> recoveryRoom;

  public RecoveryRoom() {
    this.recoveryRoom = new ArrayList<VaccineAdministration>();
  }

  public void addVaccineAdministration(VaccineAdministration vaccineAdministration) {
    this.recoveryRoom.add(vaccineAdministration);

    setSMSSending(vaccineAdministration);
  }

  public int size() {
    return recoveryRoom.size();
  }

  private void setSMSSending(VaccineAdministration vaccineAdministration) {

  }
}
