public class ReferenceConstructor {
    public static void main(String [] args) {
        ReferenceConstructor reference = new ReferenceConstructor();
        reference.makeMemberDTO();
    }

    public MemberDTO makeMemberDTO() {
        MemberDTO dto1 = new MemberDTO();
        MemberDTO dto2 = new MemberDTO("Lora");
        MemberDTO dto3 = new MemberDTO("Lora", "0101241234");
        MemberDTO dto4 = new MemberDTO("Lora", "01012341234", "lieet2015@gmail.com");

        return dto4;
    }
}