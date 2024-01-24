package OrangeCompass.OrangeCompass.user.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Date birth;
    private String type;
    private String loginType;
    private Date createdAt;
    private Date updatedAt;
    private String profileImageUrl;
    private String status;
}
