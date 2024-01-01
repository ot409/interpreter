package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class DumpByteCode implements ByteCode {
    private String dumpOption;

    /**
     * Constructs a DumpByteCode object with the given arguments.
     * @param args the arguments passed to the DumpCode bytecode, including the dump option.
     * @throws IllegalArgumentException if the number of arguments is invalid or the dump option is not 'ON' or 'OFF'.
     */
    public DumpByteCode(String[] args) {
        /*System.out.println(args.length);*/
        // validate the number of arguments
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for DumpCode.");
        }
        // validate the dump option
        String option = args[1].toUpperCase();
        if (!option.equals("ON") && !option.equals("OFF")) {
            throw new IllegalArgumentException("Invalid dump option for DumpCode. Expected 'ON' or 'OFF'.");
        }
        this.dumpOption = option;
    }

    @Override
    public void execute(VirtualMachine virtualMachine) {
        // set the dumping option on the virtual machine
        if (dumpOption.equals("ON")) {
            virtualMachine.setDumping(true);
        } else if (dumpOption.equals("OFF")) {
            virtualMachine.setDumping(false);
        }
    }

    /**
     * Returns a string representation of the DumpByteCode bytecode.
     * @return a string representation of the DumpByteCode bytecode.
     */
    /*@Override
    public String toString() {
        return "DUMP " + dumpOption;
    }*/

    /**
     * Test the DumpByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    /*public static void main(String[] args) {
        String[] arguments = {"DUMP", "ON"};
        DumpCode dumpCode = new DumpCode(arguments);
        System.out.println(dumpCode);
    }*/
}
