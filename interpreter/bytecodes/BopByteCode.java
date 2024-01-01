package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class BopByteCode implements ByteCode, Dumpable {
    private String operator;

    /**
     * Constructs a BopCode object with the given arguments.
     * @param args the arguments passed to the BopCode bytecode,
     * including the operator for the binary operation.
     */
    public BopByteCode(String[] args) {
        this.operator = args[1];
    }

    /**
     * Executes the BopCode bytecode by performing the specified binary operation on the top two values
     * from the runtime stack in the virtual machine and pushing the result back to the stack.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.bop(this.operator);
    }

    /**
     * Returns a string representation of the BopByteCode bytecode.
     * @return a string representation of the BopByteCode bytecode.
     */
    @Override
    public String toString(){
        return "BOP " + this.operator;
    }

    /**
     * Test the BopCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"BOP", "+"};
        BopByteCode bopCode = new BopByteCode(arguments);
        System.out.println(bopCode);
    }
}
