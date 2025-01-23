package cz.esnhk.cds.model;

import java.util.Date;

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
    private boolean is_active;

    ;
    private boolean staff;
    private String profile_picture;
    private boolean confirmed;
    private String description;
    private String students_count_preference;
    private int faculty;
    private String school_subject;
    private int[] groups;
    private String[] user_permissions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getLast_Login() {
        return last_Login;
    }

    public void setLast_Login(Date last_Login) {
        this.last_Login = last_Login;
    }

    public boolean isIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(boolean is_superuser) {
        this.is_superuser = is_superuser;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDate_joined() {
        return date_joined;
    }

    public void setDate_joined(Date date_joined) {
        this.date_joined = date_joined;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public boolean isEmail_subscription() {
        return email_subscription;
    }

    public void setEmail_subscription(boolean email_subscription) {
        this.email_subscription = email_subscription;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudents_count_preference() {
        return students_count_preference;
    }

    public void setStudents_count_preference(String students_count_preference) {
        this.students_count_preference = students_count_preference;
    }

    public int getFaculty() {
        return faculty;
    }

    public void setFaculty(int faculty) {
        this.faculty = faculty;
    }

    public String getSchool_subject() {
        return school_subject;
    }

    public void setSchool_subject(String school_subject) {
        this.school_subject = school_subject;
    }

    public int[] getGroups() {
        return groups;
    }

    public void setGroups(int[] groups) {
        this.groups = groups;
    }

    public String[] getUser_permissions() {
        return user_permissions;
    }

    public void setUser_permissions(String[] user_permissions) {
        this.user_permissions = user_permissions;
    }

    private static class json_state {
        boolean is_notified_about_new_buddy;
    }

}
