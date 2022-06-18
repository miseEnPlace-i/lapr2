package app.controller;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import app.domain.model.CSVReader;
import app.domain.model.Company;
import app.domain.model.LegacyData;
import app.domain.model.LegacyDataObjectBuilder;
import app.domain.model.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.model.list.AppointmentScheduleList;
import app.domain.model.list.CenterEventList;
import app.domain.model.list.VaccineAdministrationList;
import app.domain.model.store.SNSUserStore;
import app.dto.ArrivalCompare;
import app.dto.DepartureCompare;
import app.dto.LegacyDataDTO;
import app.exception.NotFoundException;
import app.mapper.LegacyDataMapper;
import app.service.sorting.ISortStrategy;
import app.service.sorting.SortFactory;

public class ImportLegacyDataController {
  private Company company;
  private VaccinationCenter center;
  private SNSUserStore snsUserStore;

  public ImportLegacyDataController(Company company, VaccinationCenter center) {
    this.company = company;
    this.center = center;
    this.snsUserStore = this.company.getSNSUserStore();
  }

  public List<String[]> read(String filepath)
      throws ClassNotFoundException, InstantiationException, IllegalAccessException, FileNotFoundException, NoSuchMethodException, InvocationTargetException {
    CSVReader csvReader = new CSVReader(filepath);
    return csvReader.read();
  }

  public List<LegacyDataDTO> convert(List<String[]> fileData) throws ParseException {
    List<LegacyDataDTO> legacyDtoList = new ArrayList<LegacyDataDTO>();

    for (String[] d : fileData) {
      LegacyDataDTO dto = LegacyDataMapper.toDto(d);
      legacyDtoList.add(dto);
    }

    return legacyDtoList;
  }

  public void validate(List<LegacyDataDTO> legacyDtoList) throws NotFoundException {
    for (LegacyDataDTO d : legacyDtoList) {
      String snsNumber = d.getSnsNumber();
      if (this.snsUserStore.findSNSUserByNumber(snsNumber) == null) throw new NotFoundException("SNS User w/ number " + snsNumber);
    }
  }

  public double sort(List<LegacyDataDTO> legacyDtoList, boolean isArrival, boolean isAsc) {
    ISortStrategy sortStrategy = SortFactory.getSortStrategy();
    Comparator<LegacyDataDTO> comparator = isArrival ? new ArrivalCompare() : new DepartureCompare();
    long startTime = System.nanoTime();
    sortStrategy.doSort(legacyDtoList, comparator);
    long endTime = System.nanoTime();
    if (!isAsc) Collections.reverse(legacyDtoList);
    return (endTime - startTime) / Math.pow(10, 6);
  }

  public void save(List<LegacyDataDTO> legacyDtoList) {
    AppointmentScheduleList aptSchList = this.center.getAppointmentList();
    WaitingRoom waitingRoom = this.center.getWaitingRoom();
    CenterEventList centerEventList = this.center.getEvents();

    for (LegacyDataDTO d : legacyDtoList) {
      LegacyData legacyData = LegacyDataMapper.toModel(d, this.center);
      LegacyDataObjectBuilder builder = new LegacyDataObjectBuilder(legacyData);
      VaccineAdministrationList administrationList = legacyData.getSNSUser().getUserHealthData().getVaccineAdministrationList();

      aptSchList.saveAppointment(builder.createAppointment());
      waitingRoom.saveArrival(builder.createArrival());
      administrationList.saveVaccineAdministration(builder.createAdministration());
      centerEventList.save(builder.createArrivalEvent());
      centerEventList.save(builder.createVaccinatedEvent());
      centerEventList.save(builder.createDeparturedEvent());
    }
  }
}
