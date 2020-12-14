package ro.agilehub.javacourse.car.hire.fleet.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.agilehub.javacourse.car.hire.api.model.CarDTO;
import ro.agilehub.javacourse.car.hire.fleet.domain.CarDO;
import ro.agilehub.javacourse.car.hire.fleet.service.CarServiceImpl;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FleetController.class)
public class FleetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CarServiceImpl carServiceImpl;

    private static final Integer CAR_ONE_MILEAGE = 0;
    // le-am modificat asa din 2 motive
    // cand le folosesti pentru a construi obiectele de tip DO, alea sunt intre baza de date si controller
    // daca in DTO valoarea e CarDTO.CarClassEnum.SPORTS, atunci in DO va fi mapat stringul SPORTS
    // la fel si invers, ca sa mearga maparea trebuie sa ii dai in DO stringul SPORTS
    // apoi, serializarea valorilor in controller e diferita
    // SPORTS e serializat in Sports, asa ca trebuie sa folosesti CarDTO.CarClassEnum.SPORTS.getValue() la comparare
    private static final CarDTO.CarClassEnum CAR_ONE_CAR_CLASS = CarDTO.CarClassEnum.SPORTS;
    private static final String CAR_ONE_FUEL = "Diesel";
    private static final Integer CAR_TWO_MILEAGE = 22000;
    private static final CarDTO.CarClassEnum CAR_TWO_CAR_CLASS = CarDTO.CarClassEnum.SUPERCAR;
    private static final String CAR_TWO_FUEL = "Gasoline";
    private static final int CAR_LIST_SIZE = 2;


    @Test
    public void addCar_whenInputOk_return201() throws Exception {
        final var id = "123";
        var input = new CarDTO();
        input.setRegistrationNumber("uniqueRegistrationNumber");

        CarDO carDO = CarDO.builder().id("123").registrationNumber("uniqueRegistrationNumber").build();

        when(carServiceImpl.addCar(any())).thenReturn(carDO);

        // deocamdata am scos parametru de status, dar tinand cont de specificul testului, merge verificat
        // ce se intampla si daca e statusul dat ca parametru. ar trebui sa fie de forma /fleet?status=ACTIVE
        var result = mockMvc.perform(MockMvcRequestBuilders.post("/fleet")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andReturn();

        var carDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CarDTO.class);

        assertEquals(id, carDTO.getId());
    }

    @Test
    public void deleteCar_whenInputOk_return200() throws Exception {
        final var id = "123";

        mockMvc.perform(MockMvcRequestBuilders.delete("/fleet/" + id)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        verify(carServiceImpl, times(1)).deleteById(id);
    }

    @Test
    public void getFleet_return200() throws Exception {

        var car1 = CarDO.builder().carClass(CAR_ONE_CAR_CLASS.name()).fuel(CAR_ONE_FUEL).mileage(CAR_ONE_MILEAGE).build();
        var car2 = CarDO.builder().carClass(CAR_TWO_CAR_CLASS.name()).fuel(CAR_TWO_FUEL).mileage(CAR_TWO_MILEAGE).build();

        when(carServiceImpl.findAll()).thenReturn(List.of(car1, car2));

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fleet"))
                .andExpect(status().isOk())
                .andReturn();

        var carList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<CarDTO>>() {
        });

        assertEquals(CAR_LIST_SIZE, carList.size());
        assertEquals(CAR_ONE_CAR_CLASS, carList.get(0).getCarClass());
        assertEquals(CAR_ONE_FUEL, carList.get(0).getFuel());
        assertEquals(CAR_ONE_MILEAGE, carList.get(0).getMileage());
        assertEquals(CAR_TWO_MILEAGE, carList.get(1).getMileage());
        assertEquals(CAR_TWO_CAR_CLASS, carList.get(1).getCarClass());
        assertEquals(CAR_TWO_FUEL, carList.get(1).getFuel());
    }

    @Test
    public void getFleetCar_whenInputOk_return200() throws Exception {
        final var id = "123";
        var input = new CarDTO();
        input.setRegistrationNumber("uniqueRegistrationNumber");
        input.setId(id);

        CarDO carDO = CarDO.builder().id(id).registrationNumber("uniqueRegistrationNumber").build();

        when(carServiceImpl.findById(id)).thenReturn(carDO);

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/fleet/" + id))
                .andExpect(status().isOk())
                .andReturn();

        var carDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CarDTO.class);

        assertEquals(id, carDTO.getId());
    }
}
