package ro.agilehub.javacourse.car.hire.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ro.agilehub.javacourse.car.hire.api.model.PatchDocumentDTO;
import ro.agilehub.javacourse.car.hire.api.model.UserDTO;
import ro.agilehub.javacourse.car.hire.api.specification.UserApi;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<UserDTO> createUser(@Valid UserDTO userDTO) {
        UserDTO user = new UserDTO();
        user.setId("13");
        user.setUserName("Viocartman");
        user.setFirstName("Vio");
        user.setLastName("Cartman");

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDTO> getUser(String userId) {
        UserDTO user = new UserDTO();
        user.setId("13");
        user.setUserName("Viocartman");
        user.setFirstName("Vio");
        user.setLastName("Cartman");

        return ResponseEntity.ok(user);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        UserDTO user1 = new UserDTO();
        user1.setId("13");
        user1.setUserName("Viocartman");
        user1.setFirstName("Vio");
        user1.setLastName("Cartman");

        UserDTO user2 = new UserDTO();
        user2.setId("26");
        user2.setUserName("Viocartman1");
        user2.setFirstName("Vio1");
        user2.setLastName("Cartman1");

        return ResponseEntity.ok(List.of(user1, user2));
    }

    @Override
    public ResponseEntity<UserDTO> patchUser(String userId, @Valid List<PatchDocumentDTO> patchDocumentDTO) {
        UserDTO user2 = new UserDTO();
        user2.setId("26");
        user2.setUserName("Viocartman1");
        user2.setFirstName("Vio1");
        user2.setLastName("Cartman1");

        return ResponseEntity.ok(user2);
    }
}
