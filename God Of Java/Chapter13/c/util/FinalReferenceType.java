package c.util;

import c.service.MemberDTO;

public class FinalReferenceType {
    final MemberDTO dto = new MemberDTO();

    public static void main(String []args) {
        FinalReferenceType referenceType = new FinalReferenceType();
        referenceType.checkDTO();
    }

    public void checkDTO() {
        System.out.println(dto);
        // dto = new MemberDTO();
        dto.name = "Lora";
        System.out.println(dto);
    }
}