package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class StoreByteCode implements ByteCode, Dumpable {
    private int offsetOfCurrentFrame;
    private String id;

    /**
     * Constructs a StoreCode object with the given arguments.
     * @param args the arguments passed to the StoreCode
     * bytecode, including the offset in the current frame
     * and an optional identifier.
     * @throws IllegalArgumentException if the number of arguments is invalid,
     *         the offset is not a strictly positive value, or the offset value
     *         is not an integer.
     */
    public StoreByteCode(String[] args) {
        // validate the number of arguments
        if ( args.length > 3) {
            throw new IllegalArgumentException("Invalid number of arguments for StoreCode.");
        }

        try {
            // parse and validate the offset value
            int offset = Integer.parseInt(args[1]);
            if (offset < 0) {
                throw new IllegalArgumentException("Offset must be a strictly positive value.");
            }
            this.offsetOfCurrentFrame = offset;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid offset value. Must be an integer.");
        }

        // check if an identifier is present
        if (args.length == 3) {
            this.id = args[2];
        }
    }

    /**
     * Executes the StoreCode bytecode by moving the value from the top of the runtime stack
     * to the specified offset in the current frame of the virtual machine.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // store the value at the specified offset in the current frame
        virtualMachine.store(this.offsetOfCurrentFrame);
    }

    /**
     * Returns a string representation of the StoreCode bytecode. If an identifier is present, it includes the identifier.
     * @return a string representation of the StoreCode bytecode.
     */
    @Override
    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("STORE ").append(offsetOfCurrentFrame);
        if (id != null) {
            builder.append(" ").append(id).append("\tint ").append(id);
        }
        return builder.toString();
    }

    /**
     *Test the StoreCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"STORE", "1", "variableName"};
        StoreByteCode storeCode = new StoreByteCode(arguments);
        System.out.println(storeCode);
    }
}
