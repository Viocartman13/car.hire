package ro.agilehub.javacourse.car.hire.rental.domain;

import org.bson.types.ObjectId;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.user.domain.UserDO;

import java.time.LocalDateTime;

public class RentalDO {

    private ObjectId id;
    private UserDO user;
    private CarDO car;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String status;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public UserDO getUser() {
        return user;
    }

    public void setUser(UserDO user) {
        this.user = user;
    }

    public CarDO getCar() {
        return car;
    }

    public void setCar(CarDO car) {
        this.car = car;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(LocalDateTime startDateTime) {
        this.startDateTime = startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
