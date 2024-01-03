import java.util.ArrayList;
import java.util.List;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    
    public void registerStudent() {
        if (capacity > 0) {
            capacity--;
        }
    }

    
    public void dropStudent() {
        capacity++;
    }
}

class Student {
    private int studentId;
    private String name;
    private List<Course> registeredCourses;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerForCourse(Course course) {
        if (registeredCourses.size() < 5 && course.getCapacity() > 0) {
            registeredCourses.add(course);
            course.registerStudent();
            System.out.println(name + " registered for " + course.getTitle());
        } else {
            System.out.println("Registration failed for " + course.getTitle());
        }
    }

    public void dropCourse(Course course) {
        if (registeredCourses.remove(course)) {
            course.dropStudent();
            System.out.println(name + " dropped " + course.getTitle());
        } else {
            System.out.println(name + " is not registered for " + course.getTitle());
        }
    }
}

class CourseListing {
    private List<Course> courses;

    public CourseListing() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void displayCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println(course.getCode() + " - " + course.getTitle() + " (" + course.getCapacity() + " slots)");
        }
    }
}

public class RegistrationSystem {
    public static void main(String[] args) {
        CourseListing courseListing = new CourseListing();

        Course c1 = new Course("CSE101", "Introduction to Computer Science", "Fundamental concepts of CS", 50, "Mon, Wed, Fri");
        Course c2 = new Course("MATH201", "Calculus I", "Limits, derivatives, and integrals", 40, "Tue, Thu");
        Course c3 = new Course("ENG102", "English Composition", "Writing and communication skills", 30, "Mon, Wed");

        courseListing.addCourse(c1);
        courseListing.addCourse(c2);
        courseListing.addCourse(c3);

        courseListing.displayCourses();

        Student student1 = new Student(1, "John Doe");
        Student student2 = new Student(2, "Jane Smith");

        student1.registerForCourse(c1);
        student1.registerForCourse(c2);
        student1.registerForCourse(c3);

        student2.registerForCourse(c1);
        student2.registerForCourse(c2);

        courseListing.displayCourses();

        student1.dropCourse(c2);

        courseListing.displayCourses();
    }
}