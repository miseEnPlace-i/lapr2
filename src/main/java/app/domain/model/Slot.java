package app.domain.model;

public class Slot {
  private int duration;
  private int maxVaccinesPerSlot;

  public Slot(int duration, int maxVaccinesPerSlot) {
    setDuration(duration);
    setMaxVaccinesPerSlot(maxVaccinesPerSlot);
  }

  public int getDuration() {
    return duration;
  }

  public int getMaxVaccinesPerSlot() {
    return maxVaccinesPerSlot;
  }

  private void setDuration(int duration) {
    if (duration <= 0) throw new IllegalArgumentException("Slot duration must be a positive integer");

    this.duration = duration;
  }

  private void setMaxVaccinesPerSlot(int maxVaccinesPerSlot) {
    if (maxVaccinesPerSlot <= 0) throw new IllegalArgumentException("Max vaccines per slot must be a positive integer");

    this.maxVaccinesPerSlot = maxVaccinesPerSlot;
  }
}
