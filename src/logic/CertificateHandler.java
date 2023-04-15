package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import domain.Certificate;

public class CertificateHandler {
    private final CertificateHandler certificateRepository;

    public CertificateHandler(Connection connection) {
        this.certificateRepository = new CertificateHandler(connection);
    }

    public void addStudent(Certificate certificate) throws SQLException {
        certificateRepository.addStudent(certificate);
    }

    public List<Certificate> getStudents() throws SQLException {
        return certificateRepository.getStudents();
    }

    public void deleteStudent(String email) throws SQLException {
        certificateRepository.deleteStudent(email);
    }

    public void updateStudent(Certificate certificate) throws SQLException {
        certificateRepository.updateStudent(certificate);
    }
    
}
