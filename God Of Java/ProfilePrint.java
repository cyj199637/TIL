public class ProfilePrint {
    byte age;
    String name;
    boolean isMarried;

    public static void main(String [] args) {
        ProfilePrint profile=new ProfilePrint();
        profile.setAge((byte)25);
        profile.setName("Lora");
        profile.setMarried(false);

        System.out.println(profile.getAge());
        System.out.println(profile.getName());
        System.out.println(profile.isMarried());
    }

    public void setAge(byte paraAge) {
        age=paraAge;
    }

    public byte getAge() {
        return age;
    }

    public void setName(String paraName) {
        name=paraName;
    }

    public String getName() {
        return name;
    }

    public void setMarried(boolean paraIsMarried) {
        isMarried=paraIsMarried;
    }

    public boolean isMarried() {
        return isMarried;
    }
}