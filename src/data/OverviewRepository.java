package data;

import java.sql.*;

public class OverviewRepository {

    public String overview1(String gender) {
        String query = "SELECT (COUNT(c.CertificateID) / (SELECT COUNT(*) FROM Registration r JOIN Student s ON r.StudentEmail = s.StudentEmail WHERE s.Gender = ?) * 100) AS 'Percentage' FROM Certificate c JOIN Student s ON c.StudentEmail = s.StudentEmail WHERE s.Gender = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, gender);
            statement.setString(2, gender);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                double percentage = resultSet.getDouble("Percentage");
                return String.format("%.2f%%", percentage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String overview2(int courseID) {
        String query = "SELECT m.ContentItemID, AVG(CAST(p.Progress AS FLOAT)) / 100.0 AS 'Average Progress Percentage' FROM dbo.Progress p JOIN dbo.Module m ON p.ContentItemID = m.ContentItemID JOIN dbo.ContentItem ci ON m.ContentItemID = ci.ContentItemID JOIN dbo.Course c ON ci.CourseID = c.CourseID WHERE c.CourseID = ? GROUP BY m.ContentItemID;";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String result = "";
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setInt(1, courseID);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String contentItemID = resultSet.getString("ContentItemID");
                String percentage = String.valueOf(resultSet.getDouble("Average Progress Percentage"));
                result += "" + contentItemID + " " + percentage + "\n";
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (Exception e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    public String overview3(String email, int courseID) {
        String query = "SELECT m.ContentItemID, m.Title, p.Progress, (p.Progress / (SELECT COUNT(*) FROM ContentItem WHERE CourseID = c.CourseID) * 100) AS Percentage FROM Progress p JOIN Student s ON p.StudentEmail = s.StudentEmail JOIN ContentItem ci ON p.ContentItemID = ci.ContentItemID JOIN Module m ON ci.ContentItemID = m.ContentItemID JOIN Course c ON ci.CourseID = c.CourseID WHERE s.StudentEmail = ? AND c.CourseID = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            statement.setInt(2, courseID);
            resultSet = statement.executeQuery();
            String result = "";
            while (resultSet.next()) {
                int contentItemID = resultSet.getInt("ContentItemID");
                String title = resultSet.getString("Title");
                int progress = resultSet.getInt("Progress");
                int percentage = resultSet.getInt("Percentage");
                result += contentItemID + " " + title + " " + progress + " " + percentage + "\n";
            }
            System.out.println(result);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String overview4(String email) {
        String query = "SELECT c.NameEmployee, c.StudentEmail, c.RegistrationDate FROM Certificate c INNER JOIN Registration ON c.CourseID = Registration.CourseID WHERE Registration.StudentEmail = ?";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            StringBuilder result = new StringBuilder();
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String nameEmployee = resultSet.getString("NameEmployee");
                String studentEmail = resultSet.getString("StudentEmail");
                String registrationDate = resultSet.getString("RegistrationDate");
                result.append(nameEmployee).append(" ").append(studentEmail).append(" ").append(registrationDate)
                        .append("\n");
            }
            return result.toString();
        } catch (SQLException ex) {
            // handle the exception
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // handle the exception
            }
        }
        return null;
    }

    public String overview5() {
        String query = "SELECT TOP 3 w.Title, COUNT(p.ContentItemID) AS ViewCount FROM Webcast w INNER JOIN Progress p ON w.ContentItemID = p.ContentItemID GROUP BY w.Title ORDER BY ViewCount DESC;";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String result = "";
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("Title");
                String viewCount = String.valueOf(resultSet.getInt("ViewCount"));
                result += "" + title + " " + viewCount + "\n";
            }
            return result;
        } catch (SQLException ex) {
            // handle the exception
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                // handle the exception
            }
        }
        return null;
    }

    public String overview6() {
        String query = "SELECT TOP 3 CourseID, COUNT(CertificateID) AS NumCertificates FROM Certificate GROUP BY CourseID ORDER BY NumCertificates DESC;";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            String result = "";
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String courseID = resultSet.getString("CourseID");
                String numCertificates = String.valueOf(resultSet.getInt("NumCertificates"));
                result += "CourseID: " + courseID + ", Number of certificates: " + numCertificates + "\n";
            }
            return result;
        } catch (SQLException ex) {
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public String overview7(int courseID) {
        String query = "SELECT DISTINCT c.CourseName AS RecommendedCourse FROM Course c INNER JOIN RecommendedCourse rc ON c.CourseID = rc.RecommendedCourseId INNER JOIN ContentItem ci ON ci.CourseID = rc.CourseID WHERE ci.CourseID = ?;";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String result = "";
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(courseID));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String recommendedCourse = resultSet.getString("RecommendedCourse");
                result += "Recommended course: " + recommendedCourse + "\n";
            }
            return result;
        } catch (SQLException ex) {
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public String overview8(int courseID) {
        String query = "SELECT COUNT(*) AS 'Number of Students Completed' FROM Certificate WHERE CourseID = ?;";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            String result = "";
            conn = DatabaseConnection.getConnection();
            statement = conn.prepareStatement(query);
            statement.setString(1, String.valueOf(courseID));
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String number = String.valueOf(resultSet.getInt("Number of Students Completed"));
                result += "Number of students completed: " + number + "\n";
            }
            return result;
        } catch (SQLException ex) {
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }
}
