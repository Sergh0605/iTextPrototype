package documentGenerator;

import java.util.Arrays;
import java.util.Objects;

public class DocumentCompany {
    private String name;
    private byte[] logo;
    private String city;
    private String signerPosition;
    private String signerName;

    public DocumentCompany(String name, byte[] logo, String city, String signerPosition, String signerName) {
        this.name = name;
        this.logo = logo;
        this.city = city;
        this.signerPosition = signerPosition;
        this.signerName = signerName;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSignerPosition() {
        return signerPosition;
    }

    public void setSignerPosition(String signerPosition) {
        this.signerPosition = signerPosition;
    }

    public String getSignerName() {
        return signerName;
    }

    public void setSignerName(String signerName) {
        this.signerName = signerName;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name.trim();
            if (this.name.length() > 11) {
                this.name = this.name.substring(0, 11);
            }
        }
    }

    public byte[] getLogo() {
        return logo;
    }

    public void setLogo(byte[] logo) {
        this.logo = logo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DocumentCompany that)) return false;
        return Objects.equals(name, that.name) && Arrays.equals(logo, that.logo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, Arrays.hashCode(logo));
    }
}
