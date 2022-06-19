package app.domain.model.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import app.domain.model.AdminProcess;

/**
 * @author Carlos Lopes <1211277@isep.ipp.pt>
 */
public class AdminProcList implements Serializable {

  private List<AdminProcess> adminProcList;

  public AdminProcList() {
    adminProcList = new ArrayList<>();
  }

  /**
   * Adds a new administration process to the list
   * 
   * @param adminProc the administration process to be added to the list
   */
  public void addAdminProc(AdminProcess adminProc) {
    adminProcList.add(adminProc);
  }

  /**
   * @return the list of administration processes
   */
  public List<AdminProcess> getList() {
    return adminProcList;
  }

  /**
   * @return the number of administration processes in the list
   */
  public int size() {
    return adminProcList.size();
  }
}
