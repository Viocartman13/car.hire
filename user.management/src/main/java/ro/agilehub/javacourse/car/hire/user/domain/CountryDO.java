package ro.agilehub.javacourse.car.hire.user.domain;

import org.bson.types.ObjectId;

public class CountryDO {

    private ObjectId id;
    private String name;
    private String isoCode;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }
}
