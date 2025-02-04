package cz.esnhk.cds.security.model.artemis_responses;

import lombok.Data;

import java.util.Date;

@Data
public class UserDetailsResponse {
    private int id;
    private Date last_Login;
    private boolean is_superuser;
    private String object_class;
    private String email;
    private String first_name;
    private String last_name;
    private String number;
    private String sex;
    private Date date_joined;
    private Date date_of_birth;
    private boolean email_subscription;
    private String is_active;
    private boolean staff;
    private String profile_picture;
    private boolean confirmed;
    private String description;
    private String students_count_preference;
    private int faculty;
    private String school_subject;
    private int[] groups;
    private String[] user_permissions;

    public String is_active() {
        return is_active;
    }
}
