package dto;

import enums.UserType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Profile {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
    private String password;
    private UserType type;
    private LocalDateTime create_date;
    private int result;
}
