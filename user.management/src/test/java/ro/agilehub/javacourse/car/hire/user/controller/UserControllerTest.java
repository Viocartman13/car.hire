package ro.agilehub.javacourse.car.hire.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ro.agilehub.javacourse.car.hire.api.model.CreatedDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.user.controller.mapper.UserDTOMapper;
import ro.agilehub.javacourse.car.hire.user.service.definition.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @MockBean
    private UserDTOMapper userDTOMapper;

    @Test
    public void addUser_whenInputOk_return201() throws Exception {
        final var ID = 123;
        var input = new UserDTO().email("vladcarcu@email.com");

        when(userService.createNewUser(any())).thenReturn(ID);

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(input)))
                .andExpect(status().isCreated())
                .andReturn();

        var createdDTO = objectMapper.readValue(result.getResponse().getContentAsString(), CreatedDTO.class);

        assertEquals(ID, createdDTO.getId());
    }
}