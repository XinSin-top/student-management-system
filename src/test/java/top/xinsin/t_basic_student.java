package top.xinsin;

import top.xinsin.util.sex;

import java.util.Date;

public class t_basic_student {
    private String s_no;//学生学号
    private int s_bed;//学生床号
    private String s_dormitory; //学生寝室
    private String s_class; //学生班级号
    private String s_name; //学生名字
    private int s_age; //学生年龄
    private sex s_sex; //学生性别
    private String s_instructor; //学生辅导员
    private String s_major;//学生专业
    private String s_telephone; //学生电话

    public t_basic_student(String s_no, int s_bed, String s_dormitory, String s_class, String s_name, int s_age, sex s_sex, String s_instructor, String s_major, String s_telephone) {
        this.s_no = s_no;
        this.s_bed = s_bed;
        this.s_dormitory = s_dormitory;
        this.s_class = s_class;
        this.s_name = s_name;
        this.s_age = s_age;
        this.s_sex = s_sex;
        this.s_instructor = s_instructor;
        this.s_major = s_major;
        this.s_telephone = s_telephone;
    }

    public String getS_major() {
        return s_major;
    }

    public void setS_major(String s_major) {
        this.s_major = s_major;
    }



    public t_basic_student() {
    }

    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public int getS_bed() {
        return s_bed;
    }

    public void setS_bed(int s_bed) {
        this.s_bed = s_bed;
    }

    public String getS_dormitory() {
        return s_dormitory;
    }

    public void setS_dormitory(String s_dormitory) {
        this.s_dormitory = s_dormitory;
    }

    public String getS_class() {
        return s_class;
    }

    public void setS_class(String s_class) {
        this.s_class = s_class;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public int getS_age() {
        return s_age;
    }

    public void setS_age(int s_age) {
        this.s_age = s_age;
    }

    public sex getS_sex() {
        return s_sex;
    }

    public void setS_sex(sex s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_instructor() {
        return s_instructor;
    }

    public void setS_instructor(String s_instructor) {
        this.s_instructor = s_instructor;
    }

    public String getS_telephone() {
        return s_telephone;
    }

    public void setS_telephone(String s_telephone) {
        this.s_telephone = s_telephone;
    }

}
