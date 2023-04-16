package logic;

import domain.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.OverviewRepository;

public class OverviewHandler {
    private final OverviewRepository overviewRepository;

    public OverviewHandler(Connection connection) {
        this.overviewRepository = new OverviewRepository();
    }

    public String overview1(String gender) {
        return overviewRepository.overview1(gender);
    }

    public void overview2(int courseID) {

    }

    public void overview3(String email, int courseID) {

    }

    public void overview4(String email) {

    }

    public void overview5() {

    }

    public void overview6() {

    }

    public void overview7(int courseID) {

    }

    public void overview8(int courseID) {

    }
}
