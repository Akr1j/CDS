package cz.esnhk.cds.model.users;

import lombok.Data;

@Data
public class InternationalStudentsDetailsResponse {

    private long id;
    private String last_login;
    private boolean is_superuser;
    private String object_class;
    private String email;
    private String first_name;
    private String last_name;
    private String number;
    private String sex;
    private String dateJoined;
    private String dateOfBirth;
    private boolean emailSubscription;
    private boolean is_active;
    private boolean staff;
    private String profile_picture;
    private String country;
    private String homeUniversity;
    private String description;
    private String arrival_date;
    private String arrival_note;
    private String arrival_mates;
    private int faculty;
    private int assigned_buddy;
    private int arrival_place;
    private int accommodation;
    private int student_group;
    private int[] groups;
    private int[] user_permissions;
    private int[] semesters;
}
