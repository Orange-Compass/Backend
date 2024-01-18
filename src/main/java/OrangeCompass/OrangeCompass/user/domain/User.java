package OrangeCompass.OrangeCompass.user.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long id;
    private String email;
    private String userName;
    private String password;
    private Date birth;
    private String type;
    private String loginType;
    private Date createdAt;
    private Date updatedAt;
    private String profileImageUrl;
    private String status;
}
