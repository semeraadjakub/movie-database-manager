package Serialization;

import static Serialization.SerializationWriter.*;

public class MString extends Base{
    private final byte CONTAINER_TYPE = ContainerType.STRING;
    private short stringLength;
    private byte[] string;

    public MString(String name, String str) {
        setName(name);
        stringLength = (short)str.length();
        string = str.getBytes();
        size += 1 + 2 + string.length;
    }

    public String getString() {
        return new String(string);
    }

    public int serialize(int pointer, byte[] bytes) {
        pointer = writeBytes(pointer, bytes, CONTAINER_TYPE);
        pointer = writeBytes(pointer, bytes, size);
        pointer = writeBytes(pointer, bytes, nameLength);
        pointer = writeBytes(pointer, bytes, name.getBytes());
        pointer = writeBytes(pointer, bytes, stringLength);
        pointer = writeBytes(pointer, bytes, string);
        return pointer;
    }

    public static MString Deserialize(int pointer, byte[] bytes) {
        MString string = new MString("", "");
        pointer++;
        string.size = readInt(pointer, bytes);
        pointer += 4;
        string.nameLength = readShort(pointer, bytes);
        pointer += 2;
        string.name = readString(pointer, bytes, string.nameLength);
        pointer += string.nameLength;
        string.stringLength = readShort(pointer, bytes);
        pointer += 2;
        string.string = readString(pointer, bytes, string.stringLength).getBytes();
        pointer += string.stringLength;
        return string;
    }
}
