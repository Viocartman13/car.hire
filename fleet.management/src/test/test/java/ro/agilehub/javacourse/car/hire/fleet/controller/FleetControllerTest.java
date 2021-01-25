package test.java.ro.agilehub.javacourse.car.hire.fleet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.controller.FleetController;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.repository.CarRepository;
import ro.agilehub.javacourse.car.hire.fleet.service.CarServiceImpl;
import test.java.ro.agilehub.javacourse.car.hire.fleet.MockMVCSetup;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FleetController.class)
@WithMockUser("jack")
public class FleetControllerTest extends MockMVCSetup {

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarServiceImpl carServiceImpl;

    @Mock
    private CarRepository carRepository;

    @Before
    public void setUp() {
        initMocks(this);
    }


    @Test
    public void getFleetCar_whenInputOk_return200() throws Exception {
        int id = 123;
        var input = new CarDTO();
        input.setRegistrationNumber("uniqueRegistrationNumber");
        input.setId(id);

        CarDO carDO = CarDO.builder().id(id).registrationNumber("uniqueRegistrationNumber").build();

        when(carServiceImpl.findById(id)).thenReturn(carDO);

        var result = mvc.perform(MockMvcRequestBuilders.get("/fleet/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var carDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CarDTO.class);

        assertEquals(id, carDTO.getId().intValue());
    }

    @Test
    public void patchFleetCar_whenInputOk_return200() throws Exception {
        int id = 123;
        final Integer carMileage = 131313;
        var input = new CarDTO();
        input.setRegistrationNumber("uniqueRegistrationNumber");
        input.setMileage(carMileage);
        input.setId(id);

        CarDO carDO = CarDO.builder().id(id).registrationNumber("uniqueRegistrationNumber").mileage(carMileage).build();

        when(carServiceImpl.findById(id)).thenReturn(carDO);

        var result = mvc.perform(MockMvcRequestBuilders.get("/fleet/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var carDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CarDTO.class);

        assertEquals(id, carDTO.getId().intValue());
        assertEquals(carMileage, carDTO.getMileage());
    }
}
