package Helpers.Modules;

public class FeedbackHelper {
    String semester, courseCode, feedback;

    public FeedbackHelper(){}
    public FeedbackHelper(String semester, String courseCode, String feedback) {
        this.semester = semester;
        this.courseCode = courseCode;
        this.feedback = feedback;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
