// use_case/build_cv/BuildCVOutputData.java
package use_case.build_cv;

public class BuildCVOutputData {
    private String formattedCV;
    private String message;

    public BuildCVOutputData(String formattedCV, String message) {
        this.formattedCV = formattedCV;
        this.message = message;
    }

    public String getFormattedCV() {
        return formattedCV;
    }

    public String getMessage() {
        return message;
    }
}
