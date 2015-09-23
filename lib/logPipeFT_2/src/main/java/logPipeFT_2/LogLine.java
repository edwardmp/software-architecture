package logPipeFT_2;

public class LogLine {

    public String level;
    public String text;
    public String moduleName;

    public LogLine(String level, String moduleName, String text) {
        super();
        this.level = level;
        this.text = text;
        this.moduleName = moduleName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Override
    public String toString() {
        return "[" + getLevel() + "][" + getModuleName() + "][" + getText() + "]";
    }

}