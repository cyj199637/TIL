package c.service;

public class MemberDTO {
    public String name;
    public String phone;
    public String email;

    public MemberDTO(){}

    public MemberDTO(String name) {
        this.name = name;
    }

    public MemberDTO(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public MemberDTO(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return "Name="+name+" Phone="+phone+" Email="+email;
    }
    
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;

        MemberDTO other = (MemberDTO) obj;

        if (name == null) {
            if (other.name != null) return true;
        } else if (!name.equals(other.name)) return false;

        if (email == null) {
            if (other.email != null) return true;
        } else if (!email.equals(other.email)) return false;

        if (phone == null) {
            if (other.phone != null) return true;
        } else if (!phone.equals(other.phone)) return false;

        return true;
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((phone == null) ? 0 : phone.hashCode());
        return result;
    }
}