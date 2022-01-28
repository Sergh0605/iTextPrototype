package documentGenerator;

import java.util.Objects;

public class RowOfContentsDocument {
    private String column1;
    private String column2;
    private String column3;

    public RowOfContentsDocument(String column1, String column2, String column3) {
        this.column1 = column1;
        this.column2 = column2;
        this.column3 = column3;
    }

    public String getColumn1() {
        return column1;
    }

    public void setColumn1(String column1) {
        this.column1 = column1;
    }

    public String getColumn2() {
        return column2;
    }

    public void setColumn2(String column2) {
        this.column2 = column2;
    }

    public String getColumn3() {
        return column3;
    }

    public void setColumn3(String column3) {
        this.column3 = column3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RowOfContentsDocument that)) return false;
        return Objects.equals(column1, that.column1) && Objects.equals(column2, that.column2) && Objects.equals(column3, that.column3);
    }

    @Override
    public int hashCode() {
        return Objects.hash(column1, column2, column3);
    }
}
