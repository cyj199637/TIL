package b.operator;

public class OperatorConditionalTriple {
    public static void main(String args[]) {
        OperatorConditionalTriple foo = new OperatorConditionalTriple();
        foo.doBlindDate(90);
        foo.doBlindDate(40);
    }

    public boolean doBlindDate(int point) {
        boolean doBlindDateFlag=false;
        doBlindDateFlag = (point>80) ? true : false;
        System.out.println(point+": "+doBlindDateFlag);
        return doBlindDateFlag;
    }
}