package blueprintPdfCompiler;

import java.util.Objects;

public class DocumentCompany {
    private String name;
    private byte[] sign;

    public DocumentCompany(String name) {
        this.setName(name);
    }

    public DocumentCompany(String name, byte[] sign) {
        this.setName(name);
        this.setSign(sign);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name.trim();
            if (this.name.length() > 11) {
                this.name = this.name.substring(0, 11);
            }
        }
    }

    public byte[] getSign() {
        return sign;
    }

    public void setSign(byte[] sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentCompany)) return false;
        DocumentCompany that = (DocumentCompany) o;
        return Objects.equals(name, that.name) && Objects.equals(sign, that.sign);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, sign);
    }
}
