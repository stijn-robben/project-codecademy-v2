package logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import data.CertificateRepository;
import domain.Certificate;

public class CertificateHandler {
    private final CertificateRepository certificateRepository;

    public CertificateHandler(Connection connection) {
        this.certificateRepository = new CertificateRepository(connection);
    }

    public void addCertificate(Certificate certificate) throws SQLException {
        certificateRepository.addCertificate(certificate);
    }

    public List<Certificate> getCertificates() throws SQLException {
        return certificateRepository.getCertificates();
    }

    public void deleteCertificate(int certificateID) throws SQLException {
        certificateRepository.deleteCertificate(certificateID);
    }

    public void updateCertificate(Certificate certificate, int certificateID) throws SQLException {
        certificateRepository.updateCertificate(certificate, certificateID);
    }

}
