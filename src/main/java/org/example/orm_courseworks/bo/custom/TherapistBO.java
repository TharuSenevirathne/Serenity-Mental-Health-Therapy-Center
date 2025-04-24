package org.example.orm_courseworks.bo.custom;

import javafx.collections.ObservableList;
import org.example.orm_courseworks.bo.SuperBO;
import org.example.orm_courseworks.dto.TherapistDTO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface TherapistBO extends SuperBO {
    ObservableList<TherapistDTO> getAllTherapists() throws SQLException, ClassNotFoundException;
    void addTherapist(TherapistDTO therapistDTO, String programId) throws SQLException, ClassNotFoundException;
    boolean deleteTherapist(String id) throws SQLException, ClassNotFoundException;
    boolean updateTherapist(TherapistDTO therapistDTO, String programId) throws SQLException, ClassNotFoundException;
    List<String> getAvailableTherapistIds();
}
