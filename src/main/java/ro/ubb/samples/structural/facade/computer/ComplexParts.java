package ro.ubb.samples.structural.facade.computer;

/* Complex parts */

class CPU {
    public void freeze() {
    }

    public void jump(long position) {
    }

    public void execute() {
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        return new byte[10];
    }
}

class Memory {
    public void load(long position, byte[] data) {
    }
}
