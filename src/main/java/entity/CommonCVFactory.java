package entity;

public class CommonCVFactory implements CVFactory {
    @Override
    public CV createCV() {
        return new CommonCV();
    }
}

