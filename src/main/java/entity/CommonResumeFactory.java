package entity;

public class CommonResumeFactory implements ResumeFactory {
    @Override
    public Resume createResume() {
        return new CommonResume();
    }
}
