package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class WriteByteCode implements ByteCode, Dumpable {
    public WriteByteCode(String[] args) {

    }

    /**
     * Executes the WriteByteCode bytecode by printing the top value of the runtime stack in the virtual machine.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.printPeek();
    }

    /**
     * Returns a string representation of the WriteByteCode bytecode.
     * @return a string representation of the WriteByteCode bytecode.
     */
    @Override
    public String toString(){
        return "WRITE";
    }

    /**
     * Test the WriteByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"WRITE"};
        WriteByteCode writeByteCode = new WriteByteCode(arguments);
        System.out.println(writeByteCode);
    }
}
