package logic;

import java.sql.Connection;
import data.OverviewRepository;

public class OverviewHandler {
    private final OverviewRepository overviewRepository;

    public OverviewHandler(Connection connection) {
        this.overviewRepository = new OverviewRepository();
    }

    public String overview1(String gender) {
        return overviewRepository.overview1(gender);
    }

    public String overview2(int courseID) {
        return overviewRepository.overview2(courseID);
    }

    public String overview3(String email, int courseID) {
        return overviewRepository.overview3(email, courseID);
    }

    public String overview4(String email) {
        return overviewRepository.overview4(email);
    }

    public String overview5() {
        return overviewRepository.overview5();
    }

    public String overview6() {
        return overviewRepository.overview6();
    }

    public String overview7(int courseID) {
        return overviewRepository.overview7(courseID);
    }

    public String overview8(int courseID) {
        return overviewRepository.overview8(courseID);
    }
}
