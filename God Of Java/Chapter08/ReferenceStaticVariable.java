public class ReferenceStaticVariable {
    static String name;
    
    public ReferenceStaticVariable() {}
    public ReferenceStaticVariable(String name) {
        this.name = name;
    }
    public static void main(String [] args) {
        ReferenceStaticVariable reference = new ReferenceStaticVariable();
        reference.checkName();
    }
    public void checkName() {
        ReferenceStaticVariable reference1 = new ReferenceStaticVariable("Lora");
        System.out.println(reference1.name);
        ReferenceStaticVariable reference2 = new ReferenceStaticVariable("Sally");
        System.out.println(reference1.name);
    }
}