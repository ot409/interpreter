package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class HaltByteCode implements ByteCode {

    /**
     * Constructs a HaltCode object with the given arguments.
     * @param args the arguments passed to the HaltByteCode bytecode (none required).
     */
    public HaltByteCode(String[] args) {
        //  HALT bytecode takes no argument
    }

    /**
     * Executes the HaltCode bytecode by requesting the VirtualMachine
     * to halt execution of the current program.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // request the VirtualMachine to halt execution of the current program
        virtualMachine.halt();
    }
}
